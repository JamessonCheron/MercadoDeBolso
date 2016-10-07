package com.example.mercadodebolso.mercadodebolso.dao.interfaces;

import com.example.mercadodebolso.mercadodebolso.model.Pedido;
import com.example.mercadodebolso.mercadodebolso.model.Usuario;

import java.util.ArrayList;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public interface InterfacePedidoDao {

    long inserir(Object obj);

    void update(Object obj);

    Object getById(long id);

    ArrayList<Pedido> listarTudo(long idUsuario);

    ArrayList<Long> listarIdsListasEntregues(long idUsuario);
}
