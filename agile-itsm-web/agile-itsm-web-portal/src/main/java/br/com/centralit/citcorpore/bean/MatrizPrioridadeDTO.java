package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 *
 * @author rodrigo.oliveira
 *
 */
public class MatrizPrioridadeDTO extends BaseEntity {

    private static final long serialVersionUID = -3987037840778547987L;

    private Integer idMatrizPrioridade;
    private String siglaImpacto;
    private String nivelImpacto;
    private String siglaUrgencia;
    private String nivelUrgencia;
    private Integer valorPrioridade;
    private Integer idContrato;
    private String deleted;

    /**
     * @return the idMatrizPrioridade
     */
    public Integer getIdMatrizPrioridade() {
        return idMatrizPrioridade;
    }

    /**
     * @param idMatrizPrioridade
     *            the idMatrizPrioridade to set
     */
    public void setIdMatrizPrioridade(final Integer idMatrizPrioridade) {
        this.idMatrizPrioridade = idMatrizPrioridade;
    }

    /**
     * @return the idImpacto
     */
    public String getSiglaImpacto() {
        return siglaImpacto;
    }

    /**
     * @param idImpacto
     *            the idImpacto to set
     */
    public void setSiglaImpacto(final String siglaImpacto) {
        this.siglaImpacto = siglaImpacto;
    }

    /**
     * @return the idUrgencia
     */
    public String getSiglaUrgencia() {
        return siglaUrgencia;
    }

    /**
     * @param idUrgencia
     *            the idUrgencia to set
     */
    public void setSiglaUrgencia(final String siglaUrgencia) {
        this.siglaUrgencia = siglaUrgencia;
    }

    /**
     * @return the valorPrioridade
     */
    public Integer getValorPrioridade() {
        return valorPrioridade;
    }

    /**
     * @param valorPrioridade
     *            the valorPrioridade to set
     */
    public void setValorPrioridade(final Integer valorPrioridade) {
        this.valorPrioridade = valorPrioridade;
    }

    /**
     * @return the idContrato
     */
    public Integer getIdContrato() {
        return idContrato;
    }

    /**
     * @param idContrato
     *            the idContrato to set
     */
    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * @return the deleted
     */
    public String getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     *            the deleted to set
     */
    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the nivelImpacto
     */
    public String getNivelImpacto() {
        return nivelImpacto;
    }

    /**
     * @param nivelImpacto
     *            the nivelImpacto to set
     */
    public void setNivelImpacto(final String nivelImpacto) {
        this.nivelImpacto = nivelImpacto;
    }

    /**
     * @return the nivelUrgencia
     */
    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    /**
     * @param nivelUrgencia
     *            the nivelUrgencia to set
     */
    public void setNivelUrgencia(final String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

}
