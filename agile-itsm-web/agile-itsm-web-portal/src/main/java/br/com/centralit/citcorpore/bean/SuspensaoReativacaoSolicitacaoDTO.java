package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class SuspensaoReativacaoSolicitacaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String idContrato;
    private String solicitante;
    private String idGrupo;
    private String justificativa;
    private Integer idJustificativa;
    private String tipoAcao;

    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    public void setIdJustificativa(final Integer idJustificativa) {
        this.idJustificativa = idJustificativa;
    }

    public String getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final String idContrato) {
        this.idContrato = idContrato;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(final String solicitante) {
        this.solicitante = solicitante;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(final String justificativa) {
        this.justificativa = justificativa;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(final String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

}
