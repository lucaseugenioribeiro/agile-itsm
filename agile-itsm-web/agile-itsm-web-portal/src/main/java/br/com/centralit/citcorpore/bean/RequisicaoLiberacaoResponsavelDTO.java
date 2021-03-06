package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author thiago matias
 *
 */
public class RequisicaoLiberacaoResponsavelDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Integer idRequisicaoLiberacao;
    private Integer idResponsavel;

    private Integer idRequisicaoLiberacaoResp;
    private String nomeResponsavel;
    private String papelResponsavel;
    private String telResponsavel;
    private String emailResponsavel;
    private String nomeCargo;
    private Date dataFim;
    private Integer sequenciaEmpregado;

    public Integer getIdRequisicaoLiberacaoResp() {
        return idRequisicaoLiberacaoResp;
    }

    public void setIdRequisicaoLiberacaoResp(final Integer idRequisicaoLiberacaoResp) {
        this.idRequisicaoLiberacaoResp = idRequisicaoLiberacaoResp;
    }

    public Integer getIdRequisicaoLiberacao() {
        return idRequisicaoLiberacao;
    }

    public void setIdRequisicaoLiberacao(final Integer parm) {
        idRequisicaoLiberacao = parm;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer parm) {
        idResponsavel = parm;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(final String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getPapelResponsavel() {
        return papelResponsavel;
    }

    public void setPapelResponsavel(final String papelResponsavel) {
        this.papelResponsavel = papelResponsavel;
    }

    public String getTelResponsavel() {
        return telResponsavel;
    }

    public void setTelResponsavel(final String telResponsavel) {
        this.telResponsavel = telResponsavel;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(final String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(final String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the sequenciaEmpregado
     */
    public Integer getSequenciaEmpregado() {
        return sequenciaEmpregado;
    }

    /**
     * @param sequenciaEmpregado
     *            the sequenciaEmpregado to set
     */
    public void setSequenciaEmpregado(final Integer sequenciaEmpregado) {
        this.sequenciaEmpregado = sequenciaEmpregado;
    }

}
