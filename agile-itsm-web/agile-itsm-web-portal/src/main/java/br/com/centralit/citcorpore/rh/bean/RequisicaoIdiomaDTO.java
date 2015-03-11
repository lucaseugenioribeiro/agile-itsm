package br.com.centralit.citcorpore.rh.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RequisicaoIdiomaDTO extends BaseEntity {
	private Integer idSolicitacaoServico;
	private Integer idIdioma;
	private String obrigatorio;
	
	private String descricao;
	private String detalhe;
	

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public Integer getIdSolicitacaoServico(){
		return this.idSolicitacaoServico;
	}
	public void setIdSolicitacaoServico(Integer parm){
		this.idSolicitacaoServico = parm;
	}

	public Integer getIdIdioma(){
		return this.idIdioma;
	}
	public void setIdIdioma(Integer parm){
		this.idIdioma = parm;
	}

	public String getObrigatorio(){
		return this.obrigatorio;
	}
	public void setObrigatorio(String parm){
		this.obrigatorio = parm;
	}

}