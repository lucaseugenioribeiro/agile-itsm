/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author valdoilo.damasceno
 *
 */
public class NotificacaoGrupoMonitDTO extends BaseEntity {

    private static final long serialVersionUID = 5032030660573765622L;

    private Integer idNotificacaoGrupoMonit;

    private Integer idMonitoramentoAtivos;

    private Integer idGrupo;

    private Date dataInicio;

    private Date dataFim;

    public Integer getIdNotificacaoGrupoMonit() {
        return idNotificacaoGrupoMonit;
    }

    public void setIdNotificacaoGrupoMonit(final Integer idNotificacaoGrupoMonit) {
        this.idNotificacaoGrupoMonit = idNotificacaoGrupoMonit;
    }

    public Integer getIdMonitoramentoAtivos() {
        return idMonitoramentoAtivos;
    }

    public void setIdMonitoramentoAtivos(final Integer idMonitoramentoAtivos) {
        this.idMonitoramentoAtivos = idMonitoramentoAtivos;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
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

}
