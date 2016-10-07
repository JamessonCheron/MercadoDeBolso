package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.controller.ListaController;
import com.example.mercadodebolso.mercadodebolso.controller.PedidoController;
import com.example.mercadodebolso.mercadodebolso.model.Pedido;
import com.example.mercadodebolso.mercadodebolso.util.Util;
import com.example.mercadodebolso.mercadodebolso.view.adapters.AdapterItens;
import com.example.mercadodebolso.mercadodebolso.view.adapters.AdapterPedidos;

import java.util.ArrayList;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public class HistoricoView extends Activity {
    private ListaController listaController;
    private Context contexto;
    private MenuLateral menuLateral;
    private PedidoController pedidoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historico);
        contexto = this;
        listaController = new ListaController(contexto);
        pedidoController = new PedidoController(contexto);
        menuLateral = new MenuLateral(contexto);
        menuLateral.loadDrawerLayout();


        loadPedidos();


    }

    private void loadPedidos() {
        ArrayList<Pedido> listaPedidos = pedidoController.getAll();
        TextView nenhumPedido = (TextView) findViewById(R.id.textNenhumaLista);
        ListView listView = (ListView) findViewById(R.id.lista);

        if(listaPedidos.size() > 0){
            nenhumPedido.setVisibility(View.INVISIBLE);
            final AdapterPedidos adapter = new AdapterPedidos(this, listaPedidos);
            listView.setAdapter(adapter);
            listView.setVisibility(View.VISIBLE);
        } else{
            listView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent  = new Intent(this, Main.class);
        startActivityForResult(intent, 1);
        finish();
    }
}
