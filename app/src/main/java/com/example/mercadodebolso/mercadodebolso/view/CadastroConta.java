package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.EditText;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.util.Util;
import com.example.mercadodebolso.mercadodebolso.sistema.LoginInfo;

/**
 * Created by jamesson on 5/30/2016.
 */
public class CadastroConta extends Activity{
    private Context context;
    private LoginInfo sistema;
    private long idUsuario;
    private String login;
    private String senha;

    private EditText inputLogin;
    private EditText inputSenha;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_conta);

        inputSenha = (EditText) findViewById(R.id.input_senhaBanco);
        final EditText agencia = (EditText) findViewById(R.id.input_Agencia);

//        agencia.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String input = agencia.getText().toString();
//                if (input.isEmpty()) {
//                    agencia.getBackground().setTint(ContextCompat.getColor(context, R.color.vermelhoAlert));
//                }
//                else if (input.length() < 4){
//                    agencia.requestFocus();
//                    agencia.getBackground().setTint(ContextCompat.getColor(context, R.color.vermelhoAlert));
//                    Util.alert(CadastroConta.this, "4 dígitos no minimo");
//                } else if (input.length() > 4) {
//                    agencia.requestFocus();
//                    Util.alert(CadastroConta.this, "4 dígitos no máximo");
//                    }
//            }
//
//        });

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
        }
    }

}
