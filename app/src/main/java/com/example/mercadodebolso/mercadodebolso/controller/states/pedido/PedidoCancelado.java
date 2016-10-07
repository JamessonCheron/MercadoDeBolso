package com.example.mercadodebolso.mercadodebolso.controller.states.pedido;

import com.example.mercadodebolso.mercadodebolso.controller.interfaces.PedidoState;
import com.example.mercadodebolso.mercadodebolso.model.Pedido;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public class PedidoCancelado implements PedidoState{

    public Pedido pedido;

    public PedidoCancelado(Pedido pedido){
    this.pedido = pedido;
    }

    @Override
    public void pagarPedido() {
        //pedido cancelado não pode mais ser pago
    }

    @Override
    public void entregarPedido() {
        //pedido cancelado não pode ser entregue
    }

    @Override
    public void cancelarPedido() {
        //pedido cancelado não pode ser cancelado novamente
    }

    @Override
    public int getStatus() {
        return -1;
    }
}
