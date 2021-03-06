package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class PerfilAcessoGrupoDTO extends BaseEntity {

    private Integer idPerfilAcessoGrupo;
    private Integer idGrupo;
    private Date dataInicio;
    private Date dataFim;

    /**
     * @return valor do atributo idGrupo.
     */
    public Integer getIdGrupo() {
        return idGrupo;
    }

    /**
     * Define valor do atributo idGrupo.
     *
     * @param idGrupo
     */
    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    /**
     * @return valor do atributo dataInicio.
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * Define valor do atributo dataInicio.
     *
     * @param dataInicio
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return valor do atributo dataFim.
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * Define valor do atributo dataFim.
     *
     * @param dataFim
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return valor do atributo idPerfilAcessoGrupo.
     */
    public Integer getIdPerfilAcessoGrupo() {
        return idPerfilAcessoGrupo;
    }

    /**
     * Define valor do atributo idPerfilAcessoGrupo.
     *
     * @param idPerfilAcessoGrupo
     */
    public void setIdPerfilAcessoGrupo(final Integer idPerfilAcessoGrupo) {
        this.idPerfilAcessoGrupo = idPerfilAcessoGrupo;
    }

}
