package com.example.mercadodebolso.mercadodebolso.dao.factory;

import android.content.Context;

import com.example.mercadodebolso.mercadodebolso.dao.ItemDaoSQL;
import com.example.mercadodebolso.mercadodebolso.dao.PedidoDaoSQL;
import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceItemDao;
import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfacePedidoDao;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public class FactoryPedidoDao {

    public static InterfacePedidoDao getInstance(String Id, Context context) {
        if( Id == null){
            return null;
        }
        if( Id.equalsIgnoreCase("SQL")){
            return new PedidoDaoSQL(context);
        } else if (Id.equalsIgnoreCase("MEMORIA")) {
            //    return new  ItemMemoriaDAO();
        }
        return null;
    }

}
