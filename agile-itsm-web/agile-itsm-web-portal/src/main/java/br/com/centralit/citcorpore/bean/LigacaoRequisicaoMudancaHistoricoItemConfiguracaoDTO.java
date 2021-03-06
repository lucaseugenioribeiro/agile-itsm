package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author murilo.pacheco
 *         classe criada para fazer a ligação da tabela de responsaveis com os hitoricos.
 *
 */
public class LigacaoRequisicaoMudancaHistoricoItemConfiguracaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idLigacao_mud_hist_ic;
    private Integer idRequisicaoMudanca;
    private Integer idHistoricoMudanca;
    private Integer idrequisicaomudancaitemconfiguracao;

    public Integer getIdLigacao_mud_hist_ic() {
        return idLigacao_mud_hist_ic;
    }

    public void setIdLigacao_mud_hist_ic(final Integer idLigacao_mud_hist_ic) {
        this.idLigacao_mud_hist_ic = idLigacao_mud_hist_ic;
    }

    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    public Integer getIdHistoricoMudanca() {
        return idHistoricoMudanca;
    }

    public void setIdHistoricoMudanca(final Integer idHistoricoMudanca) {
        this.idHistoricoMudanca = idHistoricoMudanca;
    }

    public Integer getIdrequisicaomudancaitemconfiguracao() {
        return idrequisicaomudancaitemconfiguracao;
    }

    public void setIdrequisicaomudancaitemconfiguracao(final Integer idrequisicaomudancaitemconfiguracao) {
        this.idrequisicaomudancaitemconfiguracao = idrequisicaomudancaitemconfiguracao;
    }

}
