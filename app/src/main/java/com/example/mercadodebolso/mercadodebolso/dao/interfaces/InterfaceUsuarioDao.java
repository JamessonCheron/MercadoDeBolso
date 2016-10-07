package com.example.mercadodebolso.mercadodebolso.dao.interfaces;

import com.example.mercadodebolso.mercadodebolso.model.Lista;
import com.example.mercadodebolso.mercadodebolso.model.Usuario;

import java.util.ArrayList;

/**
 * Created by jamesson on 5/22/2016.
 */

public interface InterfaceUsuarioDao {

    long inserir(Object obj);

    void remover(long id);

    void update(Object obj);

    //ArrayList listarTudo();

    Object getById(long id);

    ArrayList buscar(Object chaveBusca);

    Usuario buscarPorNomeUnico(String nome);

    int countRows();

}
