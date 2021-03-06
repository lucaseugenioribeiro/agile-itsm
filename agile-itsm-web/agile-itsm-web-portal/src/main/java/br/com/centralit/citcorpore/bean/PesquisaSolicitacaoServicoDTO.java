package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import br.com.citframework.util.UtilI18N;

public class PesquisaSolicitacaoServicoDTO {

    private Date dataInicio;

    private Date dataFim;

    private Date dataInicioFechamento;

    private Date dataFimFechamento;

    private Integer idSolicitacaoServicoPesquisa;

    private Integer idSolicitacaoServico;

    private Integer idContrato;

    private Integer idItemConfiguracao;

    private Integer idSolicitante;

    private Integer idResponsavel;

    private Integer idUsuarioResponsavelAtual;

    private Integer idUnidade;

    private Integer idLocalidade;

    private Integer idServico;

    private String situacao;

    private Integer idPrioridade;

    private Integer idOrigem;

    private Integer idFaseAtual;

    private String ordenacao;

    private Integer idGrupoAtual;

    private String palavraChave;

    private Integer idTipoDemandaServico;

    private String exibirCampoDescricao;

    private String nomeItemConfiguracao;

    private String nomeSolicitante;

    private String nomeResponsavel;

    private String nomeUsuarioResponsavelAtual;

    private String nomeTipoDemandaServico;

    private String faseAtual;

    private String prioridade;

    private String grupoAtual;

    private String origem;

    private Integer totalItens;

    private Integer totalPagina;

    private Integer pagAtual;

    private Integer pagAtualAux;

    private String flag;

    private UsuarioDTO usuarioLogado;

    /**
     * Valor do TOP List
     *
     * @author thyen.chang
     */
    private Integer topList;

    public Integer getTopList() {
        return topList;
    }

