/**
 * CentralIT - CITSmart
 *
 */
package br.com.centralit.citcorpore.integracao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RelatorioValorServicoContratoDTO;
import br.com.centralit.citcorpore.bean.ValoresServicoContratoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ValoresServicoContratoDao extends CrudDaoDefaultImpl {

    public ValoresServicoContratoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idValorServicoContrato", "idValorServicoContrato", true, true, false, false));
        listFields.add(new Field("idServicoContrato", "idServicoContrato", false, false, false, false));
        listFields.add(new Field("valorServico", "valorServico", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ValorServicoContrato";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ValoresServicoContratoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdServicoContrato(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", parm));
        ordenacao.add(new Order("idValorServicoContrato"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Retorna Lita de Valores Servico Contrato Ativas
     * idServicoContrato.
     *
     * @param idServicoContrato
     * @return valoresServicoContrato
     * @throws Exception
     */
    public Collection obterValoresAtivosPorIdServicoContrato(final Integer idServicoContrato) throws Exception {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final String sql = "SELECT idValorServicoContrato,idServicoContrato,valorServico,dataInicio,dataFim  FROM " + this.getTableName()
                + " WHERE idServicoContrato  = ? ORDER BY idValorServicoContrato DESC";
        parametro.add(idServicoContrato);
        list = this.execSQL(sql, parametro.toArray());
        final List<String> listRetorno = new ArrayList<String>();
        listRetorno.add("idValorServicoContrato");
        listRetorno.add("idServicoContrato");
        listRetorno.add("valorServico");
        listRetorno.add("dataInicio");
        listRetorno.add("dataFim");
        final Collection result = engine.listConvertion(this.getBean(), list, listRetorno);
        return result;
    }

    public void deleteByIdServicoContrato(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", parm));
        super.deleteByCondition(condicao);
    }

    public boolean existeAtivos(final Integer idServicoContrato) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", idServicoContrato));
        condicao.add(new Condition("dataFim", "is", null));
        ordenacao.add(new Order("idValorServicoContrato"));
        final Collection var = super.findByCondition(condicao, ordenacao);
        if (var == null || var.isEmpty()) {
            return false;
        }
        return true;
    }

    public Collection<RelatorioValorServicoContratoDTO> listaValoresServicoContrato(final ValoresServicoContratoDTO valoresServicoContratoDTO) throws Exception {
        List<RelatorioValorServicoContratoDTO> listaQuantidadeSolicitacaoServico = null;

        if (valoresServicoContratoDTO != null && valoresServicoContratoDTO.getIdContrato() != null && valoresServicoContratoDTO.getDataInicio() != null
                && valoresServicoContratoDTO.getDataFim() != null) {

            final StringBuilder sql = new StringBuilder();
            final List parametro = new ArrayList<>();
            final List listRetorno = new ArrayList<>();

            sql.append("select ");
            sql.append("s.nomeservico, vsc.idvalorservicocontrato, vsc.valorServico, ");
            sql.append("vsc.datainicio, vsc.datafim ");
            sql.append("from ");
            sql.append("solicitacaoservico ss ");
            sql.append("inner join ");
            sql.append("servicocontrato sc on sc.idservicocontrato = ss.idservicocontrato ");
            sql.append("inner join ");
            sql.append("valorservicocontrato vsc on vsc.idservicocontrato = sc.idservicocontrato ");
            sql.append("inner join ");
            sql.append("contratos c on c.idcontrato = sc.idcontrato ");
            sql.append("inner join ");
            sql.append("servico s on s.idservico = sc.idservico ");
            sql.append("where ");

            sql.append("c.idcontrato = ? and ");
            parametro.add(valoresServicoContratoDTO.getIdContrato());

            if (valoresServicoContratoDTO.getIdServico() != null && !valoresServicoContratoDTO.getIdServico().equals("")) {
                sql.append("s.idservico = ? and ");
                parametro.add(valoresServicoContratoDTO.getIdServico());
            }

            sql.append("ss.datahorainicio between ? and ? and ");
            sql.append("UPPER(ss.situacao) = UPPER('Fechada') ");
            sql.append("group by ");
            sql.append("s.nomeServico, ");
            sql.append("vsc.valorServico, ");
            sql.append("vsc.idValorServicoContrato, ");
            sql.append("vsc.datainicio, ");
            sql.append("vsc.datafim ");

            parametro.add(valoresServicoContratoDTO.getDataInicio());

            parametro.add(this.transformaHoraFinal(valoresServicoContratoDTO.getDataFim()));

            // if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.ORACLE) ) {
            // parametro.add(valoresServicoContratoDTO.getDataInicio() );
            // } else {
            // parametro.add(valoresServicoContratoDTO.getDataFim() + " 23:59:59 ");
            // }
            //
            // if (valoresServicoContratoDTO.getIdContrato() != null) {
            // parametro.add(valoresServicoContratoDTO.getIdContrato() );
            // }
            //
            // if (valoresServicoContratoDTO.getIdServico() != null) {
            // sql.append(" servicocontrato.idservico = ? ");
            // parametro.add(valoresServicoContratoDTO.getIdServico() );
            // }

            final List list = this.execSQL(sql.toString(), parametro.toArray());

            listRetorno.add("nomeServico");
            listRetorno.add("idServicoContrato");
            listRetorno.add("valorServico");
            listRetorno.add("dataInicio");
            listRetorno.add("dataFim");

            if (list != null && !list.isEmpty()) {
                listaQuantidadeSolicitacaoServico = this.listConvertion(RelatorioValorServicoContratoDTO.class, list, listRetorno);
                return listaQuantidadeSolicitacaoServico;
            }
        }
        return listaQuantidadeSolicitacaoServico;
    }

    private Timestamp transformaHoraFinal(final Date data) throws ParseException {
        final String dataHora = data + " 23:59:59";
        final String pattern = "yyyy-MM-dd hh:mm:ss";
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        final java.util.Date d = sdf.parse(dataHora);
        final java.sql.Timestamp sqlDate = new java.sql.Timestamp(d.getTime());
        return sqlDate;
    }

    /**
     * @param idServicoContrato
     * @param data
     * @throws PersistenceException
     * @author cledson.junior
     */
    public void updateValoresServicoContrato(final Integer idServicoContrato, final Date data) throws PersistenceException {
        final List parametros = new ArrayList<>();
        if (data != null) {
            parametros.add(data);
        } else {
            parametros.add(null);
        }
        parametros.add(idServicoContrato);
        final String sql = "UPDATE " + this.getTableName() + " SET datafim = ? WHERE idServicoContrato = ?";
        this.execUpdate(sql, parametros.toArray());
    }

}
