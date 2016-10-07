package com.example.mercadodebolso.mercadodebolso.controller.states.pedido;

import com.example.mercadodebolso.mercadodebolso.controller.interfaces.PedidoState;
import com.example.mercadodebolso.mercadodebolso.model.Pedido;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public class PedidoEntregue implements PedidoState {

    public Pedido pedido;

    public PedidoEntregue(Pedido pedido){
        this.pedido = pedido;
    }

    @Override
    public void pagarPedido() {
        //pedido entregue já foi pago
    }

    @Override
    public void entregarPedido() {
        //pedido não pode ser entregue duas vizes
    }

    @Override
    public void cancelarPedido() {
        //pedido entregue já foi concluido, não da mais pra cancelar
    }

    @Override
    public int getStatus() {
        return 1;
    }
}
