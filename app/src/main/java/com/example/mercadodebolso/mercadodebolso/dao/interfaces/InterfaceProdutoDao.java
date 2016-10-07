package com.example.mercadodebolso.mercadodebolso.dao.interfaces;

import com.example.mercadodebolso.mercadodebolso.model.Produto;

import java.util.ArrayList;

/**
 * Created by jamesson.ribeiro on 03/05/2016.
 */
public interface InterfaceProdutoDao {

    long inserir(Object obj);

    void remover(long id);

    void update(Object obj);

    ArrayList listarTudo();

    Object getById(long id);

    ArrayList buscar(Object chaveBusca);

    Produto buscarPorNomeUnico(String nome);

    int countRows();
}
