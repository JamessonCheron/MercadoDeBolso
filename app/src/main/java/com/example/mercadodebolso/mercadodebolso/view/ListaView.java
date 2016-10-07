package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.util.Util;
import com.example.mercadodebolso.mercadodebolso.controller.ListaController;
import com.example.mercadodebolso.mercadodebolso.model.Lista;
import com.example.mercadodebolso.mercadodebolso.view.adapters.AdapterItens;


/**
 * Created by jamesson.ribeiro on 04/04/2016.
 */
public class ListaView extends Activity {
    private long idLista;
    private ListaController listaController;
    private Context contexto;
    private MenuLateral menuLateral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        contexto = this;
        listaController = new ListaController(contexto);
        menuLateral = new MenuLateral(contexto);
        menuLateral.loadDrawerLayout();

        Intent intent = getIntent();

        idLista = intent.getLongExtra("id_lista", 0);
        if(idLista != 0){
            loadListaView();
        } else{
            Util.alert(this, "Erro ao abrir lista");
            startActivityForResult(new Intent(contexto, Main.class), 1);
            finish();
        }

    }

    public void loadListaView(){
        ListView listaItens;
        final Lista lista = listaController.getLista(idLista);
        int qtdItens = lista.getQtdItens();
        listaItens = (ListView) findViewById(R.id.lvItens);
        TextView nenhumaLista = (TextView) findViewById(R.id.textNenhumaLista);
        RelativeLayout headerLista = (RelativeLayout) findViewById(R.id.headerLista);
        RelativeLayout listaFooter = (RelativeLayout) findViewById(R.id.listaFooter);
        Button btnFazerPedido = (Button) findViewById(R.id.FazPedido);

        btnFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirPedido = new Intent(contexto, PedidoView.class);
                abrirPedido.putExtra("id_usuario", lista.getIdUsuario());
                abrirPedido.putExtra("id_lista", idLista);
                startActivityForResult(abrirPedido, 1);
            }
        });

        if(lista.getQtdItens() == 0){
            nenhumaLista.setVisibility(View.VISIBLE);
            headerLista.setVisibility(View.INVISIBLE);
            listaFooter.setVisibility(View.INVISIBLE);
        }else{
            nenhumaLista.setVisibility(View.INVISIBLE);
            headerLista.setVisibility(View.VISIBLE);
            listaFooter.setVisibility(View.VISIBLE);
        }

        final AdapterItens adapter = new AdapterItens(this, lista.getItens(), idLista);
        listaItens.setAdapter(adapter);

        TextView nomeListaView = (TextView) findViewById(R.id.nomeLista);
        nomeListaView.setText(lista.getNome());

        TextView qtdTotal = (TextView) findViewById(R.id.qtdTotal);
        if(qtdItens > 1){
            qtdTotal.setText(Integer.toString(qtdItens) + " itens");
        } else{
            qtdTotal.setText(Integer.toString(qtdItens) + " item");
        }


        TextView precoView = (TextView) findViewById(R.id.precoTotal);
        precoView.setText(Util.formatReal(lista.getPrecoLista()));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent  = new Intent(this, Main.class);
        startActivityForResult(intent, 1);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){//reload
            loadListaView();
        } else if(resultCode == 0){//finish
            finish();
        } else if(resultCode == 2){
            Intent intent = new Intent(contexto, Main.class);
            startActivityForResult(intent, 1);
        }
    }

    public void onClickAddItem(View view) {
        Intent intent = new Intent(contexto, AddItem.class);
        intent.putExtra("id_lista", idLista);
        startActivityForResult(intent, 1);
    }
}
