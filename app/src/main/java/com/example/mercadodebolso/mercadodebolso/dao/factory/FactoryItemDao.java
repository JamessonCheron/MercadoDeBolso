package com.example.mercadodebolso.mercadodebolso.dao.factory;

import android.content.Context;

import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceItemDao;
import com.example.mercadodebolso.mercadodebolso.dao.ItemDaoSQL;

/**
 * Created by jamesson.ribeiro on 03/05/2016.
 */
public class FactoryItemDao {

    public static InterfaceItemDao getInstance(String Id, Context context) {
        if( Id == null){
            return null;
        }
        if( Id.equalsIgnoreCase("SQL")){
                return new ItemDaoSQL(context);
        } else if (Id.equalsIgnoreCase("MEMORIA")) {
            //    return new  ItemMemoriaDAO();
        }
        return null;
    }

}
