package br.com.centralit.citcorpore.bean;

import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class CargaCatalagoServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 2984986456849222230L;
    private Integer idCatalagoServico;
    private List<CategoriaServicoDTO> categoriaServicoDTO;
    private List<TipoDemandaDTO> tipoDemandaDTO;
    private List<TipoEventoServicoDTO> tipoEventoServicoDTO;

    /**
     * @return the idCatalagoServico
     */
    public Integer getIdCatalagoServico() {
        return idCatalagoServico;
    }

    /**
     * @param idCatalagoServico
     *            the idCatalagoServico to set
     */
    public void setIdCatalagoServico(final Integer idCatalagoServico) {
        this.idCatalagoServico = idCatalagoServico;
    }

    /**
     * @return the categoriaServicoDTO
     */
    public List<CategoriaServicoDTO> getCategoriaServicoDTO() {
        return categoriaServicoDTO;
    }

    /**
     * @param categoriaServicoDTO
     *            the categoriaServicoDTO to set
     */
    public void setCategoriaServicoDTO(final List<CategoriaServicoDTO> categoriaServicoDTO) {
        this.categoriaServicoDTO = categoriaServicoDTO;
    }

    /**
     * @return the tipoDemandaDTO
     */
    public List<TipoDemandaDTO> getTipoDemandaDTO() {
        return tipoDemandaDTO;
    }

    /**
     * @param tipoDemandaDTO
     *            the tipoDemandaDTO to set
     */
    public void setTipoDemandaDTO(final List<TipoDemandaDTO> tipoDemandaDTO) {
        this.tipoDemandaDTO = tipoDemandaDTO;
    }

    /**
     * @return the tipoEventoServicoDTO
     */
    public List<TipoEventoServicoDTO> getTipoEventoServicoDTO() {
        return tipoEventoServicoDTO;
    }

    /**
     * @param tipoEventoServicoDTO
     *            the tipoEventoServicoDTO to set
     */
    public void setTipoEventoServicoDTO(final List<TipoEventoServicoDTO> tipoEventoServicoDTO) {
        this.tipoEventoServicoDTO = tipoEventoServicoDTO;
    }

}
