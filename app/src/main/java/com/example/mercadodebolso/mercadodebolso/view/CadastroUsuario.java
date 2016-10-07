package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.util.Util;
import com.example.mercadodebolso.mercadodebolso.controller.UsuarioController;

/**
 * Created by Uirá Veríssimo on 27/05/2016.
 */
public class CadastroUsuario extends Activity{

    private EditText inputNome;
    private EditText inputLogin;
    private EditText inputSenha1;
    private EditText inputSenha2;
    private Button btnCadastrar;

    private String nome;
    private String login;
    private String senha1;
    private String senha2;

    private Context context;
    private UsuarioController usuarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_usuario);
        context = this;
        usuarioController = new UsuarioController(context);

        inputNome = (EditText) findViewById(R.id.input_nome);
        inputLogin = (EditText) findViewById(R.id.input_login);
        inputSenha1 = (EditText) findViewById(R.id.input_senha1);
        inputSenha2 = (EditText) findViewById(R.id.input_senha2);
        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);

        inputNome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    tratarInputNome();
                } else {
                    inputNome.getBackground().setTint(ContextCompat.getColor(context, R.color.cinzaEscuro));
                }
            }
        });

        inputLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    tratarInputLogin();
                } else{
                    inputLogin.getBackground().setTint(ContextCompat.getColor(context, R.color.cinzaEscuro));
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idUsuario;
                tratarInputLogin();
                tratarInputNome();
                tratarInputSenha1();
                tratarInputSenha2();
                if(nome != null && login != null && senha1 != null && senha2 != null){
                    idUsuario = (int) usuarioController.cadastrarUsuario(nome, login, senha1);
                    Util.alert(context, "Usuário cadastrado com sucesso");
                    //volta pra fazer login
                    Intent intent = getIntent();
                    setResult(idUsuario, intent);
                    finish();
                } else{
                    //alert
                    Util.alert(context, "Verifique os campos do formulario");
                }
            }
        });

    }

    private void tratarInputSenha2() {
        senha2 = inputSenha2.getText().toString();
        senha2.trim();
        if(senha2.length() < 1 || !(senha2.equals(senha1))) {
            inputSenha2.getBackground().setTint(ContextCompat.getColor(context, R.color.vermelhoAlert));
            senha2 = null;
            Util.alert(context, "As senhas nao conferem");
        }else{
            inputSenha2.getBackground().setTint(ContextCompat.getColor(context, R.color.verdeOK));
        }
    }

    private void tratarInputSenha1() {
        senha1 = inputSenha1.getText().toString();
        senha1.trim();
        if (senha1.length() < 1) {
            inputSenha1.getBackground().setTint(ContextCompat.getColor(context, R.color.vermelhoAlert));
            senha1 = null;
            Util.alert(context, "Senha inválida");
        } else {
            inputSenha1.getBackground().setTint(ContextCompat.getColor(context, R.color.verdeOK));
        }
    }

    private void tratarInputLogin() {
        login = inputLogin.getText().toString();
        login.trim();
        if(!login.isEmpty()) {
            if (!usuarioController.validaLogin(login)) {
                inputLogin.getBackground().setTint(ContextCompat.getColor(context, R.color.vermelhoAlert));
                login = null;
            } else {
                inputLogin.getBackground().setTint(ContextCompat.getColor(context, R.color.verdeOK));
            }
            if (usuarioController.existeLogin(login)) {
                Util.alert(context, "Outro usuário já utiliza este login");
                inputLogin.getBackground().setTint(ContextCompat.getColor(context, R.color.vermelhoAlert));
                login = null;
            }
        } else{
            inputLogin.getBackground().setTint(ContextCompat.getColor(context, R.color.vermelhoAlert));
            login = null;
        }
    }

    private void tratarInputNome() {
        nome = inputNome.getText().toString();
        nome.trim();
        if(nome.isEmpty()){
            inputNome.getBackground().setTint(ContextCompat.getColor(context, R.color.vermelhoAlert));
            nome = null;
            Util.alert(context, "Digite seu nome");
        }else{
            inputNome.getBackground().setTint(ContextCompat.getColor(context, R.color.verdeOK));
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        setResult(-1); //faz nada
        finish();
    }

}
