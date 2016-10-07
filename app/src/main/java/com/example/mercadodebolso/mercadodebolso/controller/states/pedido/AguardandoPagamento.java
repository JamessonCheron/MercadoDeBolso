package com.example.mercadodebolso.mercadodebolso.controller.states.pedido;

import com.example.mercadodebolso.mercadodebolso.controller.interfaces.PedidoState;
import com.example.mercadodebolso.mercadodebolso.model.Pedido;
import com.example.mercadodebolso.mercadodebolso.util.Util;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public class AguardandoPagamento implements PedidoState {

    Pedido pedido;

    public AguardandoPagamento(Pedido pedido){
        this.pedido = pedido;
    }

    @Override
    public void pagarPedido() {
        pedido.setPedidoState(pedido.getStatusPedigoEntregue());
    }

    @Override
    public void entregarPedido() {
        //não pode enregar antes de ser pago
    }

    @Override
    public void cancelarPedido() {
        pedido.setPedidoState(pedido.getStatusPedidoCancelado());
    }

    @Override
    public int getStatus() {
        return 0;
    }
}
