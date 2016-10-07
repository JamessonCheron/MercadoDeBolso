package com.example.mercadodebolso.mercadodebolso.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Cais - Automação on 29/04/2016.
 */
public abstract class AbstractDao {
    protected SQLiteDatabase bd;
    protected String nomeTabela;

    public AbstractDao(Context context){
        BD auxBD = new BD(context);
        bd = auxBD.getWritableDatabase();
    }

    protected void setNomeTabela(String nome){
        this.nomeTabela = nome;
    }

    public int countRows(){
        Cursor cursor = bd.rawQuery("SELECT  * FROM " + nomeTabela, null);
        return cursor.getCount();
    }
}
