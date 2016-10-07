package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.util.Util;
import com.example.mercadodebolso.mercadodebolso.controller.ProdutoController;
import com.example.mercadodebolso.mercadodebolso.controller.UsuarioController;
import com.example.mercadodebolso.mercadodebolso.sistema.LoginInfo;

/**
 * Created by Cais - Automação on 27/05/2016.
 */
public class LoginView extends Activity{
    private Context context;
    private ProdutoController produtoController;
    private LoginInfo sistema;
    private long idUsuario;
    private String login;
    private String senha;
    private UsuarioController usuarioController;

    private EditText inputLogin;
    private EditText inputSenha;
    private Button btnLogin;
    private Button btnCadastrar;
    private Button btnRecuperarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = this;
        sistema = LoginInfo.getInstance(context);
        produtoController = new ProdutoController(context);
        produtoController.iniciarDAO();


        sistema.start();
        idUsuario = sistema.getUserConnected();
        if(idUsuario != 0){
            efetuarLogin(idUsuario);
        }else{
            usuarioController = new UsuarioController(context);

            btnLogin = (Button) findViewById(R.id.btn_login);
            btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);
            btnRecuperarSenha = (Button) findViewById(R.id.btn_recuperar_senha);

            inputLogin = (EditText) findViewById(R.id.input_login);
            inputSenha = (EditText) findViewById(R.id.input_senha);

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

            inputSenha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // code to execute when EditText loses focus
                        tratarInputSenha();
                    } else{
                        inputSenha.getBackground().setTint(ContextCompat.getColor(context, R.color.cinzaEscuro));
                    }
                }
            });

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tratarInputLogin();
                    tratarInputSenha();
                    if (login != null && senha != null) {
                        //senha = encrypt(senha);
                        long idUsuario = usuarioController.tryLogin(login, senha);
                        if (idUsuario > 0) {
                            efetuarLogin(idUsuario);
                        } else {
                            Util.alert(context, "Usuário ou senha incorretos");
                        }
                    } else {
                        Util.alert(context, "Verifique os campos do formlário");
                    }
                }
            });

            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CadastroUsuario.class);
                    startActivityForResult(intent, 1);
                }
            });

            btnRecuperarSenha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(context, Main.class);
                    startActivityForResult(intent, 1);*/
                    Util.alert(context, "Não dá pra recuperar a senha. Hahahaha");
                    Toast.makeText(context, "Por enquanto...", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void efetuarLogin(long idUsuario) {
        sistema.setUserConnected(idUsuario);
        Intent intent = new Intent(context, Main.class);
        intent.putExtra("id_user_connected", idUsuario);
        startActivityForResult(intent, 1);
    }

    private void tratarInputLogin() {
        login = inputLogin.getText().toString();
        login.trim();

        if(!usuarioController.validaLogin(login)) {
            inputLogin.getBackground().setTint(ContextCompat.getColor(context, R.color.vermelhoAlert));
            login = null;
        } else{
            inputLogin.getBackground().setTint(ContextCompat.getColor(context, R.color.verdeOK));
        }

    }

    private void tratarInputSenha() {
        senha = inputSenha.getText().toString();
        senha.trim();
        if(senha.length() < 1) {
            inputSenha.getBackground().setTint(ContextCompat.getColor(context, R.color.vermelhoAlert));
            Util.alert(context, "Digite sua senha");
            senha = null;
        } else{
            inputSenha.getBackground().setTint(ContextCompat.getColor(context, R.color.verdeOK));
        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        setResult(0);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 0) {
            finish();
        } else if(resultCode > 0){// resultCode > 0 significa que o usuario acabou de se cadastrar e vai fazer login
            efetuarLogin(resultCode);
        }
    }


}
