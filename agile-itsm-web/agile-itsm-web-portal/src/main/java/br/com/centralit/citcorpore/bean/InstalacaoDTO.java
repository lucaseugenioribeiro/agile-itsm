package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author flavio.santana
 *
 */
public class InstalacaoDTO extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Integer idInstalacao;
	private String sucesso;
	private String passo;
   
	public String getSucesso() {
		return sucesso;
	}
	public void setSucesso(String sucesso) {
		this.sucesso = sucesso;
	}
	public String getPasso() {
		return passo;
	}
	public void setPasso(String passo) {
		this.passo = passo;
	}
	public Integer getIdInstalacao() {
		return idInstalacao;
	}
	public void setIdInstalacao(Integer idInstalacao) {
		this.idInstalacao = idInstalacao;
	}

}