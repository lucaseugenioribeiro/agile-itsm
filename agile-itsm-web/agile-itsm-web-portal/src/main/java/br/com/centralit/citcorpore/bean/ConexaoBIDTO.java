package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import br.com.agileitsm.model.support.BaseEntity;

public class ConexaoBIDTO extends BaseEntity {

    private static final long serialVersionUID = -58546973455247L;

    private Integer paginaSelecionada;

    private Integer itensPorPagina;

    private Integer tipoLista;

    private String situacao;

    private Integer idConexaoBI;

    private String nome;

    private String link;

    private String login;

    private String senha;

    private String status;

    private String statusFiltro;

    private Timestamp dataHoraUltimaImportacao;

    private String emailNotificacao;

    private String caminhoPastaLog;

    private Integer qtdeDiasAtraso;

    private Integer idProcessamentoBatchEspecifico;

    private Integer idProcessamentoBatchExcecao;

    private String tipoImportacao;

    // paginacao
    private Integer totalItens;
    private Integer totalPaginas;

    public Integer getPaginaSelecionada() {
        return paginaSelecionada;
    }

    public void setPaginaSelecionada(final Integer paginaSelecionada) {
        this.paginaSelecionada = paginaSelecionada;
    }

    public Integer getItensPorPagina() {
        return itensPorPagina;
    }

    public void setItensPorPagina(final Integer itensPorPagina) {
        this.itensPorPagina = itensPorPagina;
    }

    public Integer getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(final Integer totalItens) {
        this.totalItens = totalItens;
    }

    public Integer getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(final Integer totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public Integer getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(final Integer tipoLista) {
        this.tipoLista = tipoLista;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Integer getIdConexaoBI() {
        return idConexaoBI;
    }

    public void setIdConexaoBI(final Integer idConexaoBI) {
        this.idConexaoBI = idConexaoBI;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getLink() {
        return link;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getStatusFiltro() {
        return statusFiltro;
    }

    public void setStatusFiltro(final String statusFiltro) {
        this.statusFiltro = statusFiltro;
    }

    public Timestamp getDataHoraUltimaImportacao() {
        return dataHoraUltimaImportacao;
    }

    public void setDataHoraUltimaImportacao(final Timestamp dataHoraUltimaImportacao) {
        this.dataHoraUltimaImportacao = dataHoraUltimaImportacao;
    }

    public String getEmailNotificacao() {
        return emailNotificacao;
    }

    public void setEmailNotificacao(final String emailNotificacao) {
        this.emailNotificacao = emailNotificacao;
    }

    public String getCaminhoPastaLog() {
        return caminhoPastaLog;
    }

    public void setCaminhoPastaLog(final String caminhoPastaLog) {
        this.caminhoPastaLog = caminhoPastaLog;
    }

    public Integer getQtdeDiasAtraso() {
        return qtdeDiasAtraso;
    }

    public void setQtdeDiasAtraso(final Integer qtdeDiasAtraso) {
        this.qtdeDiasAtraso = qtdeDiasAtraso;
    }

    public Integer getIdProcessamentoBatchEspecifico() {
        return idProcessamentoBatchEspecifico;
    }

    public void setIdProcessamentoBatchEspecifico(final Integer idProcessamentoBatchEspecifico) {
        this.idProcessamentoBatchEspecifico = idProcessamentoBatchEspecifico;
    }

    public Integer getIdProcessamentoBatchExcecao() {
        return idProcessamentoBatchExcecao;
    }

    public void setIdProcessamentoBatchExcecao(final Integer idProcessamentoBatchExcecao) {
        this.idProcessamentoBatchExcecao = idProcessamentoBatchExcecao;
    }

    /**
     * @return pegando data como string para exibir na listagem a data formatada com horas e minutos
     * @author thiago.barbosa
     */
    public String getDataHoraUltimaImportacaoString() {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (dataHoraUltimaImportacao != null && !dataHoraUltimaImportacao.equals("")) {
            return sdf.format(dataHoraUltimaImportacao);
        }
        return "--";
    }

    public String getTipoImportacao() {
        return tipoImportacao;
    }

    public void setTipoImportacao(final String tipoImportacao) {
        this.tipoImportacao = tipoImportacao;
    }

}
