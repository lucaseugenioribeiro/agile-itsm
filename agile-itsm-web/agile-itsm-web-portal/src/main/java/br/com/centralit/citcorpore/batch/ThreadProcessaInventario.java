package br.com.centralit.citcorpore.batch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import br.com.centralit.citcorpore.bean.FormulaDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.NetMapDTO;
import br.com.centralit.citcorpore.comm.server.XmlReadDtdAgente;
import br.com.centralit.citcorpore.metainfo.script.ScriptRhinoJSExecute;
import br.com.centralit.citcorpore.metainfo.util.RuntimeScript;
import br.com.centralit.citcorpore.negocio.FormulaService;
import br.com.centralit.citcorpore.negocio.ItemConfiguracaoService;
import br.com.centralit.citcorpore.snmp.SNMPManager;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilTratamentoArquivos;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ThreadProcessaInventario extends Thread {

    private NetMapDTO netMapDTO;
    private boolean finish = false;
    private boolean breakThread = false;
    private Socket echoSocket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    @Override
    public void run() {

        if (netMapDTO == null) {
            finish = true;
            return;
        }
        netMapDTO.setNovoIC(false);
        netMapDTO.setForce(false);
        final SimpleDateFormat spd = new SimpleDateFormat("yyyyMMdd");
        final Date dataAtual = UtilDatas.getDataAtual();
        final String dataAtualInv = spd.format(dataAtual).trim();
        boolean agenteLocalDOTNET = false;
        boolean agenteServerWindows = false;
        boolean agenteSNMP = false;
        String nameOrIp = "";

        if (netMapDTO.getNome() != null) {
            nameOrIp = netMapDTO.getNome();
        } else {
            nameOrIp = netMapDTO.getIp();
        }
        FormulaService formulaService = null;

        try {
            formulaService = (FormulaService) ServiceLocator.getInstance().getService(FormulaService.class, null);
        } catch (final ServiceException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        MonitoraAtivosDiscovery.MENSAGEM_PROCESSAMENTO = "mostrarStatusInventario.verificandoOAtivo" + "#" + nameOrIp;

        File fXX = new File(MonitoraAtivosDiscovery.DIRETORIO_ARQUIVOS_INVENTARIO + "CITSMART_INVENTORY_" + nameOrIp + "_" + dataAtualInv + ".BKP");
        if (!fXX.exists()) {
            String ipServidorInv = null;
            try {
                ipServidorInv = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.IP_SERVIDOR_INVENTARIO, null);
            } catch (final Exception e1) {
                e1.printStackTrace();
            }

            boolean arquivoExiste = false;
            MonitoraAtivosDiscovery.MENSAGEM_PROCESSAMENTO = "mostrarStatusInventario.capturandoInventario" + "#" + nameOrIp;
            try {
                final Collection<FormulaDTO> listFormula = formulaService.findByIdentificador(FormulaDTO.FORMULA_INVENTORY_PROCESS_BEFORE_CAPTURE);

                if (listFormula != null && !listFormula.isEmpty()) {
                    for (final FormulaDTO formulaDTO : listFormula) {
                        if (formulaDTO != null) {
                            final String strScript = formulaDTO.getConteudo();
                            if (strScript != null && !strScript.trim().equalsIgnoreCase("")) {
                                final ScriptRhinoJSExecute scriptExecute = new ScriptRhinoJSExecute();
                                final RuntimeScript runtimeScript = new RuntimeScript();
                                final Context cx = Context.enter();
                                final Scriptable scope = cx.initStandardObjects();
                                scope.put("listaInfo", scope, null);
                                scope.put("fileName", scope, MonitoraAtivosDiscovery.DIRETORIO_ARQUIVOS_INVENTARIO + "CITSMART_INVENTORY_" + nameOrIp + "_"
                                        + dataAtualInv + ".XML");
                                scope.put("netMapDTO", scope, netMapDTO);
                                scope.put("thread", scope, this);
                                scope.put("out", scope, System.out);
                                scope.put("dataAtualInv", scope, dataAtualInv);
                                scope.put("RuntimeScript", scope, runtimeScript);
                                scriptExecute.processScript(cx, scope, strScript, ThreadProcessaInventario.class.getName() + "_"
                                        + FormulaDTO.FORMULA_INVENTORY_PROCESS_SAVE);
                            }
                        }
                    }
                }
                if (finish) {
                    return;
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
            fXX = new File(MonitoraAtivosDiscovery.DIRETORIO_ARQUIVOS_INVENTARIO + "CITSMART_INVENTORY_" + nameOrIp + "_" + dataAtualInv + ".XML");
            if (fXX.exists()) {
                arquivoExiste = true;
            } else {
                agenteLocalDOTNET = this.geraInventarioAtivoAgenteLocal(nameOrIp, dataAtualInv);
                if (!agenteLocalDOTNET) { // Se nao tem AGENTE LOCAL .NET, tenta AGENTE SERVER
                    if (ipServidorInv != null && !ipServidorInv.trim().equalsIgnoreCase("")) {
                        agenteServerWindows = this.geraInventarioAtivo(ipServidorInv, nameOrIp, nameOrIp, dataAtualInv);
                    }
                    if (!agenteServerWindows) { // Se nao tem AGENTE SERVER .NET, tenta SNMP
                        agenteSNMP = this.geraInventarioAtivoSNMP(nameOrIp, dataAtualInv);
                        if (agenteSNMP) {
                            MonitoraAtivosDiscovery.MENSAGEM_PROCESSAMENTO = "mostrarStatusInventario.inventarioDoAtivo" + "#" + nameOrIp + "#"
                                    + "mostrarStatusInventario.caturadoComSucesso";
                            arquivoExiste = true;
                        } else {
                            MonitoraAtivosDiscovery.MENSAGEM_PROCESSAMENTO = "mostrarStatusInventario.inventarioDoAtivo" + "#" + nameOrIp + "#"
                                    + "mostrarStatusInventario.problemaAoCapturarInformações";
                        }
                    } else {
                        // Deu certo, entao grava na lista para processamento
                        MonitoraAtivosDiscovery.MENSAGEM_PROCESSAMENTO = "mostrarStatusInventario.inventarioDoAtivo" + "#" + nameOrIp + "#"
                                + "mostrarStatusInventario.caturadoComSucesso";
                        arquivoExiste = true;
                    }
                } else {
                    // Deu certo, entao grava na lista para processamento
                    MonitoraAtivosDiscovery.MENSAGEM_PROCESSAMENTO = "mostrarStatusInventario.inventarioDoAtivo" + "#" + nameOrIp + "#"
                            + "mostrarStatusInventario.caturadoComSucesso";
                    arquivoExiste = true;
                }
            }
            if (arquivoExiste) {

                boolean erroAux = false;
                MonitoraAtivosDiscovery.MENSAGEM_PROCESSAMENTO = "mostrarStatusInventario.processamentoOInventarioDoAtivo" + "#" + nameOrIp;

                final String fileName = MonitoraAtivosDiscovery.DIRETORIO_ARQUIVOS_INVENTARIO + "CITSMART_INVENTORY_" + nameOrIp + "_" + dataAtualInv + ".XML";

                File f = new File(fileName);
                if (!f.exists()) {
                    MonitoraAtivosDiscovery.MENSAGEM_PROCESSAMENTO = "mostrarStatusInventario.arquivoDeInvetarioNaoExiste" + "#" + nameOrIp;
                    finish = true;
                    return;
                }
                f = null;
                StringBuilder strBuffer = null;
                try {
                    strBuffer = UtilTratamentoArquivos.lerArquivoCharUTF8(fileName);
                } catch (final Exception e) {
                    e.printStackTrace();
                    erroAux = true;
                }
                if (!erroAux) {
                    final XmlReadDtdAgente xmlReadDtdAgente = new XmlReadDtdAgente();
                    List<ItemConfiguracaoDTO> list = null;
                    try {
                        if (strBuffer != null) {
                            String aux = strBuffer.toString();
                            if (aux.indexOf("<") > -1) {
                                aux = aux.substring(aux.indexOf("<"));
                                list = xmlReadDtdAgente.XmlReadDtdAgent(aux);
                            }
                        }
                    } catch (final ServiceException e) {
                        System.out.println("CITSMART --> PROBLEMAS NO PROCESSAMENTO DO ARQUIVO: " + fileName);
                        e.printStackTrace();
                        erroAux = true;
                    } catch (final LogicException e) {
                        System.out.println("CITSMART --> PROBLEMAS NO PROCESSAMENTO DO ARQUIVO: " + fileName);
                        e.printStackTrace();
                        erroAux = true;
                    } catch (final Exception e) {
                        System.out.println("CITSMART --> PROBLEMAS NO PROCESSAMENTO DO ARQUIVO: " + fileName);
                        e.printStackTrace();
                        erroAux = true;
                    }
                    ItemConfiguracaoService serviceItem = null;
                    try {
                        serviceItem = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(ItemConfiguracaoService.class, null);
                    } catch (final ServiceException e) {
                        e.printStackTrace();
                        erroAux = true;
                    } catch (final Exception e) {
                        e.printStackTrace();
                        erroAux = true;
                    }
                    if (!erroAux) {
                        try {
                            if (list != null) {
                                if (!breakThread) {
                                    try {
                                        final Collection<FormulaDTO> col = formulaService.findByIdentificador(FormulaDTO.FORMULA_INVENTORY_PROCESS_SAVE);
                                        if (col != null && !col.isEmpty()) {
                                            for (final FormulaDTO formulaDTO : col) {
                                                if (formulaDTO != null) {
                                                    final String strScript = formulaDTO.getConteudo();
                                                    if (strScript != null && !strScript.trim().equalsIgnoreCase("")) {
                                                        final ScriptRhinoJSExecute scriptExecute = new ScriptRhinoJSExecute();
                                                        final RuntimeScript runtimeScript = new RuntimeScript();
                                                        final Context cx = Context.enter();
                                                        final Scriptable scope = cx.initStandardObjects();
                                                        scope.put("listaInfo", scope, list);
                                                        scope.put("fileName", scope, fileName);
                                                        scope.put("netMapDTO", scope, netMapDTO);
                                                        scope.put("thread", scope, this);
                                                        scope.put("out", scope, System.out);
                                                        scope.put("dataAtualInv", scope, dataAtualInv);
                                                        scope.put("RuntimeScript", scope, runtimeScript);
                                                        scriptExecute.processScript(cx, scope, strScript, ThreadProcessaInventario.class.getName() + "_"
                                                                + FormulaDTO.FORMULA_INVENTORY_PROCESS_SAVE);
                                                    }
                                                }
                                            }
                                        }
                                        if (finish) {
                                            return;
                                        }
                                    } catch (final Exception e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        final List<ItemConfiguracaoDTO> listaSincronizada = Collections.synchronizedList(new ArrayList<ItemConfiguracaoDTO>(
                                                list));
                                        synchronized (listaSincronizada) {
                                            if (listaSincronizada != null && !listaSincronizada.isEmpty()) {
                                                for (final ItemConfiguracaoDTO itemConfiguracaoDTO : listaSincronizada) {
                                                    serviceItem.createItemConfiguracao(itemConfiguracaoDTO, null);
                                                }
                                            }
                                        }
                                    } catch (final Exception e) {
                                        e.printStackTrace();
                                    }

                                    final String fileNameBkp = fileName.replaceAll(".XML", ".BKP");
                                    UtilTratamentoArquivos.copyFile(fileName, fileNameBkp);
                                    File fAux = new File(fileName);
                                    fAux.delete();
                                    fAux = null;
                                    netMapDTO.setForce(false);
                                    netMapDTO.setDataInventario(new java.sql.Date(UtilDatas.getDataHoraAtual().getTime()));
                                } else {
                                    netMapDTO.setForce(true);
                                }
                            }
                        } catch (final Exception e) {
                            e.printStackTrace();
                        } finally {
                            serviceItem = null;
                        }
                    }
                    serviceItem = null;
                }
            }
        } else {
            MonitoraAtivosDiscovery.MENSAGEM_PROCESSAMENTO = "mostrarStatusInventario.oAtivo" + "#<b>" + nameOrIp + "</b>#"
                    + "mostrarStatusInventario.jaTemInventarioCapturadoParaHoje";
            netMapDTO.setForce(false);
        }
        fXX = null;
        finish = true;
    }

    private boolean geraInventarioAtivoSNMP(final String nomeHostIP, final String dataAtualInv) {
        try {
            final String xml = SNMPManager.getSNMPXML(nomeHostIP, MonitoraAtivosDiscovery.PORTA_SNMP, null);
            if (xml != null && !xml.trim().equalsIgnoreCase("")) {
                UtilTratamentoArquivos.gravaArquivoCharSetISO(MonitoraAtivosDiscovery.DIRETORIO_ARQUIVOS_INVENTARIO + "CITSMART_INVENTORY_" + nomeHostIP + "_"
                        + dataAtualInv + ".XML", xml);
                return true;
            }
            return false;
        } catch (final Exception e) {
            return false;
        }
    }

    private boolean geraInventarioAtivoAgenteLocal(final String nomeHostIPServer, final String dataAtualInv) {
        return this.geraInventarioAtivo(nomeHostIPServer, "localhost", nomeHostIPServer, dataAtualInv);
    }

    public boolean geraInventarioAtivo(final String nomeHostIPServer, final String nomeHostIPInventario, final String nomeGerarFile, final String dataAtualInv) {
        boolean retorno = false;

        try {
            echoSocket = new Socket(nomeHostIPServer, MonitoraAtivosDiscovery.PORTA_AGENTE_DOTNET);

            // corretiva 162506 - Esta alteração corrige problema de retorno de
            // Caracteres não convertidos para o CharSet correto.
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(echoSocket.getOutputStream(), "UTF-8")), true);

            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream(), "UTF-8"));

        } catch (final UnknownHostException e) {
            // System.err.println("Don't know about host: localhost.");
            try {
                if (out != null) {
                    out.close();
                }
            } catch (final Exception eX1) {}
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final Exception eX1) {}
            try {
                // Encerra o echoSocket
                if (echoSocket != null && !echoSocket.isClosed()) {
                    echoSocket.close();
                }
            } catch (final IOException eZ) {
                System.out.println("Problema ao encerrar o socket: " + eZ);
                e.printStackTrace();
            }
            echoSocket = null;
            out = null;
            in = null;
            return false;
        } catch (final IOException e) {
            // System.err.println("Couldn't get I/O for the connection to: localhost.");
            try {
                if (out != null) {
                    out.close();
                }
            } catch (final Exception eX1) {}
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final Exception eX1) {}
            try {
                // Encerra o echoSocket
                if (echoSocket != null && !echoSocket.isClosed()) {
                    echoSocket.close();
                }
            } catch (final IOException eZ) {
                System.out.println("Problema ao encerrar o socket: " + eZ);
                e.printStackTrace();
            }
            echoSocket = null;
            out = null;
            in = null;
            return false;
        }

        try {
            out.println("GETINVENT:" + nomeHostIPInventario + "\n");
        } catch (final Exception e) {
            try {
                // Encerra o echoSocket
                if (echoSocket != null && !echoSocket.isClosed()) {
                    echoSocket.close();
                }
            } catch (final IOException eZ) {
                System.out.println("Problema ao encerrar o socket: " + eZ);
                e.printStackTrace();
            }
        }
        // out.println("INVENTORY:" + nomeHostIPInventario + "\n");
        String answer = "";
        final List lst = new ArrayList<>();
        try {
            answer = in.readLine();
            if (answer.trim().equalsIgnoreCase("NOK")) {
                out.println("INVENTORY:" + nomeHostIPInventario + "\n");
            } else {
                lst.add(answer);
            }
        } catch (final Exception e) {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (final Exception eX1) {}
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final Exception eX1) {}
            try {
                // Encerra o echoSocket
                if (echoSocket != null && !echoSocket.isClosed()) {
                    echoSocket.close();
                }
            } catch (final IOException eX) {
                System.out.println("Problema ao encerrar o socket: " + e);
                eX.printStackTrace();
            }
            echoSocket = null;
            out = null;
            in = null;
            return false;
        }
        while (true) {
            try {
                if (finish) {
                    break;
                }
                answer = in.readLine();
                if (answer == null) {
                    break;
                }
                if (answer.trim().equalsIgnoreCase("NOK")) {
                    break;
                }
                lst.add(answer);
                // System.out.println(answer);
                if (answer.trim().equalsIgnoreCase("</CONTENT>")) {
                    lst.add("</REQUEST>");
                    break;
                }
            } catch (final Exception e) {
                break;
            }
        }
        if (!finish) {
            try {
                if (lst != null && lst.size() > 0) {
                    UtilTratamentoArquivos.gravaArquivoCharSetISO(MonitoraAtivosDiscovery.DIRETORIO_ARQUIVOS_INVENTARIO + "CITSMART_INVENTORY_" + nomeGerarFile
                            + "_" + dataAtualInv + ".XML", lst);
                    retorno = true;
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out.println("<EOF>\n");
        } catch (final Exception e) {}
        try {
            out.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        try {
            echoSocket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        out = null;
        in = null;
        echoSocket = null;

        return retorno;
    }

    public NetMapDTO getNetMapDTO() {
        return netMapDTO;
    }

    public void setNetMapDTO(final NetMapDTO netMapDTO) {
        this.netMapDTO = netMapDTO;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(final boolean finish) {
        this.finish = finish;
    }

    public boolean isBreakThread() {
        return breakThread;
    }

    public void setBreakThread(final boolean breakThread) {
        this.breakThread = breakThread;
    }

    @Override
    public void interrupt() {
        finish = true;
        try {
            if (out != null) {
                out.close();
            }
        } catch (final Exception eX1) {}
        try {
            if (in != null) {
                in.close();
            }
        } catch (final Exception eX1) {}
        try {
            // Encerra o echoSocket
            if (echoSocket != null && !echoSocket.isClosed()) {
                echoSocket.close();
            }
        } catch (final IOException eZ) {}
        echoSocket = null;
        out = null;
        in = null;
        super.interrupt();
    }

}
