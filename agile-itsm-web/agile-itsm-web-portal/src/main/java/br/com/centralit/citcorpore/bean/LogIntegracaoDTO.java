/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Carlos Santos
 *         DTO para log da integração de sistemas externos
 */
public class LogIntegracaoDTO extends BaseEntity {

    private static final long serialVersionUID = -3593079788503253157L;

    private Integer idLogIntegracao;
    private Integer idIntegracao;
    private Timestamp dataHora;
    private String resultado;
    private String situacao;

    public Integer getIdLogIntegracao() {
        return idLogIntegracao;
    }

    public void setIdLogIntegracao(final Integer idLogIntegracao) {
        this.idLogIntegracao = idLogIntegracao;
    }

    public Integer getIdIntegracao() {
        return idIntegracao;
    }

    public void setIdIntegracao(final Integer idIntegracao) {
        this.idIntegracao = idIntegracao;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(final Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(final String resultado) {
        this.resultado = resultado;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

}
