package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author rosana.godinho
 *
 */
public class InformacaoItemConfiguracaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idInformacaoItemConfiguracao;

    private Integer idItemConfiguracao;

    private String nomeGrupoItemConfiguracao;

    private String identificacaoItemConfiguracao;

    private String nomeGrupo;

    /**
     * @return valor do atributo idInformacaoItemConfiguracao.
     */
    public Integer getIdInformacaoItemConfiguracao() {
        return idInformacaoItemConfiguracao;
    }

    /**
     * Define valor do atributo idInformacaoItemConfiguracao.
     *
     * @param idInformacaoItemConfiguracao
     */
    public void setIdInformacaoItemConfiguracao(final Integer idInformacaoItemConfiguracao) {
        this.idInformacaoItemConfiguracao = idInformacaoItemConfiguracao;
    }

    /**
     * @return valor do atributo nomeGrupo.
     */
    public String getNomeGrupo() {
        return nomeGrupo;
    }

    /**
     * Define valor do atributo nomeGrupo.
     *
     * @param nomeGrupo
     */
    public void setNomeGrupo(final String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    /**
     * @return valor do atributo idItemConfiguracao.
     */
    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    /**
     * Define valor do atributo idItemConfiguracao.
     *
     * @param idItemConfiguracao
     */
    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    /**
     * @return the nomeGrupoItemConfiguracao
     */
    public String getNomeGrupoItemConfiguracao() {
        return nomeGrupoItemConfiguracao;
    }

    /**
     * @param nomeGrupoItemConfiguracao
     *            the nomeGrupoItemConfiguracao to set
     */
    public void setNomeGrupoItemConfiguracao(final String pNomeGrupoItemConfiguracao) {
        nomeGrupoItemConfiguracao = pNomeGrupoItemConfiguracao;
    }

    public String getIdentificacaoItemConfiguracao() {
        return identificacaoItemConfiguracao;
    }

    public void setIdentificacaoItemConfiguracao(final String identificacaoItemConfiguracao) {
        this.identificacaoItemConfiguracao = identificacaoItemConfiguracao;
    }

}
