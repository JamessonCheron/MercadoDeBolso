package com.example.mercadodebolso.mercadodebolso.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cais - Automação on 26/04/2016.
 */
public class BD extends SQLiteOpenHelper {
    private static final String NOME_BD = "BD_MercadoDeBolso";
    private static final int VERSAO_BD = 1;

    public BD(Context ctx){
        super(ctx, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table produto(_id integer primary key autoincrement, nome text not null, preco double not null);");
        bd.execSQL("create table lista(_id integer primary key autoincrement, id_usuario integer not null, nome text not null);");
        bd.execSQL("create table item(_id integer primary key autoincrement, id_lista integer not null, id_produto integer not null, qtd integer not null);");
        bd.execSQL("create table usuario(_id integer primary key autoincrement, nome text not null, login text not null, senha text not null);");
        bd.execSQL("create table config(_id integer primary key, id_user_connected integer not null);");
        bd.execSQL("create table pedido(_id integer primary key autoincrement, id_usuario integer not null, id_lista integer not null, status integer not null, taxa integer not null, frete integer not null, preco_total integer not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table produto;");
        bd.execSQL("drop table lista;");
        bd.execSQL("drop table item");
        bd.execSQL("drop table usuario");
        bd.execSQL("drop table config");
        bd.execSQL("drop table pedido");
        onCreate(bd);
    }


}
