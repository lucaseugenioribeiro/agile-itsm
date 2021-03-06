package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ImportacaoContratosDTO extends BaseEntity {

    private static final long serialVersionUID = -7252057961936714136L;
    private Integer idContrato;
    private boolean resultado;
    private String mensagem;

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(final boolean resultado) {
        this.resultado = resultado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;
    }

}
