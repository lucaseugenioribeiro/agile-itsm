package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class OpiniaoDTO extends BaseEntity {

    public static String QUEIXA = "Queixa";
    public static String ELOGIO = "Elogio";

    private static final long serialVersionUID = 638687400065001805L;

    private Integer idOpiniao;
    private Integer idUsuario;
    private Integer idSolicitacao;
    private Date data;
    private Timestamp hora;
    private String tipo; // (elogio, queixa)
    private String observacoes;

    public Integer getIdOpiniao() {
        return idOpiniao;
    }

    public void setIdOpiniao(final Integer idOpiniao) {
        this.idOpiniao = idOpiniao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(final Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(final Date data) {
        this.data = data;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(final Timestamp hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String observacoes) {
        this.observacoes = observacoes;
    }

}
