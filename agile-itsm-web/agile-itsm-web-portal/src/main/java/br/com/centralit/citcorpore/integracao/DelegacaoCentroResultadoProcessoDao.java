package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.DelegacaoCentroResultadoProcessoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class DelegacaoCentroResultadoProcessoDao extends CrudDaoDefaultImpl {

    public DelegacaoCentroResultadoProcessoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idDelegacaoCentroResultado", "idDelegacaoCentroResultado", true, false, false, false));
        listFields.add(new Field("idProcessoNegocio", "idProcessoNegocio", true, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "DelegCentroResultadoProcesso";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return DelegacaoCentroResultadoProcessoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdDelegacaoCentroResultado(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idDelegacaoCentroResultado", "=", parm));
        ordenacao.add(new Order("idProcessoNegocio"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdDelegacaoCentroResultado(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idDelegacaoCentroResultado", "=", parm));
        super.deleteByCondition(condicao);
    }

}
