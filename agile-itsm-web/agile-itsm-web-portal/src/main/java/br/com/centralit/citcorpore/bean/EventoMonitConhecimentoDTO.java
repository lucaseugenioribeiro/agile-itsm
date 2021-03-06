/**
 *
 */
package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Vadoilo Damasceno
 *
 */
public class EventoMonitConhecimentoDTO extends BaseEntity {

    private static final long serialVersionUID = -2467321613106192462L;

    private Integer idEventoMonitoramento;

    private Integer idBaseConhecimento;

    /**
     * @return the idEventoMonitoramento
     */
    public Integer getIdEventoMonitoramento() {
        return idEventoMonitoramento;
    }

    /**
     * @param idEventoMonitoramento
     *            the idEventoMonitoramento to set
     */
    public void setIdEventoMonitoramento(final Integer idEventoMonitoramento) {
        this.idEventoMonitoramento = idEventoMonitoramento;
    }

    /**
     * @return the idBaseConhecimento
     */
    public Integer getIdBaseConhecimento() {
        return idBaseConhecimento;
    }

    /**
     * @param idBaseConhecimento
     *            the idBaseConhecimento to set
     */
    public void setIdBaseConhecimento(final Integer idBaseConhecimento) {
        this.idBaseConhecimento = idBaseConhecimento;
    }

}
