package com.example.mercadodebolso.mercadodebolso.model;

import com.example.mercadodebolso.mercadodebolso.controller.interfaces.PedidoState;
import com.example.mercadodebolso.mercadodebolso.controller.states.pedido.AguardandoPagamento;
import com.example.mercadodebolso.mercadodebolso.controller.states.pedido.PedidoCancelado;
import com.example.mercadodebolso.mercadodebolso.controller.states.pedido.PedidoEntregue;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public class Pedido implements PedidoState{

    private long idPedido;
    private long idUsuario;
    private long idLista;
    private double taxa;
    private double frete;
    private double precoTotal;
    private Lista lista;

    PedidoState statusPedido; // pode ser um dos status abaixo
    PedidoState aguardandoPagamento; //0
    PedidoState pedidoCancelado; //-1
    PedidoState pedidoEntregue; //1

    public Pedido(){
        aguardandoPagamento = new AguardandoPagamento(this);
        pedidoCancelado = new PedidoCancelado(this);
        pedidoEntregue = new PedidoEntregue(this);
    }


    public void setStatusPedido(int statusPedido){
        switch(statusPedido){
            case  0: setPedidoState(aguardandoPagamento); break;
            case 1: setPedidoState(pedidoEntregue); break;
            case  -1: setPedidoState(pedidoCancelado); break;
        }
    }

    public int getStatusPedido(){
        return statusPedido.getStatus();
    }

    public void setPedidoState(PedidoState novoStatus){
        statusPedido = novoStatus;
    }


    @Override
    public void pagarPedido() {
        statusPedido.pagarPedido();
    }

    @Override
    public void entregarPedido() {
        statusPedido.entregarPedido();
    }

    @Override
    public void cancelarPedido() {
        statusPedido.cancelarPedido();
    }

    @Override
    public int getStatus() {
        return statusPedido.getStatus();
    }

    public PedidoState getStatusAguardandoPagamento(){
        return aguardandoPagamento;
    }
    public PedidoState getStatusPedigoEntregue(){
        return pedidoEntregue;
    }
    public PedidoState getStatusPedidoCancelado(){
        return pedidoCancelado;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdLista() {
        return idLista;
    }

    public void setIdLista(long idLista) {
        this.idLista = idLista;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public void calculaPrecoTotal() {
        precoTotal = lista.getPrecoLista() + frete;
    }

    public String getStatusNome() {
        String status = null;
        switch (statusPedido.getStatus()){
            case  0: status = "Aguardando pagamento"; break;
            case -1: status = "Cancelado"; break;
            case  1: status = "Entregue"; break;
        }
        return status;
    }
}
