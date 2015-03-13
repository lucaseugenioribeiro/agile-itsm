package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.OcorrenciaProblemaDTO;
import br.com.centralit.citcorpore.bean.ProblemaDTO;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.SQLConfig;

/**
 *
 * @author geber.costa
 *
 *
 */
public class OcorrenciaProblemaDAO extends CrudDaoDefaultImpl {

    public OcorrenciaProblemaDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idocorrencia", "idOcorrencia", true, true, false, false));
        listFields.add(new Field("idproblema", "idProblema", false, false, false, false));
        listFields.add(new Field("iditemtrabalho", "idItemTrabalho", false, false, false, false));
        listFields.add(new Field("idjustificativa", "idJustificativa", false, false, false, false));
        listFields.add(new Field("datainicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));
        listFields.add(new Field("categoria", "categoria", false, false, false, false));
        listFields.add(new Field("origem", "origem", false, false, false, false));
        listFields.add(new Field("descricao", "descricao", false, false, false, false));
        listFields.add(new Field("ocorrencia", "ocorrencia", false, false, false, false));
        listFields.add(new Field("informacoescontato", "informacoesContato", false, false, false, false));
        listFields.add(new Field("tempogasto", "tempoGasto", false, false, false, false));
        listFields.add(new Field("dataregistro", "dataregistro", false, false, false, false));
        listFields.add(new Field("horaregistro", "horaregistro", false, false, false, false));
        listFields.add(new Field("registradopor", "registradopor", false, false, false, false));
        listFields.add(new Field("complementojustificativa", "complementoJustificativa", false, false, false, false));
        listFields.add(new Field("dadosproblema", "dadosProblema", false, false, false, false));
        listFields.add(new Field("idcategoriaocorrencia", "idCategoriaOcorrencia", false, false, false, false));
        listFields.add(new Field("idorigemocorrencia", "idOrigemOcorrencia", false, false, false, false));
        return listFields;
    }

    public Collection findByIdProblema(final Integer idProblema) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idProblema", "=", idProblema));
        ordenacao.add(new Order("dataregistro"));
        ordenacao.add(new Order("idOcorrencia"));
        return super.findByCondition(condicao, ordenacao);
    }

    @Override
    public String getTableName() {
        return "ocorrenciaproblema";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return OcorrenciaProblemaDTO.class;
    }

    public Collection<OcorrenciaProblemaDTO> listByIdProblemaEPorUltimaData(final Integer idProblema) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idProblema", "=", idProblema));
        ordenacao.add(new Order("dataregistro"));
        ordenacao.add(new Order("idOcorrencia"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection<OcorrenciaProblemaDTO> listByUltimaDataEHora(final Integer idProblema) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idProblema", "=", idProblema));

        return super.findByCondition(condicao, ordenacao);
    }

    public Collection<OcorrenciaProblemaDTO> listByUltimaDataEHora(final ProblemaDTO problemaDTO) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idProblema", "=", problemaDTO.getIdProblema()));

        return super.findByCondition(condicao, ordenacao);
    }