    public void setTopList(final Integer topList) {
        this.topList = topList;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio
     *            the dataInicio to set
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim
     *            the dataFim to set
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the dataInicioFechamento
     */
    public Date getDataInicioFechamento() {
        return dataInicioFechamento;
    }

    /**
     * @param dataInicioFechamento
     *            the dataInicioFechamento to set
     */
    public void setDataInicioFechamento(final Date dataInicioFechamento) {
        this.dataInicioFechamento = dataInicioFechamento;
    }

    /**
     * @return the dataFimFechamento
     */
    public Date getDataFimFechamento() {
        return dataFimFechamento;
    }

    /**
     * @param dataFimFechamento
     *            the dataFimFechamento to set
     */
    public void setDataFimFechamento(final Date dataFimFechamento) {
        this.dataFimFechamento = dataFimFechamento;
    }

    /**
     * @return the idSolicitacaoServicoPesquisa
     */
    public Integer getIdSolicitacaoServicoPesquisa() {
        return idSolicitacaoServicoPesquisa;
    }

    /**
     * @param idSolicitacaoServicoPesquisa
     *            the idSolicitacaoServicoPesquisa to set
     */
    public void setIdSolicitacaoServicoPesquisa(final Integer idSolicitacaoServicoPesquisa) {
        this.idSolicitacaoServicoPesquisa = idSolicitacaoServicoPesquisa;
    }

    /**
     * @return the idContrato
     */
    public Integer getIdContrato() {
        return idContrato;
    }

    /**
     * @param idContrato
     *            the idContrato to set
     */
    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * @return the idItemConfiguracao
     */
    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    /**
     * @param idItemConfiguracao
     *            the idItemConfiguracao to set
     */
    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    /**
     * @return the idSolicitante
     */
    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    /**
     * @param idSolicitante
     *            the idSolicitante to set
     */
    public void setIdSolicitante(final Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    /**
     * @return the idResponsavel
     */
    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    /**
     * @param idResponsavel
     *            the idResponsavel to set
     */
    public void setIdResponsavel(final Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    /**
     * @return the idUsuarioResponsavelAtual
     */
    public Integer getIdUsuarioResponsavelAtual() {
        return idUsuarioResponsavelAtual;
    }

    /**
     * @param idUsuarioResponsavelAtual
     *            the idUsuarioResponsavelAtual to set
     */
    public void setIdUsuarioResponsavelAtual(final Integer idUsuarioResponsavelAtual) {
        this.idUsuarioResponsavelAtual = idUsuarioResponsavelAtual;
    }

    /**
     * @return the idUnidade
     */
    public Integer getIdUnidade() {
        return idUnidade;
    }

    /**
     * @param idUnidade
     *            the idUnidade to set
     */
    public void setIdUnidade(final Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    /**
     * @return the idServico
     */
    public Integer getIdServico() {
        return idServico;
    }

    /**
     * @param idServico
     *            the idServico to set
     */
    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
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
     * @return the idPrioridade
     */
    public Integer getIdPrioridade() {
        return idPrioridade;
    }

    /**
     * @param idPrioridade
     *            the idPrioridade to set
     */
    public void setIdPrioridade(final Integer idPrioridade) {
        this.idPrioridade = idPrioridade;
    }

    /**
     * @return the idOrigem
     */
    public Integer getIdOrigem() {
        return idOrigem;
    }

    /**
     * @param idOrigem
     *            the idOrigem to set
     */
    public void setIdOrigem(final Integer idOrigem) {
        this.idOrigem = idOrigem;
    }

    /**
     * @return the idFaseAtual
     */
    public Integer getIdFaseAtual() {
        return idFaseAtual;
    }

    /**
     * @param idFaseAtual
     *            the idFaseAtual to set
     */
    public void setIdFaseAtual(final Integer idFaseAtual) {
        this.idFaseAtual = idFaseAtual;
    }

    /**
     * @return the ordenacao
     */
    public String getOrdenacao() {
        return ordenacao;
    }

    /**
     * @param ordenacao
     *            the ordenacao to set
     */
    public void setOrdenacao(final String ordenacao) {
        this.ordenacao = ordenacao;
    }

    /**
     * @return the idGrupoAtual
     */
    public Integer getIdGrupoAtual() {
        return idGrupoAtual;
    }

    /**
     * @param idGrupoAtual
     *            the idGrupoAtual to set
     */
    public void setIdGrupoAtual(final Integer idGrupoAtual) {
        this.idGrupoAtual = idGrupoAtual;
    }

    /**
     * @return the palavraChave
     */
    public String getPalavraChave() {
        return palavraChave;
    }

    /**
     * @param palavraChave
     *            the palavraChave to set
     */
    public void setPalavraChave(final String palavraChave) {
        this.palavraChave = palavraChave;
    }

    /**
     * @return the idTipoDemandaServico
     */
    public Integer getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    /**
     * @param idTipoDemandaServico
     *            the idTipoDemandaServico to set
     */
    public void setIdTipoDemandaServico(final Integer idTipoDemandaServico) {
        this.idTipoDemandaServico = idTipoDemandaServico;
    }

    /**
     * @return the exibirCampoDescricao
     */
    public String getExibirCampoDescricao() {
        return exibirCampoDescricao;
    }

    /**
     * @param exibirCampoDescricao
     *            the exibirCampoDescricao to set
     */
    public void setExibirCampoDescricao(final String exibirCampoDescricao) {
        this.exibirCampoDescricao = exibirCampoDescricao;
    }

    public String getNomeItemConfiguracao() {
        return nomeItemConfiguracao;
    }

    public void setNomeItemConfiguracao(final String nomeItemConfiguracao) {
        this.nomeItemConfiguracao = nomeItemConfiguracao;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(final String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    /**
     * @return the nomeResponsavel
     */

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    /**
     * @param nomeResponsavel
     *            the nomeResponsavel to set
     */
    public void setNomeResponsavel(final String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    /**
     * @return the nomeUsuarioResponsavelAtual
     */
    public String getNomeUsuarioResponsavelAtual() {
        return nomeUsuarioResponsavelAtual;
    }

    /**
     * @param nomeUsuarioResponsavelAtual
     *            the nomeUsuarioResponsavelAtual to set
     */
    public void setNomeUsuarioResponsavelAtual(final String nomeUsuarioResponsavelAtual) {
        this.nomeUsuarioResponsavelAtual = nomeUsuarioResponsavelAtual;
    }

    public String getNomeTipoDemandaServico() {
        return nomeTipoDemandaServico;
    }

    public void setNomeTipoDemandaServico(final String nomeTipoDemandaServico) {
        this.nomeTipoDemandaServico = nomeTipoDemandaServico;
    }

    public String getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(final String faseAtual) {
        this.faseAtual = faseAtual;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(final String prioridade) {
        this.prioridade = prioridade;
    }

    public String getGrupoAtual() {
        return grupoAtual;
    }

    public void setGrupoAtual(final String grupoAtual) {
        this.grupoAtual = grupoAtual;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(final String origem) {
        this.origem = origem;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Integer getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(final Integer totalItens) {
        this.totalItens = totalItens;
    }

    public Integer getTotalPagina() {
        return totalPagina;
    }

    public void setTotalPagina(final Integer paginaTotal) {
        totalPagina = paginaTotal;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(final String flag) {
        this.flag = flag;
    }

    /**
     * @author euler.ramos
     *         Criado para passar o usuário logado diretamente para o DAO que precisa, em alguns momentos das unidades que o usuário pode acessar.
     * @return
     */
    public UsuarioDTO getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(final UsuarioDTO usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Integer getPagAtual() {
        return pagAtual;
    }

    public void setPagAtual(final Integer pagAtual) {
        this.pagAtual = pagAtual;
    }

    public Integer getPagAtualAux() {
        return pagAtualAux;
    }

    public void setPagAtualAux(final Integer pagAtualAux) {
        this.pagAtualAux = pagAtualAux;
    }

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(final Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    /**
     * 25/09/2013
     * Trata a internacionalização de acordo com a situação
     * Foi verificado que existem todos as situações internacionalizadas em citcorpore.comum.{situacao}
     * sendo (situacao} em minusculo
     * 
     * @param request
     * @return String
     * @author uelen.pereira
     */
    public String getSituacaoInternacionalizada(final HttpServletRequest request) {
        return UtilI18N.internacionaliza(request, "citcorpore.comum." + situacao.trim().toLowerCase());
    }

}
