package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LigacaoRequisicaoMudancaHistoricoProblemaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idligacao_mud_hist_pr;
    private Integer idRequisicaoMudanca;
    private Integer idHistoricoMudanca;
    private Integer idProblemaMudanca;

    public Integer getIdligacao_mud_hist_pr() {
        return idligacao_mud_hist_pr;
    }

    public void setIdligacao_mud_hist_pr(final Integer idligacao_mud_hist_pr) {
        this.idligacao_mud_hist_pr = idligacao_mud_hist_pr;
    }

    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    public Integer getIdHistoricoMudanca() {
        return idHistoricoMudanca;
    }

    public void setIdHistoricoMudanca(final Integer idHistoricoMudanca) {
        this.idHistoricoMudanca = idHistoricoMudanca;
    }

    public Integer getIdProblemaMudanca() {
        return idProblemaMudanca;
    }

    public void setIdProblemaMudanca(final Integer idProblemaMudanca) {
        this.idProblemaMudanca = idProblemaMudanca;
    }

}
