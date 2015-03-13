package br.com.citframework.integracao;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.centralit.citcorpore.bean.ContratosGruposDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.negocio.ContratosGruposService;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.citframework.dto.LookupDTO;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Campo;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.LookupFieldUtil;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilFormatacao;

public class LookupProcessContrato extends LookupProcessDefaultDao {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List processLookup(final LookupDTO lookupObject) throws LogicException, Exception {
        String sql = "";
        // Collection colRetorno = new ArrayList<>();

        final LookupFieldUtil lookUpField = new LookupFieldUtil();
        final Collection colCamposRet = lookUpField.getCamposRetorno(lookupObject.getNomeLookup());
        Iterator itRet = colCamposRet.iterator();
        Campo cp;
        while (itRet.hasNext()) {
            cp = (Campo) itRet.next();
            if (!sql.equalsIgnoreCase("")) {
                sql = sql + ",";
            }
            sql = sql + cp.getNomeFisico();
        }

        sql = "SELECT " + sql;
        sql += "  FROM CONTRATOS PRJ INNER JOIN CLIENTES CLI on CLI.idCliente = PRJ.idCliente INNER JOIN Fornecedor FORN on FORN.idFornecedor = PRJ.idFornecedor ";
        String where = " (PRJ.deleted IS NULL or PRJ.deleted = 'N')";

        final ContratosGruposService contratosGruposService = (ContratosGruposService) ServiceLocator.getInstance().getService(ContratosGruposService.class,
                null);

        String COLABORADORES_VINC_CONTRATOS = ParametroUtil.getValorParametroCitSmartHashMap(
                br.com.centralit.citcorpore.util.Enumerados.ParametroSistema.COLABORADORES_VINC_CONTRATOS, "N");
        if (COLABORADORES_VINC_CONTRATOS == null) {
            COLABORADORES_VINC_CONTRATOS = "N";
        }
        if (COLABORADORES_VINC_CONTRATOS.equalsIgnoreCase("S")) {
            where += " AND PRJ.idContrato in (-1";
            final UsuarioDTO usuarioDto = (UsuarioDTO) lookupObject.getUser();
            if (usuarioDto != null) {
                final Collection<ContratosGruposDTO> colContratosColab = contratosGruposService.findByIdEmpregado(usuarioDto.getIdEmpregado());
                if (colContratosColab != null) {
                    for (final ContratosGruposDTO contratosGruposDto : colContratosColab) {
                        where += "," + contratosGruposDto.getIdContrato();
                    }
                }
            }
            where += ")";
        }

        final Collection colCamposPesq = lookUpField.getCamposPesquisa(lookupObject.getNomeLookup());
        final Iterator itPesq = colCamposPesq.iterator();
        String obj = null;
        int count = 1;
        while (itPesq.hasNext()) {
            cp = (Campo) itPesq.next();
            obj = null;
            obj = this.getValueParmLookup(lookupObject, count);
            if (obj != null) {
                final String[] trataGetNomeFisico = cp.getNomeFisico().split("\\.");
                String nomeFisico = cp.getNomeFisico();
                if (trataGetNomeFisico.length > 1) {
                    cp.setNomeFisico(trataGetNomeFisico[1]);
                    nomeFisico = trataGetNomeFisico[0] + "." + trataGetNomeFisico[1];
                }
                if (!obj.equalsIgnoreCase("")) {
                    if (!where.equalsIgnoreCase("")) {
                        where = where + " AND ";
                    }
                    if (cp.getType().equalsIgnoreCase(Constantes.getValue("FIELDTYPE_TEXT").trim())
                            || cp.getType().equalsIgnoreCase(Constantes.getValue("FIELDTYPE_TEXTAREA").trim())) {
                        final String func = Constantes.getValue("FUNCAO_CONVERTE_MAIUSCULO");
                        if (func != null && !func.trim().equalsIgnoreCase("")) {
                            where = where + func + "(" + nomeFisico + ")";
                        } else {
                            where = where + cp.getNomeFisico();
                        }
                        where = where + " LIKE '%";
                    } else {
                        if (cp.getType().equalsIgnoreCase(Constantes.getValue("FIELDTYPE_COMBO").trim())) {
                            where = where + cp.getNomeFisico();
                            where = where + " IN (";
                        } else if (cp.getType().equalsIgnoreCase("DATE")) {
                            where = where + cp.getNomeFisico();
                            where = where + " = '";
                        } else {
                            where = where + nomeFisico;
                            where = where + " = ";
                        }
                    }
                    if (cp.isSomenteBusca()) {
                        obj = obj.trim();
                        obj = obj.toUpperCase();
                        obj = Normalizer.normalize(obj, Normalizer.Form.NFD);
                        obj = obj.replaceAll("[^\\p{ASCII}]", "");
                    }

                    where = where + obj;
                    if (cp.getType().equalsIgnoreCase(Constantes.getValue("FIELDTYPE_TEXT").trim())
                            || cp.getType().equalsIgnoreCase(Constantes.getValue("FIELDTYPE_TEXTAREA").trim())) {
                        where = where + "%'";
                    } else if (cp.getType().equalsIgnoreCase("DATE")) {
                        where = where + "'";
                    } else if (cp.getType().equalsIgnoreCase(Constantes.getValue("FIELDTYPE_COMBO").trim())) {
                        where = where + ")";
                    }
                }
            }
            count++;
        }

