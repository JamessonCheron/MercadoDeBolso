package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.controller.ListaController;
import com.example.mercadodebolso.mercadodebolso.controller.ProdutoController;
import com.example.mercadodebolso.mercadodebolso.model.Lista;
import com.example.mercadodebolso.mercadodebolso.sistema.LoginInfo;
import com.example.mercadodebolso.mercadodebolso.view.adapters.AdapterListas;

import java.util.ArrayList;

public class Main extends Activity {
    private Context context;
    private ProdutoController produtoController;
    private ListaController listaController;
    private MenuLateral menuLateral;
    private LoginInfo sistema;
    //private static String TAG = Main.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        produtoController = new ProdutoController(context);
        listaController = new ListaController(context);
        menuLateral = new MenuLateral(context);
        menuLateral.loadDrawerLayout();
        produtoController.iniciarDAO();

        loadListas();
    }


    public void loadListas() {
        ListView listaMain;
        RelativeLayout headerLista = (RelativeLayout) findViewById(R.id.headerLista);
        listaMain = (ListView) findViewById(R.id.mainListView);
        TextView nenhumaLista = (TextView) findViewById(R.id.textNenhumaLista);
        if(listaController.getQtdListasValidas() > 0){
            nenhumaLista.setVisibility(View.INVISIBLE);
            headerLista.setVisibility(View.VISIBLE);
            ArrayList<Lista> arrayListas = listaController.getListasValidas();

            final AdapterListas adapter = new AdapterListas(this, arrayListas);
            listaMain.setAdapter(adapter);
            listaMain.setVisibility(View.VISIBLE);
        } else{
            nenhumaLista.setVisibility(View.VISIBLE);
            headerLista.setVisibility(View.INVISIBLE);
            listaMain.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        setResult(0);
        finish();
    }

    public void onClickIncluir(View view)
    {
        Intent PopActivity = new Intent(this, PopUpCriarLista.class);
        startActivityForResult(PopActivity, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 0) {
            finish();
        }
    }

}
