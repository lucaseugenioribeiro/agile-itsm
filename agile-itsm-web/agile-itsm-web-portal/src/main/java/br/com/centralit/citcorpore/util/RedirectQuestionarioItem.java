package br.com.centralit.citcorpore.util;

import java.io.Serializable;

public class RedirectQuestionarioItem implements Serializable {

    private static final long serialVersionUID = -2902862351523029279L;

    private String aba;
    private String situacao;
    private String include;

    public String getAba() {
        return aba;
    }

    public void setAba(final String aba) {
        this.aba = aba;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(final String include) {
        this.include = include;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

}
