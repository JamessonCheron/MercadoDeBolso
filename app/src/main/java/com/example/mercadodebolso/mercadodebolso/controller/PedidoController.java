package com.example.mercadodebolso.mercadodebolso.controller;

import android.content.Context;

import com.example.mercadodebolso.mercadodebolso.dao.PedidoDaoSQL;
import com.example.mercadodebolso.mercadodebolso.dao.factory.FactoryItemDao;
import com.example.mercadodebolso.mercadodebolso.dao.factory.FactoryPedidoDao;
import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfacePedidoDao;
import com.example.mercadodebolso.mercadodebolso.model.Lista;
import com.example.mercadodebolso.mercadodebolso.model.Pedido;
import com.example.mercadodebolso.mercadodebolso.sistema.LoginInfo;

import java.util.ArrayList;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public class PedidoController {
    private Context context;
    private InterfacePedidoDao pedidoDao;
    private long idUsuario;
    private LoginInfo sistema;

    public PedidoController(Context context){
        this.context = context;

        pedidoDao = FactoryPedidoDao.getInstance("SQL", context);
        sistema = LoginInfo.getInstance(context);
        idUsuario = sistema.getUserConnected();
    }

    public Pedido novoPedido(long idUsuario, long idLista) {
        ListaController listaController = new ListaController(context);
        long idPedido;
        Pedido novo = new Pedido();
        novo.setIdLista(idLista);
        novo.setIdUsuario(idUsuario);
        novo.setLista(listaController.getLista(idLista));
        novo.setStatusPedido(0);
        novo.setTaxa(getTaxaAtual());
        novo.setFrete(getFrete());
        novo.calculaPrecoTotal();
        idPedido = pedidoDao.inserir(novo);
        novo.setIdPedido(idPedido);
        return novo;
    }

    public double getTaxaAtual() {
        return 0.03;
    }

    public double getFrete() {
        return 20;
    }

    public Pedido getPedido(long idPedido) {
        Pedido pedido = (Pedido) pedidoDao.getById(idPedido);
        return pedido;
    }

    public void update(Pedido pedidoAtual) {
        pedidoDao.update(pedidoAtual);
    }

    public ArrayList<Pedido> getAll() {
        ArrayList<Pedido> pedidos = pedidoDao.listarTudo(idUsuario);
        return pedidos;
    }

    public ArrayList<Long> getListasPedidosEntreguesIds() {
        ArrayList<Long> pedidos = pedidoDao.listarIdsListasEntregues(idUsuario);
        return pedidos;
    }
}
