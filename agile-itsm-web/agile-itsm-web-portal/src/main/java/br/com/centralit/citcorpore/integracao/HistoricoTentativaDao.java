package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.HistoricoTentativaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class HistoricoTentativaDao extends CrudDaoDefaultImpl {

    public HistoricoTentativaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idHistoricoTentativa", "idHistoricoTentativa", true, true, false, false));
        listFields.add(new Field("idEvento", "idevento", false, false, false, false));
        listFields.add(new Field("idItemConfiguracao", "iditemconfiguracao", false, false, false, false));
        listFields.add(new Field("idBaseItemConfiguracao", "idbaseitemconfiguracao", false, false, false, false));
        listFields.add(new Field("idEmpregado", "idempregado", false, false, false, false));
        listFields.add(new Field("descricao", "descricao", false, false, false, false));
        listFields.add(new Field("data", "data", false, false, false, false));
        listFields.add(new Field("hora", "hora", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "HistoricoTentativa";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return HistoricoTentativaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

}
