package com.example.mercadodebolso.mercadodebolso.controller.interfaces;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public interface PedidoState {

    void pagarPedido();

    void entregarPedido();

    void cancelarPedido();

    int getStatus();
}
