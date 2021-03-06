/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author valdoilo.damasceno
 * @since 13.06.2014
 */
public class ScriptMonitDTO extends BaseEntity {

    private static final long serialVersionUID = -272158181369701516L;

    private Integer idScriptMonit;

    private Integer idMonitoramentoAtivos;

    private String script;

    private Date dataInicio;

    private Date DataFim;

    public Integer getIdScriptMonit() {
        return idScriptMonit;
    }

    public void setIdScriptMonit(final Integer idScriptMonit) {
        this.idScriptMonit = idScriptMonit;
    }

    public Integer getIdMonitoramentoAtivos() {
        return idMonitoramentoAtivos;
    }

    public void setIdMonitoramentoAtivos(final Integer idMonitoramentoAtivos) {
        this.idMonitoramentoAtivos = idMonitoramentoAtivos;
    }

    public String getScript() {
        return script;
    }

    public void setScript(final String script) {
        this.script = script;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return DataFim;
    }

    public void setDataFim(final Date dataFim) {
        DataFim = dataFim;
    }

}
