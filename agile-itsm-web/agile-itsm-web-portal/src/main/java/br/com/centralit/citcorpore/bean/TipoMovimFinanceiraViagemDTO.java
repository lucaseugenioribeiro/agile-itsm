package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author ronnie.lopes
 *
 */
public class TipoMovimFinanceiraViagemDTO extends BaseEntity {

    private static final long serialVersionUID = 1582364224581163482L;

    private Integer idtipoMovimFinanceiraViagem;
    private String nome;
    private String descricao;
    private String classificacao;
    private String situacao;
    private String exigePrestacaoConta;
    private String exigeDataHoraCotacao;
    private String permiteAdiantamento;
    private Double valorPadrao;
    private String tipo;
    private String imagem;

    /**
     * @return the idtipoMovimFinanceiraViagem
     */
    public Integer getIdtipoMovimFinanceiraViagem() {
        return idtipoMovimFinanceiraViagem;
    }

    /**
     * @param idtipoMovimFinanceiraViagem
     *            the idtipoMovimFinanceiraViagem to set
     */
    public void setIdtipoMovimFinanceiraViagem(final Integer idtipoMovimFinanceiraViagem) {
        this.idtipoMovimFinanceiraViagem = idtipoMovimFinanceiraViagem;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     *            the nome to set
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao
     *            the descricao to set
     */
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the classificacao
     */
    public String getClassificacao() {
        return classificacao;
    }

    /**
     * @param classificacao
     *            the classificacao to set
     */
    public void setClassificacao(final String classificacao) {
        this.classificacao = classificacao;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao
     *            the situacao to set
     */
    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the exigePrestacaoConta
     */
    public String getExigePrestacaoConta() {
        return exigePrestacaoConta;
    }

    /**
     * @param exigePrestacaoConta
     *            the exigePrestacaoConta to set
     */
    public void setExigePrestacaoConta(final String exigePrestacaoConta) {
        this.exigePrestacaoConta = exigePrestacaoConta;
    }

    /**
     * @return the permiteAdiantamento
     */
    public String getPermiteAdiantamento() {
        return permiteAdiantamento;
    }

    /**
     * @param permiteAdiantamento
     *            the permiteAdiantamento to set
     */
    public void setPermiteAdiantamento(final String permiteAdiantamento) {
        this.permiteAdiantamento = permiteAdiantamento;
    }

    /**
     * @return the valorPadrao
     */
    public Double getValorPadrao() {
        return valorPadrao;
    }

    /**
     * @param valorPadrao
     *            the valorPadrao to set
     */
    public void setValorPadrao(final Double valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     *            the tipo to set
     */
    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the imagem
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * @param imagem
     *            the imagem to set
     */
    public void setImagem(final String imagem) {
        this.imagem = imagem;
    }

    /**
     * @return the exigeDataHoraCotacao
     */
    public String getExigeDataHoraCotacao() {
        return exigeDataHoraCotacao;
    }

    /**
     * @param exigeDataHoraCotacao
     *            the exigeDataHoraCotacao to set
     */
    public void setExigeDataHoraCotacao(final String exigeDataHoraCotacao) {
        this.exigeDataHoraCotacao = exigeDataHoraCotacao;
    }

}
