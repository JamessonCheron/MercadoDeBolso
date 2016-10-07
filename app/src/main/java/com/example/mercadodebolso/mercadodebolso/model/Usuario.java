package com.example.mercadodebolso.mercadodebolso.model;

/**
 * Created by jamesson on 5/22/2016.
 */
public class Usuario {
    private long id;
    private String nome;
    private String login;
    private String senha;
    private int stayConnected;

    public long getIdUser() {
        return this.id;
    }

    public void setId(long novoId) {

        this.id = novoId;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStayConnected() {
        return this.stayConnected;
    }

    public void setStayConnected(int stayConnected) {
        this.stayConnected = stayConnected;
    }
}
