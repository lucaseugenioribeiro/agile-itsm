package br.com.centralit.citcorpore.metainfo.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.metainfo.bean.BotaoAcaoVisaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class BotaoAcaoVisaoDao extends CrudDaoDefaultImpl {

    public BotaoAcaoVisaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final List<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idBotaoAcaoVisao", "idBotaoAcaoVisao", true, true, false, false));
        listFields.add(new Field("idVisao", "idVisao", false, false, false, false));
        listFields.add(new Field("texto", "texto", false, false, false, false));
        listFields.add(new Field("acao", "acao", false, false, false, false));
        listFields.add(new Field("script", "script", false, false, false, false));
        listFields.add(new Field("hint", "hint", false, false, false, false));
        listFields.add(new Field("icone", "icone", false, false, false, false));
        listFields.add(new Field("ordem", "ordem", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "BotaoAcaoVisao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return BotaoAcaoVisaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdVisao(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idVisao", "=", parm));
        ordenacao.add(new Order("ordem"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdVisao(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idVisao", "=", parm));
        super.deleteByCondition(condicao);
    }

}
