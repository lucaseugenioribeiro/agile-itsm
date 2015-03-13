package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ValorLimiteAprovacaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ValorLimiteAprovacaoDao extends CrudDaoDefaultImpl {

    public ValorLimiteAprovacaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idValorLimiteAprovacao", "idValorLimiteAprovacao", true, true, false, false));
        listFields.add(new Field("idLimiteAprovacao", "idLimiteAprovacao", false, false, false, false));
        listFields.add(new Field("tipoUtilizacao", "tipoUtilizacao", false, false, false, false));
        listFields.add(new Field("tipoLimite", "tipoLimite", false, false, false, false));
        listFields.add(new Field("valorLimite", "valorLimite", false, false, false, false));
        listFields.add(new Field("intervaloDias", "intervaloDias", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ValorLimiteAprovacao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ValorLimiteAprovacaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdLimiteAprovacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idLimiteAprovacao", "=", parm));
        ordenacao.add(new Order("idValorLimiteAprovacao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdLimiteAprovacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idLimiteAprovacao", "=", parm));
        super.deleteByCondition(condicao);
    }

}
