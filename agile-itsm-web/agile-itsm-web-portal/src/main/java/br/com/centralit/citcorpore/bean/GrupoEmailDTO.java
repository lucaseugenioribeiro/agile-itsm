package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Util;

public class GrupoEmailDTO extends BaseEntity {

	private static final long serialVersionUID = -4079973225632843658L;
	private Integer idGrupo;
	private Integer idEmpregado;
	private String nome;
	private String email;
	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public Integer getIdEmpregado() {
		return idEmpregado;
	}
	public void setIdEmpregado(Integer idEmpregado) {
		this.idEmpregado = idEmpregado;
	}
	public String getNome() {
		return Util.tratarAspasSimples(this.nome);
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
