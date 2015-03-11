package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

@SuppressWarnings("serial")
public class EventoGrupoDTO extends BaseEntity {
	
	private Integer idEvento;
	private Integer idGrupo;

	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

}