    public Collection<OcorrenciaProblemaDTO> listByIdProblemaAndCategoria(final Integer idProblema, final CategoriaOcorrencia categoria)
            throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idProblema", "=", idProblema));
        condicao.add(new Condition("categoria", "=", categoria.name()));
        ordenacao.add(new Order("dataregistro"));
        ordenacao.add(new Order("idOcorrencia"));
        return super.findByCondition(condicao, ordenacao);
    }

    public OcorrenciaProblemaDTO listUltimaDataHora(final int idProblema) throws PersistenceException {

        final OcorrenciaProblemaDTO ocorrenciaProblemaDTO = null;

        if (idProblema > 0) {

            final StringBuilder sql = new StringBuilder();

            final List parametro = new ArrayList<>();

            final List listRetorno = new ArrayList<String>();

            List<OcorrenciaProblemaDTO> listaOcorrenciasProblemaDTO = null;

            // Montando a instrução SQL.
            sql.append("SELECT dataregistro, MAX(horaregistro) FROM OCORRENCIAPROBLEMA WHERE dataregistro IN (");

            sql.append("SELECT MAX(dataregistro) FROM OCORRENCIAPROBLEMA WHERE idproblema = ?");

            sql.append(")");

            // Adicionando o parametro.
            parametro.add(idProblema);

            // Especificando as colunas cujo os valores serão retornados.
            listRetorno.add("dataregistro");

            listRetorno.add("horaregistro");

            listaOcorrenciasProblemaDTO = this.execSQL(sql.toString(), parametro.toArray());

            if (listaOcorrenciasProblemaDTO != null && !listaOcorrenciasProblemaDTO.isEmpty()) {

                return listaOcorrenciasProblemaDTO.get(0);
            }
        }

        return ocorrenciaProblemaDTO;
    }

    public OcorrenciaProblemaDTO findUltimoByIdSolicitacaoServico(final Integer idProblema) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();

        if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.POSTGRESQL) || CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.MYSQL)) {
            sql.append("select o.idocorrencia, o.idjustificativa, o.idproblema, o.iditemtrabalho, o.dataregistro, o.horaregistro, o.registradopor, "
                    + " o.descricao, o.datainicio, o.datafim, o.complementojustificativa, o.informacoescontato, "
                    + " o.categoria, o.origem, o.tempogasto, o.ocorrencia, o.idcategoriaocorrencia, o.idorigemocorrencia " + " from ocorrenciaproblema o "
                    + " where o.idproblema = ? " + " order by o.idocorrencia desc limit 1 ");

        } else if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.ORACLE)) {

            sql.append("select o.idocorrencia, o.idjustificativa, o.idproblema, o.iditemtrabalho, o.dataregistro, o.horaregistro, o.registradopor, "
                    + " o.descricao, o.datainicio, o.datafim, o.complementojustificativa, o.informacoescontato, "
                    + " o.categoria, o.origem, o.tempogasto, o.ocorrencia, o.idcategoriaocorrencia, o.idorigemocorrencia " + " from ocorrenciaproblema o "
                    + " where o.idproblema = ? and rownum = 1 " + " order by o.idocorrencia desc  ");

        } else if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.SQLSERVER)) {

            sql.append("select top 1 o.idocorrencia, o.idjustificativa, o.idproblema, o.iditemtrabalho, o.dataregistro, o.horaregistro, o.registradopor, "
                    + " o.descricao, o.datainicio, o.datafim, o.complementojustificativa, o.informacoescontato, "
                    + " o.categoria, o.origem, o.tempogasto, o.ocorrencia, o.idcategoriaocorrencia, o.idorigemocorrencia " + " from ocorrenciaproblema o "
                    + " where o.idproblema = ? " + " order by o.idocorrencia desc  ");
        }

        parametro.add(idProblema);

        list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idOcorrencia");
        listRetorno.add("idJustificativa");
        listRetorno.add("idProblema");
        listRetorno.add("idItemTrabalho");
        listRetorno.add("dataregistro");
        listRetorno.add("horaregistro");
        listRetorno.add("registradopor");
        listRetorno.add("descricao");
        listRetorno.add("dataInicio");
        listRetorno.add("dataFim");
        listRetorno.add("complementoJustificativa");
        listRetorno.add("informacoesContato");
        listRetorno.add("categoria");
        listRetorno.add("origem");
        listRetorno.add("tempoGasto");
        listRetorno.add("ocorrencia");
        listRetorno.add("idCategoriaOcorrencia");
        listRetorno.add("idOrigemOcorrencia");

        if (list != null && !list.isEmpty()) {
            final List result = this.listConvertion(this.getBean(), list, listRetorno);
            return (OcorrenciaProblemaDTO) result.get(0);
        } else {
            return null;
        }
    }

    public OcorrenciaProblemaDTO findUltimoByIdSolicitacaoServicoAndOcorrencia(final Integer idProblema) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();
        final String strOcorr = "Escalação automática";

        final StringBuilder sql = new StringBuilder();
        sql.append("select o.idocorrencia, o.idjustificativa, o.idproblema, o.iditemtrabalho, o.dataregistro, o.horaregistro, o.registradopor, "
                + " o.descricao, o.datainicio, o.datafim, o.complementojustificativa, o.dadossolicitacao, o.informacoescontato, "
                + " o.categoria, o.origem, o.tempogasto, o.ocorrencia, o.idcategoriaocorrencia, o.idorigemocorrencia " + " from ocorrenciaproblema o "
                + " where o.idproblemaa = ? and o.idocorrencia = (select max(idocorrencia) from ocorrenciaproblema where ocorrencia like '%" + strOcorr
                + "%' )" + " order by o.idocorrencia desc");

        parametro.add(idProblema);

        list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idOcorrencia");
        listRetorno.add("idJustificativa");
        listRetorno.add("idProblema");
        listRetorno.add("idItemTrabalho");
        listRetorno.add("dataregistro");
        listRetorno.add("horaregistro");
        listRetorno.add("registradopor");
        listRetorno.add("descricao");
        listRetorno.add("dataInicio");
        listRetorno.add("dataFim");
        listRetorno.add("complementoJustificativa");
        listRetorno.add("dadosSolicitacao");
        listRetorno.add("informacoesContato");
        listRetorno.add("categoria");
        listRetorno.add("origem");
        listRetorno.add("tempoGasto");
        listRetorno.add("ocorrencia");
        listRetorno.add("idCategoriaOcorrencia");
        listRetorno.add("idOrigemOcorrencia");

        if (list != null && !list.isEmpty()) {
            final List result = this.listConvertion(this.getBean(), list, listRetorno);
            return (OcorrenciaProblemaDTO) result.get(0);
        }
        return null;
    }

}
