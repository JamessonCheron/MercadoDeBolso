package com.example.mercadodebolso.mercadodebolso.controller;

import android.content.Context;

import com.example.mercadodebolso.mercadodebolso.dao.UsuarioDaoSQL;
import com.example.mercadodebolso.mercadodebolso.dao.factory.FactoryUsuarioDao;
import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceUsuarioDao;
import com.example.mercadodebolso.mercadodebolso.model.Usuario;
import com.example.mercadodebolso.mercadodebolso.util.Util;

/**
 * Created by Uirá Veríssimo on 27/05/2016.
 */
public class UsuarioController {
    private UsuarioDaoSQL usuarioDAO;

    private InterfaceUsuarioDao usuarioDao;
    private Context context;

    public UsuarioController(Context context){
        this.usuarioDao= FactoryUsuarioDao.getInstance("SQL", context);
        this.context = context;
        usuarioDAO = new UsuarioDaoSQL(context);
    }

    public boolean existeLogin(String login) {
        Usuario temp = usuarioDao.buscarPorNomeUnico(login);
        if(temp != null){
            return true;
        } else {
            return false;
        }
    }

    public long cadastrarUsuario(String nome, String login, String senha){
        Usuario novo = new Usuario();
        //senha = encrypt(senha);
        novo.setNome(nome);
        novo.setLogin(login);
        novo.setSenha(senha);
        return usuarioDao.inserir(novo);
    }

    public boolean validaLogin(String login) {
        if(login.contains(" ")){
            Util.alert(context, "LoginView inválido");
            return false;
        }
        return true;
    }

    public long tryLogin(String login, String senha) {
        Usuario temp = usuarioDao.buscarPorNomeUnico(login);
        if(temp != null){
            if(temp.getSenha().equals(senha)) {
                return temp.getIdUser();
            } else {
                return -1;
            }
        } else{
            return -1;
        }
    }

    public Usuario getUsuario(long idUsuario) {
        Usuario usuario = (Usuario) usuarioDao.getById(idUsuario);
        return usuario;
    }
}
