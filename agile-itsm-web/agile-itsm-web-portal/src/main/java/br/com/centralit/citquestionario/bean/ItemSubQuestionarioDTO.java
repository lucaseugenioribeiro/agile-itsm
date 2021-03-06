package br.com.centralit.citquestionario.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class ItemSubQuestionarioDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Collection colSubQuestionario;
    private Integer idItem;

    public Collection getColSubQuestionario() {
        return colSubQuestionario;
    }

    public void setColSubQuestionario(final Collection colSubQuestionario) {
        this.colSubQuestionario = colSubQuestionario;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(final Integer idItem) {
        this.idItem = idItem;
    }

}
