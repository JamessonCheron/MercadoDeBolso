package com.example.mercadodebolso.mercadodebolso.model;

/**
 * Created by jamesson on 4/5/2016.
 */

public class Item {
    private long idItem;
    private long idLista;
    private long idProduto;
    private int qtd;
    private Produto produto;

    public Item(){

    }

    public Item(long idLista, Produto produto, int qtd) {
        this.idProduto = produto.getId();
        this.qtd = qtd;
        this.idLista = idLista;
        this.produto = produto;
    }

    public long getIdLista() {
        return idLista;
    }

    public int getQtd() {
        return this.qtd;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public long getIdItem() {
        return this.idItem;
    }

    public double getPrecoItem() {
        return qtd * produto.getPreco();
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public long getId() {
        return idItem;
    }

    public void setId(long id) {
        this.idItem = id;
    }

    public void setListaId(long listaId) {
        this.idLista = listaId;
    }

    public void setProdutoId(long produtoId) {
        this.idProduto = produtoId;
    }
}
