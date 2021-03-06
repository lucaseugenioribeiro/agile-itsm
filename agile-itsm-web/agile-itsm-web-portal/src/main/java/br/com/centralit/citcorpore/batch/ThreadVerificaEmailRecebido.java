package br.com.centralit.citcorpore.batch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import microsoft.exchange.webservices.data.ConflictResolutionMode;
import microsoft.exchange.webservices.data.EWSHttpException;
import microsoft.exchange.webservices.data.EmailMessage;
import microsoft.exchange.webservices.data.Item;
import microsoft.exchange.webservices.data.ItemId;
import microsoft.exchange.webservices.data.ServiceLocalException;
import net.htmlparser.jericho.Source;
import br.com.centralit.citcorpore.bean.OcorrenciaSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.integracao.ClienteEmailCentralServicoDao;
import br.com.centralit.citcorpore.negocio.OcorrenciaSolicitacaoService;
import br.com.centralit.citcorpore.negocio.SolicitacaoServicoService;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.UtilDatas;

/**
 *
 * @author geber.costa
 *         1 )Essa Thread verifica os emails recebidos, se existir um ou varios emails novos a thread os grava em uma ocorrencia,
 *         e essa ocorrencia é gravada em uma solicitaca.
 *
 *         2) A solicitação é alterada é a que o número estiver no titulo do email.
 *
 *         3) Para a Thread funcionar, é necessário ter no mínimo a versão 7 do java, e o parametro de HABILITA_ROTINA_DE_LEITURA_EMAIL estar setada como 'S',
 *         por padrão o parametro vem setado como 'N'
 *
 *         4)
 *
 */

public class ThreadVerificaEmailRecebido extends Thread {

    private boolean threadAtiva = true;

