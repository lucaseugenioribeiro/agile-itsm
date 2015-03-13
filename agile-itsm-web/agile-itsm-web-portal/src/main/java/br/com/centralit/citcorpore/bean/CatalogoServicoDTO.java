package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

/**
 *
 * @author pedro
 *
 */
public class CatalogoServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 1582364224581163482L;

    private Integer idCatalogoServico;
    private String tituloCatalogoServico;
    private Integer idContrato;
    private Date dataInicio;
    private Date dataFim;
    private String obs;
    private String nomeServico;
    private String servicoSerialize;
    private String infoCatalogoServicoSerialize;

    private String descInfoCatalogoServico;
    private String nomeInfoCatalogoServico;
    private Integer idInfoCatalogoServico;
    private Integer idServicoContrato;

    private Integer rowIndex;

    private String nomeContrato;

    private List<ServContratoCatalogoServDTO> colServicoContrato;
    private List<InfoCatalogoServicoDTO> colInfoCatalogoServico;

    public String getDescInfoCatalogoServico() {
        return descInfoCatalogoServico;
    }

    public void setDescInfoCatalogoServico(final String descInfoCatalogoServico) {
        this.descInfoCatalogoServico = descInfoCatalogoServico;
    }

    public String getNomeInfoCatalogoServico() {
        return nomeInfoCatalogoServico;
    }

    public void setNomeInfoCatalogoServico(final String nomeInfoCatalogoServico) {
        this.nomeInfoCatalogoServico = nomeInfoCatalogoServico;
    }

    public Integer getIdCatalogoServico() {
        return idCatalogoServico;
    }

    public void setIdCatalogoServico(final Integer idCatalogoServico) {
        this.idCatalogoServico = idCatalogoServico;
    }

    public String getTituloCatalogoServico() {
        return tituloCatalogoServico;
    }

    public void setTituloCatalogoServico(final String tituloCatalogoServico) {
        this.tituloCatalogoServico = tituloCatalogoServico;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(final String obs) {
        this.obs = obs;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getServicoSerialize() {
        return servicoSerialize;
    }

    public void setServicoSerialize(final String servicoSerialize) {
        this.servicoSerialize = servicoSerialize;
    }

    public String getInfoCatalogoServicoSerialize() {
        return infoCatalogoServicoSerialize;
    }

    public void setInfoCatalogoServicoSerialize(final String infoCatalogoServicoSerialize) {
        this.infoCatalogoServicoSerialize = infoCatalogoServicoSerialize;
    }

    public List<ServContratoCatalogoServDTO> getColServicoContrato() {
        return colServicoContrato;
    }

    public void setColServicoContrato(final List<ServContratoCatalogoServDTO> colServicoContrato) {
        this.colServicoContrato = colServicoContrato;
    }

    public List<InfoCatalogoServicoDTO> getColInfoCatalogoServico() {
        return colInfoCatalogoServico;
    }

    public void setColInfoCatalogoServico(final List<InfoCatalogoServicoDTO> colInfoCatalogoServico) {
        this.colInfoCatalogoServico = colInfoCatalogoServico;
    }

    public String getNomeContrato() {
        return nomeContrato;
    }

    public void setNomeContrato(final String nomeContrato) {
        this.nomeContrato = nomeContrato;
    }

    public Integer getIdInfoCatalogoServico() {
        return idInfoCatalogoServico;
    }

    public void setIdInfoCatalogoServico(final Integer idInfoCatalogoServico) {
        this.idInfoCatalogoServico = idInfoCatalogoServico;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(final Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
    }

}
