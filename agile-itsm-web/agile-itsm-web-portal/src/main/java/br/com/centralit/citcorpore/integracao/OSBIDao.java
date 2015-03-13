package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.OSBIDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class OSBIDao extends CrudDaoDefaultImpl {

    public OSBIDao() {
        super(Constantes.getValue("DATABASE_BI_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idOS", "idOS", true, true, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));
        listFields.add(new Field("idClassificacaoOS", "idClassificacaoOS", false, false, false, false));
        listFields.add(new Field("idServicoContrato", "idServicoContrato", false, false, false, false));
        listFields.add(new Field("ano", "ano", false, false, false, false));
        listFields.add(new Field("numero", "numero", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("demanda", "demanda", false, false, false, false));
        listFields.add(new Field("objetivo", "objetivo", false, false, false, false));
        listFields.add(new Field("nomeAreaRequisitante", "nomeAreaRequisitante", false, false, false, false));
        listFields.add(new Field("situacaoOS", "situacaoOS", false, false, false, false));
        listFields.add(new Field("obsFinalizacao", "obsFinalizacao", false, false, false, false));
        listFields.add(new Field("quantidadeGlosasAnterior", "quantidadeGlosasAnterior", false, false, false, false));
        listFields.add(new Field("idOSPai", "idOSPai", false, false, false, false));
        listFields.add(new Field("quantidade", "quantidade", false, false, false, false));
        listFields.add(new Field("dataEmissao", "dataEmissao", false, false, false, false));
        listFields.add(new Field("idConexaoBI", "idConexaoBI", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "os";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return OSBIDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

}
