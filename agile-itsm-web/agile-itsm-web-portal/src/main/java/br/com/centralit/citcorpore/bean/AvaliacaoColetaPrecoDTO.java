package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AvaliacaoColetaPrecoDTO extends BaseEntity {
	private Integer idCriterio;
	private Integer idColetaPreco;
	private Integer avaliacao;
	
	public Integer getIdCriterio(){
		return this.idCriterio;
	}
	public void setIdCriterio(Integer parm){
		this.idCriterio = parm;
	}

	public Integer getAvaliacao(){
		return this.avaliacao;
	}
	public void setAvaliacao(Integer parm){
		this.avaliacao = parm;
	}
    public Integer getIdColetaPreco() {
        return idColetaPreco;
    }
    public void setIdColetaPreco(Integer idColetaPreco) {
        this.idColetaPreco = idColetaPreco;
    }

}
