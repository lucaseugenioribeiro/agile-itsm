package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author murilo.pacheco
 *         classe criada para fazer a ligação da tabela de responsaveis com os hitoricos.
 *
 */
public class LigacaoRequisicaoMudancaHistoricoResponsavelDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idLigacao_mud_hist_resp;
    private Integer idRequisicaoMudanca;
    private Integer idHistoricoMudanca;
    private Integer idRequisicaoMudancaResp;

    public Integer getIdLigacao_mud_hist_resp() {
        return idLigacao_mud_hist_resp;
    }

    public void setIdLigacao_mud_hist_resp(final Integer idLigacao_mud_hist_resp) {
        this.idLigacao_mud_hist_resp = idLigacao_mud_hist_resp;
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

    public Integer getIdRequisicaoMudancaResp() {
        return idRequisicaoMudancaResp;
    }

    public void setIdRequisicaoMudancaResp(final Integer idRequisicaoMudancaResp) {
        this.idRequisicaoMudancaResp = idRequisicaoMudancaResp;
    }

}
