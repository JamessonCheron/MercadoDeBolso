package com.example.mercadodebolso.mercadodebolso.model;

/**
 * Created by jamesson on 4/5/2016.
 */

public class Produto {
    //atributos
    private long id;
    private String nome;
    private double preco;

    //construtores
    public Produto() {

    }
    public Produto(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }
    public Produto(String nome, double preco, int id){
        this.nome = nome;
        this.preco = preco;
        this.id = id;
    }

    //metodos
    public String getNome(){
        return this.nome;
    }

    public long getId() {
        return id;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
