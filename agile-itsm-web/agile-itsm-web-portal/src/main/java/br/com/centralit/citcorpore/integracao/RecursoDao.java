package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RecursoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class RecursoDao extends CrudDaoDefaultImpl {

    public RecursoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idRecurso", "idRecurso", true, true, false, false));
        listFields.add(new Field("idGrupoRecurso", "idGrupoRecurso", false, false, false, false));
        listFields.add(new Field("idRecursoPai", "idRecursoPai", false, false, false, false));
        listFields.add(new Field("nomeRecurso", "nomeRecurso", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("tipoAtualizacao", "tipoAtualizacao", false, false, false, false));
        listFields.add(new Field("idNagiosConexao", "idNagiosConexao", false, false, false, false));
        listFields.add(new Field("hostName", "hostName", false, false, false, false));
        listFields.add(new Field("serviceName", "serviceName", false, false, false, false));
        listFields.add(new Field("horaInicioFunc", "horaInicioFunc", false, false, false, false));
        listFields.add(new Field("horaFimFunc", "horaFimFunc", false, false, false, false));
        listFields.add(new Field("idCalendario", "idCalendario", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        listFields.add(new Field("statusAberturaInc", "statusAberturaInc", false, false, false, false));
        listFields.add(new Field("idSolicitante", "idSolicitante", false, false, false, false));
        listFields.add(new Field("emailAberturaInc", "emailAberturaInc", false, false, false, false));
        listFields.add(new Field("descricaoAbertInc", "descricaoAbertInc", false, false, false, false));
        listFields.add(new Field("impacto", "impacto", false, false, false, false));
        listFields.add(new Field("urgencia", "urgencia", false, false, false, false));
        listFields.add(new Field("idGrupo", "idGrupo", false, false, false, false));
        listFields.add(new Field("idOrigem", "idOrigem", false, false, false, false));
        listFields.add(new Field("idServicoContrato", "idServicoContrato", false, false, false, false));
        listFields.add(new Field("idItemConfiguracao", "idItemConfiguracao", false, false, false, false));
        listFields.add(new Field("statusAlerta", "statusAlerta", false, false, false, false));
        listFields.add(new Field("emailsAlerta", "emailsAlerta", false, false, false, false));
        listFields.add(new Field("descricaoAlerta", "descricaoAlerta", false, false, false, false));
        listFields.add(new Field("idEventoMonitoramento", "idEventoMonitoramento", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "Recurso";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("nomeRecurso");
    }

    @Override
    public Class getBean() {
        return RecursoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByHostName(final String hostName) throws PersistenceException {
        String sql = "select " + this.getNamesFieldsStr() + " from " + this.getTableName() + "";
        sql = sql + " where hostName = ? and dataFim is null order by nomeRecurso,hostName,serviceName ";
        final List parametros = new ArrayList<>();
        parametros.add(hostName);
        final List lst = this.execSQL(sql, parametros.toArray());
        return this.listConvertion(this.getBean(), lst, this.getListNamesFieldClass());
    }

    public Collection findByIdGrupoRecurso(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idGrupoRecurso", "=", parm));
        ordenacao.add(new Order("nomeRecurso"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdGrupoRecurso(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idGrupoRecurso", "=", parm));
        super.deleteByCondition(condicao);
    }

    /**
     * @author euler.ramos
     * @param idCalendario
     * @return
     * @throws Exception
     */
    public ArrayList<RecursoDTO> findByIdCalendario(final Integer idcalendario) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCalendario", "=", idcalendario));
        ordenacao.add(new Order("nomeRecurso"));
        final ArrayList<RecursoDTO> result = (ArrayList<RecursoDTO>) super.findByCondition(condicao, ordenacao);
        return result == null ? new ArrayList<RecursoDTO>() : result;
    }

}
