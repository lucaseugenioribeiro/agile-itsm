package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.LigacaoRequisicaoMudancaHistoricoServicoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaResponsavelDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class LigacaoRequisicaoMudancaServicoDao extends CrudDaoDefaultImpl {

    public LigacaoRequisicaoMudancaServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idligacao_mud_hist_se", "idligacao_mud_hist_se", true, true, false, false));
        listFields.add(new Field("idRequisicaoMudanca", "idRequisicaoMudanca", false, false, false, false));
        listFields.add(new Field("idHistoricoMudanca", "idHistoricoMudanca", false, false, false, false));
        listFields.add(new Field("idrequisicaomudancaservico", "idrequisicaomudancaservico", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "LIGACAO_MUD_HIST_SE";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return LigacaoRequisicaoMudancaHistoricoServicoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public void deleteByIdRequisicaoMudanca(final Integer idRequisicaoMudanca) throws ServiceException, Exception {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();

        condicoes.add(new Condition("idRequisicaoMudanca", "=", idRequisicaoMudanca));

        super.deleteByCondition(condicoes);
    }

    public Collection<RequisicaoMudancaResponsavelDTO> findByIdMudanca(final Integer idRequisicaoMudanca) throws Exception {
        final List fields = new ArrayList<>();

        final String sql = "select rqResponsavel.iditemconfiguracao, rqResponsavel.idRequisicaoMudanca, responsavel.nome, responsavel.telefone, responsavel.email, cargo.nomecargo , rqResponsavel.descricao "
                + "from requisicaomudancaitemconfiguracao rqResponsavel "
                + "inner join requisicaomudanca lib on rqResponsavel.idRequisicaoMudanca = lib.idRequisicaomudanca "
                + "inner join itemconfiguracao responsavel on rqResponsavel.iditemconfiguracao = responsavel.iditemconfiguracao "
                + "inner join cargos cargo on responsavel.idcargo = cargo.idcargo" + "where rqResponsavel.idRequisicaomudanca = ? ";

        final List resultado = this.execSQL(sql, new Object[] {idRequisicaoMudanca});

        fields.add("idResponsavel");
        fields.add("idRequisicaoMudanca");
        fields.add("nomeResponsavel");
        fields.add("telResponsavel");
        fields.add("emailResponsavel");
        fields.add("nomeCargo");
        fields.add("papelResponsavel");

        return this.listConvertion(RequisicaoMudancaResponsavelDTO.class, resultado, fields);
    }

}
