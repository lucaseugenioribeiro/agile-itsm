/**
 * 
 */
package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Centralit
 * 
 */
public class ContratosGruposDTO extends BaseEntity {

	private static final long serialVersionUID = -5497970768833161287L;

	private Integer idGrupo;

	private Integer idContrato;

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Integer getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

}
