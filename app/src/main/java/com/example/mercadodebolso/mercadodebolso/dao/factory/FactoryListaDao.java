package com.example.mercadodebolso.mercadodebolso.dao.factory;

import android.content.Context;

import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceListaDao;
import com.example.mercadodebolso.mercadodebolso.dao.ListaDaoSQL;

/**
 * Created by jamesson on 5/3/2016.
 */
public class FactoryListaDao {

    public static InterfaceListaDao getInstance(String Id, Context context) {
        if( Id == null){
            return null;
        }
        if( Id.equalsIgnoreCase("SQL")){
                return new ListaDaoSQL(context);
        } else if (Id.equalsIgnoreCase("MEMORIA")) {
            //    return new  ListaMemoriaDAO();
        }


        return null;
    }
}

//http://www.oodesign.com/factory-pattern.html
