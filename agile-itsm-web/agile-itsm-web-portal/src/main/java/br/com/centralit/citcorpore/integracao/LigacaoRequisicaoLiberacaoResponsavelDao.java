package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.LigacaoRequisicaoLiberacaoHistoricoResponsavelDTO;
import br.com.centralit.citcorpore.bean.RequisicaoLiberacaoResponsavelDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class LigacaoRequisicaoLiberacaoResponsavelDao extends CrudDaoDefaultImpl {

    public LigacaoRequisicaoLiberacaoResponsavelDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idLigacao_lib_hist_resp", "idLigacao_lib_hist_resp", true, true, false, false));
        listFields.add(new Field("idRequisicaoLiberacao", "idRequisicaoLiberacao", false, false, false, false));
        listFields.add(new Field("idHistoricoLiberacao", "idHistoricoLiberacao", false, false, false, false));
        listFields.add(new Field("idRequisicaoLiberacaoResp", "idRequisicaoLiberacaoResp", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "LIGACAO_LIB_HIST_RESP";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return LigacaoRequisicaoLiberacaoHistoricoResponsavelDTO.class;
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

    public Collection<RequisicaoLiberacaoResponsavelDTO> findByIdLiberacao(final Integer idRequisicaoLiberacao) throws Exception {
        new ArrayList<>();
        final List fields = new ArrayList<>();

        final String sql = " select rqResponsavel.idResponsavel, rqResponsavel.idRequisicaoLiberacao, responsavel.nome, responsavel.telefone, responsavel.email, cargo.nomecargo , rqResponsavel.papelresponsavel "
                + " from requisicaoliberacaoresponsavel rqResponsavel "
                + " inner join liberacao lib on rqResponsavel.idRequisicaoLiberacao = lib.idLiberacao "
                + " inner join empregados responsavel on rqResponsavel.idResponsavel = responsavel.idempregado "
                + " inner join cargos cargo on responsavel.idcargo = cargo.idcargo" + " where rqResponsavel.idRequisicaoLiberacao = ? ";

        final List resultado = this.execSQL(sql, new Object[] {idRequisicaoLiberacao});

        fields.add("idResponsavel");
        fields.add("idRequisicaoLiberacao");
        fields.add("nomeResponsavel");
        fields.add("telResponsavel");
        fields.add("emailResponsavel");
        fields.add("nomeCargo");
        fields.add("papelResponsavel");

        return this.listConvertion(RequisicaoLiberacaoResponsavelDTO.class, resultado, fields);
    }

}
