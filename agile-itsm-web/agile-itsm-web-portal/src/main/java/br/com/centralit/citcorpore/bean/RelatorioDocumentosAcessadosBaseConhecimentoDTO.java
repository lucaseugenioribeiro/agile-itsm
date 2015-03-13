package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioDocumentosAcessadosBaseConhecimentoDTO extends BaseEntity {

    private static final long serialVersionUID = -53999597539230945L;

    private Date dataInicial;
    private Date dataFinal;
    private Timestamp dataHoraAcesso;
    private Integer idBaseConhecimento;
    private String tituloBaseConhecimento;
    private Integer idUsuario;
    private String nomeUsuario;
    private Integer qtdeAcessos;
    private String formato;
    private String visualizacao;
    private Object listaDetalhe;
    private Integer ordenacao;

    /**
     * @return the dataHoraAcesso
     */
    public Timestamp getDataHoraAcesso() {
        return dataHoraAcesso;
    }

    /**
     * @param dataHoraAcesso
     *            the dataHoraAcesso to set
     */
    public void setDataHoraAcesso(final Timestamp dataHoraAcesso) {
        this.dataHoraAcesso = dataHoraAcesso;
    }

    /**
     * @return the idBaseConhecimento
     */
    public Integer getIdBaseConhecimento() {
        return idBaseConhecimento;
    }

    /**
     * @param idBaseConhecimento
     *            the idBaseConhecimento to set
     */
    public void setIdBaseConhecimento(final Integer idBaseConhecimento) {
        this.idBaseConhecimento = idBaseConhecimento;
    }

    /**
     * @return the tituloBaseConhecimento
     */
    public String getTituloBaseConhecimento() {
        return tituloBaseConhecimento;
    }

    /**
     * @param tituloBaseConhecimento
     *            the tituloBaseConhecimento to set
     */
    public void setTituloBaseConhecimento(final String tituloBaseConhecimento) {
        this.tituloBaseConhecimento = tituloBaseConhecimento;
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario
     *            the idUsuario to set
     */
    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @param nomeUsuario
     *            the nomeUsuario to set
     */
    public void setNomeUsuario(final String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * @return the qtdeAcessos
     */
    public Integer getQtdeAcessos() {
        return qtdeAcessos;
    }

    /**
     * @param qtdeAcessos
     *            the qtdeAcessos to set
     */
    public void setQtdeAcessos(final Integer qtdeAcessos) {
        this.qtdeAcessos = qtdeAcessos;
    }

    /**
     * @return the formato
     */
    public String getFormato() {
        return formato;
    }

    /**
     * @param formato
     *            the formato to set
     */
    public void setFormato(final String formato) {
        this.formato = formato;
    }

    /**
     * @return the visualizacao
     */
    public String getVisualizacao() {
        return visualizacao;
    }

    /**
     * @param visualizacao
     *            the visualizacao to set
     */
    public void setVisualizacao(final String visualizacao) {
        this.visualizacao = visualizacao;
    }

    /**
     * @return the listaDetalhe
     */
    public Object getListaDetalhe() {
        return listaDetalhe;
    }

    /**
     * @param listaDetalhe
     *            the listaDetalhe to set
     */
    public void setListaDetalhe(final Object listaDetalhe) {
        this.listaDetalhe = listaDetalhe;
    }

    public Integer getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(final Integer ordenacao) {
        this.ordenacao = ordenacao;
    }

    /**
     * @return the dataInicial
     */
    public Date getDataInicial() {
        return dataInicial;
    }

    /**
     * @param dataInicial
     *            the dataInicial to set
     */
    public void setDataInicial(final Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @return the dataFinal
     */
    public Date getDataFinal() {
        return dataFinal;
    }

    /**
     * @param dataFinal
     *            the dataFinal to set
     */
    public void setDataFinal(final Date dataFinal) {
        this.dataFinal = dataFinal;
    }

}
