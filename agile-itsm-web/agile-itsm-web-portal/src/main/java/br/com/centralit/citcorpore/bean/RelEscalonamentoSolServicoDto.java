package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
/**
 * 
 * @author flavio.santana
 *
 */
public class RelEscalonamentoSolServicoDto extends BaseEntity {

	/**
	 * Relaciona as solicita��es de servi�o ao escalonamento
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idSolicitacaoServico;
	private Integer idEscalonamento;
	
	public Integer getIdSolicitacaoServico() {
		return idSolicitacaoServico;
	}
	public void setIdSolicitacaoServico(Integer idSolicitacaoServico) {
		this.idSolicitacaoServico = idSolicitacaoServico;
	}
	public Integer getIdEscalonamento() {
		return idEscalonamento;
	}
	public void setIdEscalonamento(Integer idEscalonamento) {
		this.idEscalonamento = idEscalonamento;
	}
	

}