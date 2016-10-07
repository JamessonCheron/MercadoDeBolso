package com.example.mercadodebolso.mercadodebolso.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfacePedidoDao;
import com.example.mercadodebolso.mercadodebolso.model.Lista;
import com.example.mercadodebolso.mercadodebolso.model.Pedido;

import java.util.ArrayList;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public class PedidoDaoSQL extends AbstractDao implements InterfacePedidoDao {
    public PedidoDaoSQL(Context context) {
        super(context);
        setNomeTabela("pedido");
    }

    //_id id_usuario id_lista status taxa frete preco_total
    @Override
    public long inserir(Object obj) {
        Pedido temp = (Pedido) obj;
        ContentValues valores = new ContentValues();
        valores.put("id_usuario", temp.getIdUsuario());
        valores.put("id_lista", temp.getIdLista());
        valores.put("status", temp.getStatus());
        valores.put("taxa", temp.getTaxa());
        valores.put("frete", temp.getFrete());
        valores.put("preco_total", temp.getPrecoTotal());

        return bd.insert(nomeTabela, null, valores);
    }
    //_id  id_usuario  id_lista  status  taxa  frete  preco_total

    @Override
    public void update(Object obj) {
        Pedido temp = (Pedido) obj;
        ContentValues valores = new ContentValues();
        valores.put("id_usuario", temp.getIdUsuario());
        valores.put("id_lista", temp.getIdLista());
        valores.put("status", temp.getStatusPedido());
        valores.put("taxa", temp.getTaxa());
        valores.put("frete", temp.getFrete());
        valores.put("preco_total", temp.getPrecoTotal());

        bd.update(nomeTabela, valores, "_id = ?", new String[]{"" + temp.getIdPedido()});
    }

    @Override
    public Object getById(long id) {
        String[] colunas = new String[]{"_id", "id_usuario", "id_lista", "status", "taxa", "frete", "preco_total"};

        Cursor cursor = bd.query(nomeTabela, colunas, "_id=?", new String[]{Long.toString(id)}, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Pedido temp = new Pedido();
            temp.setIdPedido(cursor.getInt(0));
            temp.setIdUsuario(cursor.getInt(1));
            temp.setIdLista(cursor.getInt(2));
            temp.setStatusPedido(cursor.getInt(3));
            temp.setTaxa(cursor.getInt(4));
            temp.setFrete(cursor.getInt(5));
            temp.setPrecoTotal(cursor.getInt(6));

            return (temp);
        } else{
            return null;
        }
    }

    public ArrayList<Pedido> listarTudo(long idUsuario) {
        ArrayList<Pedido> list = new ArrayList();
        String[] colunas = new String[]{"_id", "id_usuario", "id_lista", "status", "taxa", "frete", "preco_total"};

        Cursor cursor = bd.query(nomeTabela, colunas, "id_usuario=?", new String[]{""+idUsuario}, null, null, null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Pedido temp = new Pedido();
                temp.setIdPedido(cursor.getInt(0));
                temp.setIdUsuario(cursor.getInt(1));
                temp.setIdLista(cursor.getInt(2));
                temp.setStatusPedido(cursor.getInt(3));
                temp.setTaxa(cursor.getInt(4));
                temp.setFrete(cursor.getInt(5));
                temp.setPrecoTotal(cursor.getInt(6));
                list.add(temp);

            }while (cursor.moveToNext());
        }

        return(list);
    }

    public ArrayList<Long> listarIdsListasEntregues(long idUsuario) {
        ArrayList<Long> list = new ArrayList();
        String[] colunas = new String[]{"id_lista"};

        Cursor cursor = bd.query(nomeTabela, colunas, "id_usuario=? AND status=?", new String[]{""+idUsuario, "1"}, null, null, null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                list.add(cursor.getLong(0));

            }while (cursor.moveToNext());
        }

        return(list);
    }
}
