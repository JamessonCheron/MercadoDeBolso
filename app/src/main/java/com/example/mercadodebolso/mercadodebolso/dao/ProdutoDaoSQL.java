package com.example.mercadodebolso.mercadodebolso.dao;

/**
 * Created by jamesson on 4/5/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceProdutoDao;
import com.example.mercadodebolso.mercadodebolso.model.Produto;

import java.util.ArrayList;

public class ProdutoDaoSQL extends AbstractDao implements InterfaceProdutoDao {

    public ProdutoDaoSQL(Context context){
        super(context);
        setNomeTabela("produto");
    }

    @Override
    public long inserir(Object obj) {
        Produto temp = (Produto) obj;
        ContentValues valores = new ContentValues();
        valores.put("nome", temp.getNome());
        valores.put("preco", temp.getPreco());

        return bd.insert(nomeTabela, null, valores);
    }

    @Override
    public void remover(long id) {
        bd.delete(nomeTabela, "_id = " + id, null);
    }

    @Override
    public void update(Object obj) {
        Produto temp = (Produto) obj;
        ContentValues valores = new ContentValues();
        valores.put("nome", temp.getNome());
        valores.put("preco", temp.getPreco());

        bd.update(nomeTabela, valores, "_id = ?", new String[]{"" + temp.getId()});
    }

    @Override
    public ArrayList listarTudo() {
        ArrayList<Produto> list = new ArrayList();
        String[] colunas = new String[]{"_id", "nome", "preco"};

        Cursor cursor = bd.query(nomeTabela, colunas, null, null, null, null, "nome ASC");

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            do{
                Produto temp = new Produto();
                temp.setId(cursor.getInt(0));
                temp.setNome(cursor.getString(1));
                temp.setPreco(cursor.getDouble(2));
                list.add(temp);

            }while (cursor.moveToNext());
        }

        return(list);
    }

    @Override
    public Object getById(long id) {
        String[] colunas = new String[]{"_id", "nome", "preco"};

        Cursor cursor = bd.query(nomeTabela, colunas, "_id=?", new String[]{Long.toString(id)}, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Produto temp = new Produto();
            temp.setId(cursor.getInt(0));
            temp.setNome(cursor.getString(1));
            temp.setPreco(cursor.getDouble(2));

            return (temp);
        } else{
            return null;
        }
    }

    @Override
    public ArrayList buscar(Object chaveBusca) {
        String nome = chaveBusca.toString();
        ArrayList<Produto> list = new ArrayList();
        String[] colunas = new String[]{"_id", "nome", "preco"};

        Cursor cursor = bd.query(nomeTabela, colunas, "nome LIKE ?", new String[]{"%"+ nome +"%"}, null, null, "nome ASC");

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Produto temp = new Produto();
                temp.setId(cursor.getInt(0));
                temp.setNome(cursor.getString(1));
                temp.setPreco(cursor.getDouble(2));
                list.add(temp);

            }while (cursor.moveToNext());
        }
        return(list);
    }

//    @Override
//    public int getQtdTotal() {
//        ArrayList<Produto> list = new ArrayList();
//        String[] colunas = new String[]{"_id"};
//
//        Cursor cursor = bd.query(nomeTabela, colunas, null, null, null, null, "nome ASC");
//        return cursor.getCount();
//    }

    public Produto buscarPorNomeUnico(String nome) {
        ArrayList<Produto> list = new ArrayList();
        String[] colunas = new String[]{"_id", "nome", "preco"};

        Cursor cursor = bd.query(nomeTabela, colunas, "nome=?", new String[]{nome}, null, null, "nome ASC");

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Produto temp = new Produto();
                temp.setId(cursor.getInt(0));
                temp.setNome(cursor.getString(1));
                temp.setPreco(cursor.getDouble(2));
                list.add(temp);

            }while (cursor.moveToNext());
            if(list.size() > 1){
                return null;
            } else{
                return(list.get(0));
            }
        }
        return null;
    }

}
