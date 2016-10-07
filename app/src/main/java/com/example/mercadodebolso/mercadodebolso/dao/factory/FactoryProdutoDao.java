package com.example.mercadodebolso.mercadodebolso.dao.factory;

import android.content.Context;

import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceProdutoDao;
import com.example.mercadodebolso.mercadodebolso.dao.ProdutoDaoSQL;

/**
 * Created by jamesson.ribeiro on 03/05/2016.
 */
public class FactoryProdutoDao {

    public static InterfaceProdutoDao getInstance(String Id, Context context) {
        if( Id == null){
            return null;
        }
        if( Id.equalsIgnoreCase("SQL")){
                return new ProdutoDaoSQL(context);
        } else if (Id.equalsIgnoreCase("MEMORIA")) {
            //    return new  ProdutoMemoriaDAO();
        }
        return null;
    }


}
