package com.example.mercadodebolso.mercadodebolso.dao.interfaces;

import com.example.mercadodebolso.mercadodebolso.model.Item;

import java.util.ArrayList;

/**
 * Created by jamesson.ribeiro on 03/05/2016.
 */
public interface InterfaceItemDao {

    long inserir(Object obj);

    void remover(long i);

    void update(Object obj);

    ArrayList listarTudo();

    Object getById(long id);

    ArrayList buscar(Object chaveBusca);

    Item getByProdutoLista(long idLista, long idProduto);

    int countRows();
}
