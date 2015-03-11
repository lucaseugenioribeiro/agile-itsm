package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Pedro
 *
 */
public class ControleContratoVersaoDTO extends BaseEntity {

    private Integer idControleContrato;
    private Integer idCcVersao;
    private String nomeCcVersao;
    
	public Integer getIdControleContrato() {
		return idControleContrato;
	}
	public void setIdControleContrato(Integer idControleContrato) {
		this.idControleContrato = idControleContrato;
	}
	public Integer getIdCcVersao() {
		return idCcVersao;
	}
	public void setIdCcVersao(Integer idCcVersao) {
		this.idCcVersao = idCcVersao;
	}
	public String getNomeCcVersao() {
		return nomeCcVersao;
	}
	public void setNomeCcVersao(String nomeCcVersao) {
		this.nomeCcVersao = nomeCcVersao;
	}
    
	
   

}