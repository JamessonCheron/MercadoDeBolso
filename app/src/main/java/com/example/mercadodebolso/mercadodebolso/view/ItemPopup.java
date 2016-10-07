package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.util.Util;
import com.example.mercadodebolso.mercadodebolso.controller.ListaController;
import com.example.mercadodebolso.mercadodebolso.controller.ProdutoController;
import com.example.mercadodebolso.mercadodebolso.model.Produto;

/**
 * Created by jamesson on 4/12/2016.
 */
public class ItemPopup extends Activity {
    private ProdutoController produtoController;
    private ListaController listaController;
    private long idItem;
    private long idLista;
    private long idProduto;
    private int quantidade;
    private String modo; //o modo é a forma de como o popup vai se comportar, se para adicionar ou para alterar
    private Util comum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.itempopup);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        comum = new Util();
        produtoController = new ProdutoController(this);
        listaController = new ListaController(this);

        int width  = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (heigth * .8));

        Intent intent = getIntent();
        idItem = intent.getLongExtra("id_item", 0);
        idLista = intent.getLongExtra("id_lista", 0);
        idProduto = intent.getLongExtra("id_produto", 0);
        quantidade = intent.getIntExtra("quantidade", 1);
        modo = intent.getStringExtra("modo");

        configPopUp();

        if(idProduto != 0 && idLista != 0){
            final Produto produto = produtoController.getProduto(idProduto);

            TextView nomeProduto = (TextView) findViewById(R.id.nomeProduto);
            nomeProduto.setText(produto.getNome());

            TextView precoProduto = (TextView) findViewById(R.id.precoProduto);
            precoProduto.setText(comum.formatReal(produto.getPreco()));

            final TextView precoItem = (TextView) findViewById(R.id.precoItem);
            final EditText qtdView = (EditText) findViewById(R.id.qtdItem);

            qtdView.setText(Integer.toString(quantidade));
            qtdView.requestFocus();
            qtdView.performClick();

            quantidade = Integer.parseInt(qtdView.getText().toString());
            precoItem.setText(comum.formatReal(calculaPreco(qtdView, produto)));

            qtdView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String input = qtdView.getText().toString();
                    if (input.isEmpty()) {
                        precoItem.setText(comum.formatReal(0));
                        quantidade = 0;
                    } else if(input.length() < 4){
                        precoItem.setText(comum.formatReal(calculaPreco(qtdView, produto)));
                        quantidade = Integer.parseInt(qtdView.getText().toString());
                    } else{
                        qtdView.setText(Integer.toString(quantidade));
                        qtdView.requestFocus();
                        Util.alert(ItemPopup.this, "3 dígitos no máximo");
                    }
                }
            });

        } else{
            Util.alert(this, "Erro - produto ou lista não associado");
            finish();
        }
    }

    private void configPopUp(){
        TextView titulo = (TextView) findViewById(R.id.textView);
        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        if(modo == null){
            titulo.setText("Adicionar item");
            btnConfirm.setText("Adicionar");
        } else{
            titulo.setText("Editar item");
            btnConfirm.setText("Salvar");
        }
    }

    protected double calculaPreco(EditText v, Produto produto){
        return Integer.parseInt(v.getText().toString())*produto.getPreco();
    }

    public void clickCancelar(View view){
        onBackPressed();
    }
    public void clickAddItem(View view){
        if(quantidade <= 0){
            Util.alert(this, "Escolha uma quantidade maior que zero.");
        }else {

            if(idItem == 0){
                listaController.addItem(idLista, produtoController.getProduto(idProduto), quantidade);
                Util.alert(this, "Item adicionado com sucesso");
            } else{
                listaController.editItem(idItem, quantidade);
                Util.alert(this, "Item alterado com sucesso");

            }
            setResult(1);//reload
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(-1);
        finish();
        //super.onBackPressed();
    }

}
