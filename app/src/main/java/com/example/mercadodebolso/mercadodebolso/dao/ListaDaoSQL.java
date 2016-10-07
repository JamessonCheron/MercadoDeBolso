package com.example.mercadodebolso.mercadodebolso.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceListaDao;
import com.example.mercadodebolso.mercadodebolso.model.Lista;

import java.util.ArrayList;

/**
 * Created by Cais - Automação on 29/04/2016.
 */
public class ListaDaoSQL extends AbstractDao implements InterfaceListaDao {

    public ListaDaoSQL(Context context){
        super(context);
        setNomeTabela("lista");
    }

    @Override
    public long inserir(Object obj) { //retorna id
        Lista temp = (Lista) obj;
        ContentValues valores = new ContentValues();
        valores.put("id_usuario", temp.getIdUsuario());
        valores.put("nome", temp.getNome());

        return bd.insert(nomeTabela, null, valores);
    }

    @Override
    public void remover(long id) {
        bd.delete(nomeTabela, "_id = " + id, null);
    }

    @Override
    public void update(Object obj) {
        Lista temp = (Lista) obj;
        ContentValues valores = new ContentValues();
        valores.put("id_usuario", temp.getIdUsuario());
        valores.put("nome", temp.getNome());

        bd.update(nomeTabela, valores, "_id = ?", new String[]{"" + temp.getId()});
    }

    @Override
    public ArrayList listarTudo(long idUsuario) {
        ArrayList<Lista> list = new ArrayList();
        String[] colunas = new String[]{"_id", "id_usuario", "nome"};

        Cursor cursor = bd.query(nomeTabela, colunas, "id_usuario=?", new String[]{Long.toString(idUsuario)}, null, null, "nome ASC");

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Lista temp = new Lista();
                temp.setId(cursor.getInt(0));
                temp.setIdUsuario(cursor.getInt(1));
                temp.setNome(cursor.getString(2));
                list.add(temp);

            }while (cursor.moveToNext());
        }

        return(list);
    }

    @Override
    public Object getById(long id) {
        String[] colunas = new String[]{"_id", "id_usuario", "nome"};

        Cursor cursor = bd.query(nomeTabela, colunas, "_id=?", new String[]{Long.toString(id)}, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Lista temp = new Lista();
            temp.setId(cursor.getInt(0));
            temp.setIdUsuario(cursor.getInt(1));
            temp.setNome(cursor.getString(2));

            return (temp);
        } else{
            return null;
        }
    }

    @Override
    public ArrayList buscar(long idUsuario, Object chaveBusca) {
        String nome = chaveBusca.toString();
        ArrayList<Lista> list = new ArrayList();
        String[] colunas = new String[]{"_id", "id_usuario", "nome"};

        Cursor cursor = bd.query(nomeTabela, colunas, "id_usuario=? AND nome LIKE ?", new String[]{Long.toString(idUsuario), "%"+nome+"%"}, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Lista temp = new Lista();
                temp.setId(cursor.getInt(0));
                temp.setIdUsuario(cursor.getInt(1));
                temp.setNome(cursor.getString(2));
                list.add(temp);

            }while (cursor.moveToNext());
        }
        return(list);
    }

    public Lista buscarPorNomeUnico(long idUsuario, String nome) {
        ArrayList<Lista> list = new ArrayList();
        String[] colunas = new String[]{"_id", "id_usuario", "nome"};

        Cursor cursor = bd.query(nomeTabela, colunas, "id_usuario=? AND nome=?", new String[]{Long.toString(idUsuario), nome}, null, null, "nome ASC");

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Lista temp = new Lista();
                temp.setId(cursor.getInt(0));
                temp.setIdUsuario(cursor.getInt(1));
                temp.setNome(cursor.getString(2));
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

    @Override
    public int countRows(long idUsuario){
        Cursor cursor = bd.rawQuery("SELECT * FROM " + nomeTabela + " WHERE id_usuario = " + idUsuario, null);
        return cursor.getCount();
    }
}
