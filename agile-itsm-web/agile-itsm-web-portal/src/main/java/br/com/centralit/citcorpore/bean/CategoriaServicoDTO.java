package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author rosana.godinho
 *
 */
public class CategoriaServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 2984986456849222230L;
    private Integer idCategoriaServico;
    private Integer idCategoriaServicoPai;
    private Integer idEmpresa;
    private String nomeCategoriaServico;
    private Date dataInicio;
    private Date dataFim;
    private String nomeCategoriaServicoConcatenado;
    private String nomeCategoriaServicoPai;

    private int nivel;

    /**
     * @return valor do atributo idCategoriaServico.
     */
    public Integer getIdCategoriaServico() {
        return idCategoriaServico;
    }

    /**
     * Define valor do atributo idCategoriaServico.
     *
     * @param idCategoriaServico
     */
    public void setIdCategoriaServico(final Integer idCategoriaServico) {
        this.idCategoriaServico = idCategoriaServico;
    }

    /**
     * @return valor do atributo idCategoriaServicoPai.
     */
    public Integer getIdCategoriaServicoPai() {
        return idCategoriaServicoPai;
    }

    /**
     * Define valor do atributo idCategoriaServicoPai.
     *
     * @param idCategoriaServicoPai
     */
    public void setIdCategoriaServicoPai(final Integer idCategoriaServicoPai) {
        this.idCategoriaServicoPai = idCategoriaServicoPai;
    }

    /**
     * @return valor do atributo idEmpresa.
     */
    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Define valor do atributo idEmpresa.
     *
     * @param idEmpresa
     */
    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return valor do atributo nomeCategoriaServico.
     */
    public String getNomeCategoriaServico() {
        return nomeCategoriaServico;
    }

    public String getNomeCategoriaServicoNivel() {
        if (this.getNomeCategoriaServico() == null) {
            return nomeCategoriaServico;
        }
        String str = "";
        for (int i = 0; i < this.getNivel(); i++) {
            str += "....";
        }
        return str + nomeCategoriaServico;
    }

    /**
     * Define valor do atributo nomeCategoriaServico.
     *
     * @param nomeCategoriaServico
     */
    public void setNomeCategoriaServico(final String nomeCategoriaServico) {
        this.nomeCategoriaServico = nomeCategoriaServico;
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(final int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the nomeCatServicoConcatenado
     */
    public String getNomeCategoriaServicoConcatenado() {
        return nomeCategoriaServicoConcatenado;
    }

    /**
     * @param nomeCatServicoConcatenado
     *            the nomeCatServicoConcatenado to set
     */
    public void setNomeCategoriaServicoConcatenado(final String nomeCategoriaServicoConcatenado) {
        this.nomeCategoriaServicoConcatenado = nomeCategoriaServicoConcatenado;
    }

    /**
     * @return the nomeCategoriaServicoPai
     */
    public String getNomeCategoriaServicoPai() {
        return nomeCategoriaServicoPai;
    }

    /**
     * @param nomeCategoriaServicoPai
     *            the nomeCategoriaServicoPai to set
     */
    public void setNomeCategoriaServicoPai(final String nomeCategoriaServicoPai) {
        this.nomeCategoriaServicoPai = nomeCategoriaServicoPai;
    }

}
