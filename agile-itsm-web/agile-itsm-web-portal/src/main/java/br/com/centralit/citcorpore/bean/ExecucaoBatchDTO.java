package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class ExecucaoBatchDTO extends BaseEntity {

    private static final long serialVersionUID = -7081344075951097468L;

    private Integer idExecucaoBatch;
    private Integer idProcessamentoBatch;
    private String conteudo;
    private Timestamp dataHora;

    public Integer getIdExecucaoBatch() {
        return idExecucaoBatch;
    }

    public void setIdExecucaoBatch(final Integer idExecucaoBatch) {
        this.idExecucaoBatch = idExecucaoBatch;
    }

    public Integer getIdProcessamentoBatch() {
        return idProcessamentoBatch;
    }

    public void setIdProcessamentoBatch(final Integer idProcessamentoBatch) {
        this.idProcessamentoBatch = idProcessamentoBatch;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(final String conteudo) {
        this.conteudo = conteudo;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(final Timestamp dataHora) {
        this.dataHora = dataHora;
    }

}
