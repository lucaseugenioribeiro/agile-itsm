package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RequisicaoLiberacaoMidiaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class RequisicaoLiberacaoMidiaDao extends CrudDaoDefaultImpl {

    public RequisicaoLiberacaoMidiaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idRequisicaoLiberacaoMidia", "idRequisicaoLiberacaoMidia", true, true, false, false));
        listFields.add(new Field("idMidiaSoftware", "idMidiaSoftware", false, false, false, false));
        listFields.add(new Field("idRequisicaoLiberacao", "idRequisicaoLiberacao", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "requisicaoliberacaoMidia";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return RequisicaoLiberacaoMidiaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public void deleteByIdRequisicaoLiberacao(final Integer idRequisicaoLiberacao) throws ServiceException, Exception {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();

        condicoes.add(new Condition("idRequisicaoLiberacao", "=", idRequisicaoLiberacao));

        super.deleteByCondition(condicoes);
    }

    public Collection findByIdLiberacao(final Integer parm) throws Exception {
        new ArrayList<>();
        final List fields = new ArrayList<>();

        final String sql = " select rqMidia.idRequisicaoLiberacaoMidia, rqMidia.idMidiaSoftware, rqMidia.idRequisicaoLiberacao, midia.nome "
                + " from requisicaoliberacaomidia rqMidia " + " inner join liberacao lib on rqMidia.idRequisicaoLiberacao = lib.idLiberacao "
                + " inner join midiasoftware midia on rqMidia.idMidiaSoftware = midia.idmidiasoftware " + " where rqMidia.idRequisicaoLiberacao = ? ";

        final List resultado = this.execSQL(sql, new Object[] {parm});

        fields.add("idRequisicaoLiberacaoMidia");
        fields.add("idMidiaSoftware");
        fields.add("idRequisicaoLiberacao");
        fields.add("nomeMidia");

        return this.listConvertion(this.getBean(), resultado, fields);
    }

    public Collection listByIdHistoricoLiberacao(final Integer parm) throws Exception {
        new ArrayList<>();
        final List fields = new ArrayList<>();

        final String sql = " select rqMidia.idRequisicaoLiberacaoMidia, rqMidia.idMidiaSoftware, rqMidia.idRequisicaoLiberacao, midia.nome "
                + " from requisicaoliberacaomidia rqMidia "
                + " inner join ligacao_lib_hist_midia lig on rqMidia.idrequisicaoliberacaomidia = lig.idrequisicaoliberacaomidia "
                + " inner join liberacao lib on rqMidia.idRequisicaoLiberacao = lib.idLiberacao "
                + " inner join midiasoftware midia on rqMidia.idMidiaSoftware = midia.idmidiasoftware " + " where lig.idhistoricoliberacao = ? ";

        final List resultado = this.execSQL(sql, new Object[] {parm});

        fields.add("idRequisicaoLiberacaoMidia");
        fields.add("idMidiaSoftware");
        fields.add("idRequisicaoLiberacao");
        fields.add("nomeMidia");

        return this.listConvertion(this.getBean(), resultado, fields);
    }

    public Collection findByIdLiberacaoEDataFim(final Integer parm) throws Exception {
        new ArrayList<>();
        final List fields = new ArrayList<>();

        final String sql = " select rqMidia.idRequisicaoLiberacaoMidia, rqMidia.idMidiaSoftware, rqMidia.idRequisicaoLiberacao, midia.nome "
                + " from requisicaoliberacaomidia rqMidia " + " inner join liberacao lib on rqMidia.idRequisicaoLiberacao = lib.idLiberacao "
                + " inner join midiasoftware midia on rqMidia.idMidiaSoftware = midia.idmidiasoftware "
                + " where rqMidia.idRequisicaoLiberacao = ? and rqMidia.datafim is null";

        final List resultado = this.execSQL(sql, new Object[] {parm});

        fields.add("idRequisicaoLiberacaoMidia");
        fields.add("idMidiaSoftware");
        fields.add("idRequisicaoLiberacao");
        fields.add("nomeMidia");

        return this.listConvertion(this.getBean(), resultado, fields);
    }

}
