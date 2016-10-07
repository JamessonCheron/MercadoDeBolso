package com.example.mercadodebolso.mercadodebolso.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceConfigMercadoDao;
import com.example.mercadodebolso.mercadodebolso.model.Lista;

/**
 * Created by Cais - Automação on 25/05/2016.
 */
public class ConfigDaoSQL extends AbstractDao implements InterfaceConfigMercadoDao {

    public ConfigDaoSQL(Context context){
        super(context); //context null
        setNomeTabela("config");
    }

    @Override
    public void inserir(long id_usuario) {
        ContentValues valores = new ContentValues();
        valores.put("_id", 1);
        valores.put("id_user_connected", id_usuario);

        bd.insert(nomeTabela, null, valores);
    }

    @Override
    public long getById(long id) {
        String[] colunas = new String[]{"_id", "id_user_connected"};

        Cursor cursor = bd.query(nomeTabela, colunas, "_id=?", new String[]{Long.toString(id)}, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return Long.parseLong(cursor.getString(1));
        } else{
            return 0;
        }
    }

    @Override
    public void update(long id) {
        ContentValues valores = new ContentValues();
        valores.put("_id", 1);
        valores.put("id_user_connected", id);

        bd.update(nomeTabela, valores, "_id = ?", new String[]{"1"});
    }

}
