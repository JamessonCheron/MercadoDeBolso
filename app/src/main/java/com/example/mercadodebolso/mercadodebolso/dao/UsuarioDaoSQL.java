package com.example.mercadodebolso.mercadodebolso.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceUsuarioDao;
import com.example.mercadodebolso.mercadodebolso.model.Lista;
import com.example.mercadodebolso.mercadodebolso.model.Usuario;

import java.util.ArrayList;

/**
 * Created by jamesson on 5/22/2016.
 */
public class UsuarioDaoSQL extends AbstractDao implements InterfaceUsuarioDao {

    public UsuarioDaoSQL(Context context){
        super(context);
        setNomeTabela("usuario");
    }

    @Override
    public long inserir(Object obj) {
        Usuario temp = (Usuario) obj;
        ContentValues valores = new ContentValues();
        valores.put("nome", temp.getNome());
        valores.put("login", temp.getLogin());
        valores.put("senha", temp.getSenha());

        return bd.insert(nomeTabela, null, valores);

    }

    @Override
    public void remover(long id) {
        bd.delete(nomeTabela, "_id = " + id, null);
    }

    @Override
    public void update(Object obj) {

        Usuario temp = (Usuario) obj;
        ContentValues valores = new ContentValues();
        valores.put("nome", temp.getNome());
        valores.put("login", temp.getLogin());
        valores.put("senha", temp.getSenha());

        bd.update(nomeTabela, valores, "_id = ?", new String[]{"" + temp.getIdUser()});
    }

    @Override
    public Object getById(long id) {
        String[] colunas = new String[]{"_id", "nome", "login", "senha"};

        Cursor cursor = bd.query(nomeTabela, colunas, "_id=?", new String[]{Long.toString(id)}, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Usuario temp = new Usuario();
            temp.setId(cursor.getInt(0));
            temp.setNome(cursor.getString(1));
            temp.setLogin(cursor.getString(2));
            temp.setSenha(cursor.getString(3));

            return (temp);
        } else{
            return null;
        }
    }

    @Override
    public ArrayList buscar(Object chaveBusca) {
        String nome = chaveBusca.toString();
        ArrayList<Usuario> list = new ArrayList();
        String[] colunas = new String[]{"_id", "nome", "login", "senha"};

        Cursor cursor = bd.query(nomeTabela, colunas, "nome LIKE ?", new String[]{"%"+ nome +"%"}, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Usuario temp = new Usuario();
                temp.setId(cursor.getInt(0));
                temp.setNome(cursor.getString(1));
                temp.setLogin(cursor.getString(2));
                temp.setSenha(cursor.getString(3));
                list.add(temp);

            }while (cursor.moveToNext());
        }
        return(list);
    }

    @Override
    public Usuario buscarPorNomeUnico(String login) {
        ArrayList<Usuario> list = new ArrayList();
        String[] colunas = new String[]{"_id", "nome", "login", "senha"};

        Cursor cursor = bd.query(nomeTabela, colunas, "login=?", new String[]{login}, null, null, "login ASC");

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Usuario temp = new Usuario();
                temp.setId(cursor.getInt(0));
                temp.setNome(cursor.getString(1));
                temp.setLogin(cursor.getString(2));
                temp.setSenha(cursor.getString(3));
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
