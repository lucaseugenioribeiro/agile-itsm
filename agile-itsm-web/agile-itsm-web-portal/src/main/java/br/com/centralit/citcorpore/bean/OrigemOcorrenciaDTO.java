package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author thiago.monteiro
 */
public class OrigemOcorrenciaDTO extends BaseEntity {

    private static final long serialVersionUID = 9050009857127616153L;

    private Integer idOrigemOcorrencia;
    private String nome;
    private Date dataInicio;
    private Date dataFim;

    /**
     * @return idOrigemOcorrencia
     */
    public Integer getIdOrigemOcorrencia() {
        return idOrigemOcorrencia;
    }

    /**
     * @param idOrigemOcorrencia
     */
    public void setIdOrigemOcorrencia(final Integer idOrigemOcorrencia) {
        this.idOrigemOcorrencia = idOrigemOcorrencia;
    }

    /**
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
