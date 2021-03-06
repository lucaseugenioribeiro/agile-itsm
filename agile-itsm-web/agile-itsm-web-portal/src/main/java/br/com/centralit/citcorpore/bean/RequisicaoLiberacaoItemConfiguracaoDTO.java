package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RequisicaoLiberacaoItemConfiguracaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idRequisicaoLiberacaoItemConfiguracao;
    private Integer idRequisicaoLiberacao;
    private Integer idItemConfiguracao;
    private String descricao;
    private Integer idHistoricoLiberacao;

    private String nomeItemConfiguracao;

    private String nomeStatusIc;
    private Integer idStatusIc;

    public Integer getIdStatusIc() {

        return idStatusIc;
    }

    public void setIdStatusIc(final Integer idStatusIc) {
        this.idStatusIc = idStatusIc;
    }

    public String getNomeStatusIc() {
        return nomeStatusIc;
    }

    public void setNomeStatusIc(final String nomeStatusIc) {
        this.nomeStatusIc = nomeStatusIc;
    }

    public String getNomeItemConfiguracao() {
        return nomeItemConfiguracao;
    }

    public void setNomeItemConfiguracao(final String nomeItemConfiguracao) {
        this.nomeItemConfiguracao = nomeItemConfiguracao;
    }

    public Integer getIdRequisicaoLiberacaoItemConfiguracao() {
        return idRequisicaoLiberacaoItemConfiguracao;
    }

    public void setIdRequisicaoLiberacaoItemConfiguracao(final Integer idRequisicaoLiberacaoItemConfiguracao) {
        this.idRequisicaoLiberacaoItemConfiguracao = idRequisicaoLiberacaoItemConfiguracao;
    }

    public Integer getIdRequisicaoLiberacao() {
        return idRequisicaoLiberacao;
    }

    public void setIdRequisicaoLiberacao(final Integer idRequisicaoLiberacao) {
        this.idRequisicaoLiberacao = idRequisicaoLiberacao;
    }

    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdHistoricoLiberacao() {
        return idHistoricoLiberacao;
    }

    public void setIdHistoricoLiberacao(final Integer idHistoricoLiberacao) {
        this.idHistoricoLiberacao = idHistoricoLiberacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (descricao == null ? 0 : descricao.hashCode());
        result = prime * result + (idHistoricoLiberacao == null ? 0 : idHistoricoLiberacao.hashCode());
        result = prime * result + (idItemConfiguracao == null ? 0 : idItemConfiguracao.hashCode());
        result = prime * result + (idRequisicaoLiberacao == null ? 0 : idRequisicaoLiberacao.hashCode());
        result = prime * result + (idRequisicaoLiberacaoItemConfiguracao == null ? 0 : idRequisicaoLiberacaoItemConfiguracao.hashCode());
        result = prime * result + (idStatusIc == null ? 0 : idStatusIc.hashCode());
        result = prime * result + (nomeItemConfiguracao == null ? 0 : nomeItemConfiguracao.hashCode());
        result = prime * result + (nomeStatusIc == null ? 0 : nomeStatusIc.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        RequisicaoLiberacaoItemConfiguracaoDTO objComparacao = null;

        if (obj instanceof RequisicaoLiberacaoItemConfiguracaoDTO) {
            objComparacao = (RequisicaoLiberacaoItemConfiguracaoDTO) obj;
            if (objComparacao.getIdItemConfiguracao().equals(this.getIdItemConfiguracao())
                    && objComparacao.getIdRequisicaoLiberacao().equals(this.getIdRequisicaoLiberacao())) {
                return true;
            }
        }

        return false;
    }

}
