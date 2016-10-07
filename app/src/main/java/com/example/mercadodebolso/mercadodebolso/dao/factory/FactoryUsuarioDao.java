package com.example.mercadodebolso.mercadodebolso.dao.factory;

import android.content.Context;

import com.example.mercadodebolso.mercadodebolso.dao.UsuarioDaoSQL;
import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceUsuarioDao;

/**
 * Created by jamesson on 5/22/2016.
 */
public class FactoryUsuarioDao {

    public static InterfaceUsuarioDao getInstance(String Id, Context context) {
        if( Id == null){
            return null;
        }
        if( Id.equalsIgnoreCase("SQL")){
            return new UsuarioDaoSQL(context);
        } else if (Id.equalsIgnoreCase("MEMORIA")) {
            //    return new  ItemMemoriaDAO();
        }
        return null;
    }


}