    @Override
    public void run() {
        while (threadAtiva) {

            try {
                final String parametro = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.HABILITA_ROTINA_DE_LEITURA_EMAIL, "");
                final String versaoJava = System.getProperty("java.version");
                // "1.7.0_40"
                if (parametro.equalsIgnoreCase("") || parametro.equalsIgnoreCase("N")) {
                    threadAtiva = false;
                } else if (parametro.equalsIgnoreCase("S")) {

                    if (versaoJava.charAt(2) >= 7 || versaoJava.charAt(0) > 1) {
                        sleep(1000);
                        System.out.println("###########################################");
                        System.out.println("VERSÃO DO JAVA : " + versaoJava);
                        System.out.println("###########################################");

                        // 0 - Verificar se é pop ou imap
                        // 1 - Ler emails novos
                        // 2 - Verificar o titulo (cada titulo com um numero, corresponde a solicitacao)
                        // 3 - Pegar o numero da solicitacao(esta no titulo) , criar uma ocorrencia e gravar ela nessa solicitacao. (Tentar ajax para ficar mais
                        // dinamico).
                        verificarEmail();

                    } else {
                        System.out.println("###########################################");
                        System.out.println("É necessário possuir pelo menos a versão 7 do java para funcionar o recebimento de emails pelo sistema");
                        System.out.println("###########################################");
                        threadAtiva = false;
                    }
                }
            } catch (final InterruptedException e1) {
                System.out.println("###########################################");
                System.out.println("Erro ao iniciar a Thread");
                System.out.println("###########################################");
                e1.printStackTrace();
            } catch (final MessagingException e) {
                e.printStackTrace();
            } catch (final LogicException e) {
                e.printStackTrace();
            } catch (final ServiceLocalException e) {
                e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void verificarEmail() throws Exception {
        final String protocolo = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.SMTP_LEITURA_Provider, "");
        if (protocolo.equalsIgnoreCase("pop") || protocolo.equalsIgnoreCase("pop3")) {
            receberEmailPop();
        } else if (protocolo.equalsIgnoreCase("imap")) {
            receberEmailImap();
        }
    }

    public static void receberEmailPop() {

        try {

            System.out.println("LENDO EMAILS POP...");

            // Verifica qual tipo de provider, se é imap , ou pop/pop3

            final ClienteEmailCentralServicoDao clienteEmail = new ClienteEmailCentralServicoDao();
            final Message mensagens[] = clienteEmail.getEmail2s();

            MimeMultipart mmp = new MimeMultipart();
            MimeBodyPart mbp1 = new MimeBodyPart();
            new MimeBodyPart();

            int contador = 0;
            for (final Message message : mensagens) {
                if (!message.isSet(Flags.Flag.SEEN)) {

                    String solicitacao = message.getSubject();

                    boolean encontrar = solicitacao.matches(".*Solicitação.*");
                    /**
                     * Verifica se no título do email existe a palavra Solicitação e o número, caso exista, ele vai vincular o email a solicitacao do titulo,
                     */
                    if (encontrar) {

                        encontrar = solicitacao.matches(".*[0-9].*");
                        /**
                         * Verifica se no título do email possui valor numérico, se possuir, prossegue, senão, encerra
                         */
                        if (encontrar) {

                            /**
                             * Retira tudo o que não for numérico, pega os numeros e usa como id da solicitacao de servico
                             */
                            solicitacao = solicitacao.replaceAll("[^0-9]", "");

                            mmp = (MimeMultipart) message.getContent();
                            mbp1 = (MimeBodyPart) mmp.getBodyPart(0);

                            System.out.println("-----------------------------------------------------------------------");
                            System.out.println("CONTEÚDO DO EMAIL :" + mbp1.getContent());
                            System.out.println("TITULO DO EMAIL : " + message.getSubject());
                            System.out.println("-----------------------------------------------------------------------");
                            System.out.println("Contador : " + contador);
                            contador++;

                            final OcorrenciaSolicitacaoDTO ocorrenciaDto = new OcorrenciaSolicitacaoDTO();
                            final OcorrenciaSolicitacaoService ocorrenciaService = (OcorrenciaSolicitacaoService) ServiceLocator.getInstance().getService(
                                    OcorrenciaSolicitacaoService.class, null);
                            ocorrenciaDto.setIdSolicitacaoServico(Integer.parseInt(solicitacao));
                            final Calendar calendar = Calendar.getInstance();
                            final int hora = calendar.get(Calendar.HOUR_OF_DAY);
                            final int minuto = calendar.get(Calendar.MINUTE);
                            ocorrenciaDto.setDataregistro(UtilDatas.getSqlDate(new java.util.Date()));
                            if (minuto < 10) {
                                ocorrenciaDto.setHoraregistro(hora + ":" + minuto);
                            } else {
                                ocorrenciaDto.setHoraregistro(hora + ":" + minuto);
                            }

                            ocorrenciaDto.setRegistradopor("Email - Automatico");
                            ocorrenciaDto.setDescricao(message.getSubject().toString());
                            ocorrenciaDto.setOcorrencia(mbp1.getContent().toString());
                            new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                            UtilDatas.getSqlDate(message.getReceivedDate());
                            ocorrenciaDto.setDataregistro(UtilDatas.getSqlDate(new java.util.Date()));
                            UtilDatas.formatHoraFormatadaHHMMSSStr(message.getReceivedDate());
                            ocorrenciaService.create(ocorrenciaDto);
                        }
                    }
                }
            }

            System.out.println("THREAD FINALIZADA!!!---------------------");

        } catch (final Exception e) {
            System.out.println("###########################################");
            System.out.println("Erro ao executar a Thread");
            System.out.println("###########################################");
            e.printStackTrace();
        }
    }

    public static void receberEmailImap() throws Exception {
        ArrayList<Item> results = null;
        EmailMessage email = null;

        final ClienteEmailCentralServicoDao clienteEmailCentralServicoDao = new ClienteEmailCentralServicoDao();
        results = clienteEmailCentralServicoDao.getMails();

        System.out.println("IMAP... LENDO EMAILS");

        try {
            for (final Item item : results) {
                final ItemId itemId = item.getId();
                email = EmailMessage.bind(item.getService(), itemId);

                final String texto = email.getBody().toString();
                final Source source = new Source(email.getBody().toString());
                source.getTextExtractor().toString();
                System.out.println("Mensagem : \n" + texto + " \nFIM da Mensagem ------------");
                DateFormat.getTimeInstance();
                final Locale br = new Locale("pt", "BR");
                new SimpleDateFormat("hh:mm", br);
                String solicitacao = email.getSubject();
                email.getBody().toString();

                boolean encontrar = solicitacao.matches(".*Solicitação.*");
                if (encontrar) {
                    encontrar = solicitacao.matches(".*[0-9].*");
                    if (encontrar) {
                        solicitacao = solicitacao.replaceAll("[^0-9]", "");

                        final SolicitacaoServicoService solicitacaoServicoService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(
                                SolicitacaoServicoService.class, null);
                        final SolicitacaoServicoDTO solicitacaoDto = new SolicitacaoServicoDTO();
                        solicitacaoDto.setIdSolicitacaoServico(Integer.parseInt(solicitacao));

                        final boolean existeSolicitacao = solicitacaoServicoService.existeSolicitacaoServico(solicitacaoDto);
                        if (existeSolicitacao) {
                            final OcorrenciaSolicitacaoDTO ocorrenciaDto = new OcorrenciaSolicitacaoDTO();
                            final OcorrenciaSolicitacaoService ocorrenciaService = (OcorrenciaSolicitacaoService) ServiceLocator.getInstance().getService(
                                    OcorrenciaSolicitacaoService.class, null);
                            ocorrenciaDto.setIdSolicitacaoServico(Integer.parseInt(solicitacao));

                            final Calendar calendar = Calendar.getInstance();
                            final int hora = calendar.get(Calendar.HOUR_OF_DAY);
                            final int minuto = calendar.get(Calendar.MINUTE);
                            ocorrenciaDto.setDataregistro(UtilDatas.getSqlDate(new java.util.Date()));
                            ocorrenciaDto.setHoraregistro(hora + ":" + minuto);
                            ocorrenciaDto.setRegistradopor("Email - Automatico");
                            ocorrenciaDto.setDescricao(email.getSubject());
                            ocorrenciaDto.setOcorrencia(texto);
                            ocorrenciaService.create(ocorrenciaDto);
                        }
                    }
                }
                email.setIsRead(true);
                email.update(ConflictResolutionMode.AlwaysOverwrite);
            }
        } catch (final EWSHttpException ex) {
            System.out.println("Teste");
        }

        System.out.println("LEITURA CONCLUIDA");

        try {

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
