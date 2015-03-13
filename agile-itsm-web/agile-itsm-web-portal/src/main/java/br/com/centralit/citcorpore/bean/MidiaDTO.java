package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class MidiaDTO extends BaseEntity {

    private static final long serialVersionUID = 3534747605951252279L;

    private Integer idMidia;
    private String nome;

    public Integer getIdMidia() {
        return idMidia;
    }

    public void setIdMidia(final Integer idMidia) {
        this.idMidia = idMidia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

}
