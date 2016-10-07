package com.example.mercadodebolso.mercadodebolso.dao.interfaces;

import java.util.ArrayList;

/**
 * Created by Cais - Automação on 25/05/2016.
 */
public interface InterfaceConfigMercadoDao {

    void inserir(long idUsuario);

    void update(long id);

    long getById(long id);

    int countRows();

}


