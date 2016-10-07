package com.example.mercadodebolso.mercadodebolso.dao.interfaces;

import com.example.mercadodebolso.mercadodebolso.model.Lista;

import java.util.ArrayList;

/**
 * Created by jamesson on 4/5/2016.
 */
public interface InterfaceListaDao {

    long inserir(Object obj);

    void update(Object obj);

    void remover(long id);

    ArrayList listarTudo(long idUsuario);

    Object getById(long id);

    ArrayList buscar(long idUsuario, Object chaveBusca);

    Lista buscarPorNomeUnico(long idUsuario, String nome);

    int countRows(long idUsuario);

    //  http://www.javabuilding.com/academy/patterns/dao.html
    //   https://pt.wikipedia.org/wiki/Bridge_(padr%C3%A3o_de_projeto_de_software)
}
