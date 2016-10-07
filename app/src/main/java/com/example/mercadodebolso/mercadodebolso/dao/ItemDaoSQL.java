package com.example.mercadodebolso.mercadodebolso.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceItemDao;
import com.example.mercadodebolso.mercadodebolso.model.Item;

import java.util.ArrayList;

/**
 * Created by Cais - Automação on 29/04/2016.
 * Modified by LUCAS on 03/04/2016.
 *
 */
public class ItemDaoSQL extends AbstractDao implements InterfaceItemDao {

    public ItemDaoSQL(Context context){
        super(context);
        setNomeTabela("item");
    }

    @Override
    public long inserir(Object obj) {
        Item temp = (Item) obj;
        ContentValues valores = new ContentValues();
        valores.put("id_lista", temp.getIdLista());
        valores.put("id_produto", temp.getIdProduto());
        valores.put("qtd", temp.getQtd());

        return bd.insert(nomeTabela, null, valores);
    }

    @Override
    public void remover(long id) {
        bd.delete(nomeTabela, "_id = " + id, null);
    }

    @Override
    public void update(Object obj) {
        Item temp = (Item) obj;
        ContentValues valores = new ContentValues();
        valores.put("id_lista", temp.getIdLista());
        valores.put("id_produto", temp.getIdProduto());
        valores.put("qtd", temp.getQtd());

        bd.update(nomeTabela, valores, "_id = ?", new String[]{"" + temp.getId()});
    }

    @Override
    public ArrayList listarTudo() {
        ArrayList<Item> list = new ArrayList();
        String[] colunas = new String[]{"_id", "id_lista", "id_produto", "qtd"};

        Cursor cursor = bd.query(nomeTabela, colunas, null, null, null, null, "nome ASC");

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Item temp = new Item();
                temp.setId(cursor.getInt(0));
                temp.setListaId(cursor.getInt(1));
                temp.setProdutoId(cursor.getInt(2));
                temp.setQtd(cursor.getInt(3));
                list.add(temp);

            }while (cursor.moveToNext());
        }

        return(list);
    }

    @Override
    public Object getById(long id) {
        String[] colunas = new String[]{"_id", "id_lista", "id_produto", "qtd"};

        Cursor cursor = bd.query(nomeTabela, colunas, "_id=?", new String[]{Long.toString(id)}, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Item temp = new Item();
            temp.setId(cursor.getInt(0));
            temp.setListaId(cursor.getInt(1));
            temp.setProdutoId(cursor.getInt(2));
            temp.setQtd(cursor.getInt(3));

            return (temp);
        } else{
            return null;
        }
    }

    @Override
    public ArrayList buscar(Object chaveBusca) {
        String idLista = chaveBusca.toString();
        ArrayList<Item> list = new ArrayList();
        String[] colunas = new String[]{"_id", "id_lista", "id_produto", "qtd"};
        Cursor cursor = bd.query(nomeTabela, colunas, "id_lista = ?", new String[]{idLista}, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Item temp = new Item();
                temp.setId(cursor.getInt(0));
                temp.setListaId(cursor.getInt(1));
                temp.setProdutoId(cursor.getInt(2));
                temp.setQtd(cursor.getInt(3));
                list.add(temp);

            }while (cursor.moveToNext());
        }
        return list;
    }

    public Item getByProdutoLista(long idLista, long idProduto) {
        String[] colunas = new String[]{"_id", "id_lista", "id_produto", "qtd"};
        Cursor cursor = bd.query(nomeTabela, colunas, "id_lista = ? AND id_produto = ?", new String[]{Long.toString(idLista), Long.toString(idProduto)}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Item temp = new Item();
            temp.setId(cursor.getInt(0));
            temp.setListaId(cursor.getInt(1));
            temp.setProdutoId(cursor.getInt(2));
            temp.setQtd(cursor.getInt(3));

            return (temp);
        } else{
            return null;
        }
    }
}
