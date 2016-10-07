package com.example.mercadodebolso.mercadodebolso.dao;

/**
 * Created by Cais - Automação on 25/05/2016.
 */
public class ConfigMercado {

    private static ConfigMercado config = null;
    private int stayConnected = 0;

    private ConfigMercado(){
    }

    public static ConfigMercado getInstance(){
        if(config == null){
           new ConfigMercado();
        }
        return config;
    }

    public int getStayConnected() {
        return stayConnected;
    }

    public void setStayConnected(int stayConnected) {
        this.stayConnected = stayConnected;
    }
}
