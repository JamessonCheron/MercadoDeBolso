package com.example.mercadodebolso.mercadodebolso.sistema;

import android.content.Context;

import com.example.mercadodebolso.mercadodebolso.dao.ConfigDaoSQL;

/**
 * Created by Cais - Automação on 25/05/2016.
 */
public class LoginInfo {

    private static LoginInfo sistema = null;
    private long idUserConnected;
    private ConfigDaoSQL configDao;
    private LoginInfo(Context context){
        configDao = new ConfigDaoSQL(context);
    }

    public static LoginInfo getInstance(Context context){
        if(sistema == null){
            sistema = new LoginInfo(context);
        }
        return sistema;
    }

    public long getUserConnected() {
        return idUserConnected;
    }

    public void setUserConnected(long idUserConnected) {
        if(configDao.countRows() == 0){
            configDao.inserir(idUserConnected);
        } else{
            configDao.update(idUserConnected);
        }
        this.idUserConnected = idUserConnected;
    }

    public void start() {
        idUserConnected = configDao.getById(1);
    }
}
