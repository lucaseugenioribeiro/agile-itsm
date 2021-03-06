package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.BIConsultaColunasDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class BIConsultaColunasDao extends CrudDaoDefaultImpl {

    public BIConsultaColunasDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idConsultaColuna", "idConsultaColuna", true, true, false, false));
        listFields.add(new Field("idConsulta", "idConsulta", false, false, false, false));
        listFields.add(new Field("nomeColuna", "nomeColuna", false, false, false, false));
        listFields.add(new Field("tipoFiltro", "tipoFiltro", false, false, false, false));
        listFields.add(new Field("ordem", "ordem", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "BI_ConsultaColunas";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return BIConsultaColunasDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdConsulta(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idConsulta", "=", parm));
        ordenacao.add(new Order("ordem"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdConsulta(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idConsulta", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByOrdem(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("ordem", "=", parm));
        ordenacao.add(new Order(""));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByOrdem(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("ordem", "=", parm));
        super.deleteByCondition(condicao);
    }

}
