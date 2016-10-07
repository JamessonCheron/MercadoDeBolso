package com.example.mercadodebolso.mercadodebolso.model;

/**
 * Created by jamesson on 4/5/2016.
 */

import java.util.ArrayList;

public class Lista {

    private long id;
    private long idUsuario;
    private String nomeLista;
    private ArrayList<Item> itens = new ArrayList();


    public Lista(){

    }

    public Lista(String nome) {
        this.nomeLista = nome;
    }

    public void addItem(Item novoItem) {
        this.itens.add(novoItem);
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public Double getPrecoLista() {
        double precoTotal = 0;
        for (Item atual : itens) {
            precoTotal += atual.getQtd() * atual.getProduto().getPreco();
        }
        return precoTotal;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long novoId) {
        this.id = novoId;
    }

    public String getNome() {
        return this.nomeLista;
    }

    public int getQtdItens() {
        return this.itens.size();
    }

    public void setNome(String nome) {
        this.nomeLista = nome;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
}

