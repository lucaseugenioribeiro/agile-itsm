package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class GlosaServicoContratoDTO extends BaseEntity {

    private static final long serialVersionUID = -2841631071356995758L;

    private Integer idGlosaServicoContrato;
    private Integer idServicoContrato;
    private Integer quantidadeGlosa;
    private Date dataFim;

    public Integer getIdGlosaServicoContrato() {
        return idGlosaServicoContrato;
    }

    public void setIdGlosaServicoContrato(final Integer idGlosaServicoContrato) {
        this.idGlosaServicoContrato = idGlosaServicoContrato;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
    }

    public Integer getQuantidadeGlosa() {
        return quantidadeGlosa;
    }

    public void setQuantidadeGlosa(final Integer quantidadeGlosa) {
        this.quantidadeGlosa = quantidadeGlosa;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
