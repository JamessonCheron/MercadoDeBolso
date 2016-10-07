package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.controller.ListaController;
import com.example.mercadodebolso.mercadodebolso.model.Lista;
import com.example.mercadodebolso.mercadodebolso.util.Util;

/**
 * Created by jamesson on 4/4/2016.
 */
public class PopUpCriarLista extends Activity {

    private ListaController listaController;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupview);

        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width  = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (heigth * .5));

        getWindow().findViewById(R.id.editText);

        context = this;
        listaController = new ListaController(context);
    }

    @Override
    public void onBackPressed() {
        setResult(1);
        finish();
    }

    public void clickCriar(View view)
    {
        EditText editText = (EditText) findViewById(R.id.editText);
        String nomeLista = editText.getText().toString();
        if(nomeLista.equals("")){
            Util.alert(this, "Informe o nome da lista");
        }else if(listaController.existe(nomeLista)){
            Util.alert(this, "Já existe uma lista com esse nome");
            editText.setText("");
        }else{
            Lista novaLista = listaController.criarLista(nomeLista);

            Intent ListaActivity = new Intent(this, ListaView.class);

            ListaActivity.putExtra("id_lista", novaLista.getId());
            startActivityForResult(ListaActivity, 1);
            finish();
        }
    }

    public void clickCancelar(View view){
        setResult(1);
        finish();
        //startActivity(new Intent(PopUpCriarLista.this, Main.class));
    }

}
