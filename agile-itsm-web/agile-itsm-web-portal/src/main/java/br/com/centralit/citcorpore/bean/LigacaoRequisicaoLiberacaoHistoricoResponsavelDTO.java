package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author murilo.pacheco
 *         classe criada para fazer a ligação da tabela de responsaveis com os hitoricos.
 *
 */
public class LigacaoRequisicaoLiberacaoHistoricoResponsavelDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idLigacao_lib_hist_resp;
    private Integer idRequisicaoLiberacao;
    private Integer idHistoricoLiberacao;
    private Integer idRequisicaoLiberacaoResp;

    public Integer getIdLigacao_lib_hist_resp() {
        return idLigacao_lib_hist_resp;
    }

    public void setIdLigacao_lib_hist_resp(final Integer idLigacao_lib_hist_resp) {
        this.idLigacao_lib_hist_resp = idLigacao_lib_hist_resp;
    }

    public Integer getIdRequisicaoLiberacao() {
        return idRequisicaoLiberacao;
    }

    public void setIdRequisicaoLiberacao(final Integer idRequisicaoLiberacao) {
        this.idRequisicaoLiberacao = idRequisicaoLiberacao;
    }

    public Integer getIdHistoricoLiberacao() {
        return idHistoricoLiberacao;
    }

    public void setIdHistoricoLiberacao(final Integer idHistoricoLiberacao) {
        this.idHistoricoLiberacao = idHistoricoLiberacao;
    }

    public Integer getIdRequisicaoLiberacaoResp() {
        return idRequisicaoLiberacaoResp;
    }

    public void setIdRequisicaoLiberacaoResp(final Integer idRequisicaoLiberacaoResp) {
        this.idRequisicaoLiberacaoResp = idRequisicaoLiberacaoResp;
    }

}