        String strAux;
        if (!where.equalsIgnoreCase("")) {
            sql = sql + " WHERE " + where;
            strAux = lookUpField.getWhere(lookupObject.getNomeLookup());
            if (!strAux.equalsIgnoreCase("")) {
                sql = sql + " AND ";
                sql = sql + strAux;
            }
        } else {
            strAux = lookUpField.getWhere(lookupObject.getNomeLookup());
            if (!strAux.equalsIgnoreCase("")) {
                sql = sql + " WHERE " + strAux;
            }
        }

        final Collection colCamposOrd = lookUpField.getCamposOrdenacao(lookupObject.getNomeLookup());
        final Iterator itOrd = colCamposOrd.iterator();
        String ordem = "";
        while (itOrd.hasNext()) {
            cp = (Campo) itOrd.next();
            if (!ordem.equalsIgnoreCase("")) {
                ordem = ordem + ",";
            }
            ordem = ordem + cp.getNomeFisico();
        }

        if (!ordem.equalsIgnoreCase("")) {
            sql = sql + " ORDER BY " + ordem;
        }

        sql = sql.toUpperCase();
        final List lista = this.execSQL(sql, null);
        if (lista == null || lista.size() == 0) {
            final TransactionControler tc = this.getTransactionControler();
            if (tc != null) {
                tc.close();
            }

            return null;
        }

        // Processa o resultado.
        final List result = new ArrayList<>();
        if (lista == null || lista.size() == 0) {
            final TransactionControler tc = this.getTransactionControler();
            if (tc != null) {
                tc.close();
            }

            return result;
        }
        if (lista.size() > 400) {
            final TransactionControler tc = this.getTransactionControler();
            if (tc != null) {
                tc.close();
            }

            throw new LogicException("citcorpore.comum.consultaEstourouLimite");
        }
        final Iterator it = lista.iterator();
        Campo campoAux;
        int i;
        Collection colAux;
        Object auxObj;
        while (it.hasNext()) {
            final Object[] row = (Object[]) it.next();
            itRet = colCamposRet.iterator();
            i = 0;
            campoAux = null;
            colAux = new ArrayList<>();
            while (itRet.hasNext()) {
                cp = (Campo) itRet.next();
                campoAux = new Campo(cp.getNomeFisico(), cp.getDescricao(), cp.isObrigatorio(), cp.getType(), cp.getTamanho());
                if (cp.getType().equalsIgnoreCase(Constantes.getValue("FIELDTYPE_TEXT").trim())
                        || cp.getType().equalsIgnoreCase(Constantes.getValue("FIELDTYPE_TEXTAREA").trim())) {
                    if (row[i] == null) {
                        auxObj = new String("");
                    } else {
                        final String str = new String(row[i].toString());
                        auxObj = str.replaceAll("\"", "&quot;").replaceAll("'", "&#180;");

                        /*
                         * alteracao feita por Cleon, pois ao tentar restaurar através de uma lookup um elemento textarea com quebra de linha, o setretorno nao
                         * estava comportando de forma correta
                         * disparando um erro
                         */
                        auxObj = str.replaceAll("\n", " ");
                    }
                    campoAux.setObjValue(auxObj);
                } else if (cp.getType().equalsIgnoreCase(Constantes.getValue("FIELDTYPE_DATE").trim())) {
                    if (row[i] == null) {
                        campoAux.setObjValue(null);
                    } else {
                        auxObj = row[i];
                        if (auxObj instanceof java.sql.Date) {
                            campoAux.setObjValue(UtilDatas.dateToSTR((java.sql.Date) auxObj));
                        } else if (auxObj instanceof java.sql.Timestamp) {
                            campoAux.setObjValue(UtilDatas.dateToSTR((java.sql.Timestamp) auxObj));
                        } else {
                            campoAux.setObjValue(auxObj.toString());
                        }
                    }
                } else if (cp.getType().equalsIgnoreCase(Constantes.getValue("FIELDTYPE_MOEDA").trim())) {
                    if (row[i] == null) {
                        campoAux.setObjValue(null);
                    } else {
                        auxObj = row[i];
                        String valorTransf = null;
                        if (auxObj instanceof Double) {
                            valorTransf = UtilFormatacao.formatBigDecimal(new BigDecimal(((Double) auxObj).doubleValue()), 2);
                        } else if (auxObj instanceof BigDecimal) {
                            valorTransf = UtilFormatacao.formatBigDecimal((BigDecimal) auxObj, 2);
                        } else {
                            valorTransf = auxObj.toString();
                        }
                        campoAux.setObjValue(valorTransf);
                    }
                }
                colAux.add(campoAux);
                i++;
            }
            result.add(colAux);
        }

        final TransactionControler tc = this.getTransactionControler();
        if (tc != null) {
            tc.close();
        }

        return result;
    }
}
