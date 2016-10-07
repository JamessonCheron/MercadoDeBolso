package com.example.mercadodebolso.mercadodebolso.controller;

/**
 * Created by jamesson.ribeiro on 05/04/2016.
 */

import android.content.Context;

import com.example.mercadodebolso.mercadodebolso.dao.factory.FactoryItemDao;
import com.example.mercadodebolso.mercadodebolso.dao.factory.FactoryListaDao;
import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceItemDao;
import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceListaDao;
import com.example.mercadodebolso.mercadodebolso.model.Item;
import com.example.mercadodebolso.mercadodebolso.model.Lista;
import com.example.mercadodebolso.mercadodebolso.model.Pedido;
import com.example.mercadodebolso.mercadodebolso.model.Produto;
import com.example.mercadodebolso.mercadodebolso.sistema.LoginInfo;

import java.util.ArrayList;

public class ListaController {
    private ProdutoController produtoController;
    private InterfaceListaDao listaDao;
    private InterfaceItemDao itemDao;
    private Context context;
    private LoginInfo sistema;
    private long idUsuario;

    public ListaController(Context context){
        this.context = context;
        listaDao = FactoryListaDao.getInstance("SQL", context);
        itemDao = FactoryItemDao.getInstance("SQL", context);
        produtoController = new ProdutoController(context);
        sistema = LoginInfo.getInstance(context);
        idUsuario = sistema.getUserConnected();
    }

    public int getQtdListasValidas(){
        ArrayList <Lista> listasValidas = getListasValidas();
        return listasValidas.size();
    }

    public void addItem(long idLista, Produto produto, int qtd){
        Lista lista = (Lista) listaDao.getById(idLista);
        Item item = itemDao.getByProdutoLista(idLista, produto.getId());
        int qtdSoma;
        if(item == null){
            long id;
            item = new Item(idLista, produto, qtd);
            id = itemDao.inserir(item);
            item.setId(id);
            lista.addItem(item);
        }else{
            //somar quantidades
            qtdSoma = qtd + item.getQtd();
            editItem(item.getIdItem(), qtdSoma);
        }
    }

    public Lista criarLista(String nomeLista){
        Lista lista = listaDao.buscarPorNomeUnico(idUsuario, nomeLista);
        if(lista == null){
            lista = new Lista();
            lista.setNome(nomeLista);
            lista.setIdUsuario(idUsuario);
            long id = listaDao.inserir(lista);
            lista.setId(id);
            return lista;
        } else{
            return null;
        }
    }

    public boolean existe(String nomeLista) {
        if(listaDao.buscarPorNomeUnico(idUsuario, nomeLista) == null){
            return false;
        } else{
            return true;
        }
    }

    public Lista getLista(long idLista) {
        Lista lista = (Lista) listaDao.getById(idLista);
        preencherLista(lista);
        return lista;
    }

    private void preencherLista(Lista lista){
        Produto produto;
        ArrayList<Item> itens = itemDao.buscar(lista.getId());

        for(Item atual: itens) {//carrega o produto de cada item, a apartir do id do produto
            produto = produtoController.getProduto(atual.getIdProduto());
            atual.setProduto(produto);
        }
        lista.setItens(itens); //carrega o array de itens na lista
    }

    public ArrayList<Lista> getAll() {
        ArrayList<Lista> listas = listaDao.listarTudo(idUsuario);
        for(Lista atual: listas){
            preencherLista(atual);
        }
        return listas;
    }

    public void editItem(long idItem, int quantidade) {
        Item temp = (Item) itemDao.getById(idItem);
        temp.setQtd(quantidade);
        itemDao.update(temp);
    }

    public void removerItem(long id) {
        itemDao.remover(id);
    }

    public void removerLista(long id) {
        listaDao.remover(id);
    }


    public ArrayList<Lista> getListasValidas() {
        PedidoController pedidoController = new PedidoController(context);
        ArrayList<Lista> listas = getAll();
        ArrayList<Long> idsListasEntregues = pedidoController.getListasPedidosEntreguesIds();
        ArrayList<Lista> listasValidas = new ArrayList();
        for(Lista atual : listas) {
            if (idsListasEntregues.contains(atual.getId()) == false) {
                listasValidas.add(atual);
            }
        }

        return listasValidas;
    }
}
