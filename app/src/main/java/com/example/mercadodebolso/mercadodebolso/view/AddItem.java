package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.util.Util;
import com.example.mercadodebolso.mercadodebolso.controller.ProdutoController;
import com.example.mercadodebolso.mercadodebolso.model.Produto;
import com.example.mercadodebolso.mercadodebolso.view.adapters.AdapterBuscaProduto;

import java.util.ArrayList;

/**
 * Created by jamesson.ribeiro on 07/04/2016.
 */
public class AddItem extends Activity {
    private ProdutoController produtoController;
    private ListView itemList;
    private long idLista;
    private Context contexto;
    private MenuLateral menuLateral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);
        Intent intent = getIntent();
        contexto = this;
        produtoController = new ProdutoController(this);
        menuLateral = new MenuLateral(contexto);
        menuLateral.loadDrawerLayout();

        idLista = intent.getLongExtra("id_lista", 0);
        if(idLista == 0){
            Util.alert(this, "Erro - Lista não associada");
            finish();
        }

        final EditText editTextBusca = (EditText) findViewById(R.id.inputTextProduto);
        editTextBusca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String textoDigitado = editTextBusca.getText().toString();
                textoDigitado.trim();
                searchProd(textoDigitado);
            }
        });

    }


    public void searchProd(String busca) {
        itemList = (ListView) findViewById(R.id.itemLList);
        if(!busca.isEmpty()) {
            ArrayList<Produto> listaProduto;

            TextView nenhumProduto = (TextView) findViewById(R.id.textNenhumaLista);
            listaProduto = produtoController.buscarProdutosByName(busca);
            if (listaProduto.isEmpty()) {
                nenhumProduto.setVisibility(View.VISIBLE);
            } else {
                nenhumProduto.setVisibility(View.INVISIBLE);
            }

            final AdapterBuscaProduto adapter = new AdapterBuscaProduto(this, listaProduto, idLista);
            itemList.setAdapter(adapter);
            itemList.setVisibility(View.VISIBLE);

        }else{
            itemList.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentListaView;
        intentListaView = new Intent(AddItem.this, ListaView.class);

        intentListaView.putExtra("id_lista", idLista);
        startActivity(intentListaView);
        finish();
    }
}