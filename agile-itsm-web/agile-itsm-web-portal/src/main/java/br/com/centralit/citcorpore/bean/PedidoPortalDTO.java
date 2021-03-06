package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class PedidoPortalDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idPedidoPortal;
    private Integer idEmpregado;
    private Date dataPedido;
    private Double precoTotal;
    private String status;
    private List<ServicoContratoDTO> listaServicoContrato;
    private List<SolicitacaoServicoDTO> listaSolicitacoes;

    public Integer getIdPedidoPortal() {
        return idPedidoPortal;
    }

    public void setIdPedidoPortal(final Integer idPedidoPortal) {
        this.idPedidoPortal = idPedidoPortal;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(final Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(final Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public List<SolicitacaoServicoDTO> getListaSolicitacoes() {
        return listaSolicitacoes;
    }

    public void setListaSolicitacoes(final List<SolicitacaoServicoDTO> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }

    public List<ServicoContratoDTO> getListaServicoContrato() {
        return listaServicoContrato;
    }

    public void setListaServicoContrato(final List<ServicoContratoDTO> listaServicoContrato) {
        this.listaServicoContrato = listaServicoContrato;
    }

}
