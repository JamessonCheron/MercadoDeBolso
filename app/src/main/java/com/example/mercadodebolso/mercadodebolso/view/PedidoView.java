package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.controller.ListaController;
import com.example.mercadodebolso.mercadodebolso.controller.PedidoController;
import com.example.mercadodebolso.mercadodebolso.model.Lista;
import com.example.mercadodebolso.mercadodebolso.model.Pedido;
import com.example.mercadodebolso.mercadodebolso.util.Util;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public class PedidoView extends Activity{

    private PedidoController pedidoController;
    private ListaController listaController;
    private Context contexto;
    private MenuLateral menuLateral;
    private Pedido pedidoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido);
        contexto = this;
        pedidoController = new PedidoController(contexto);
        menuLateral = new MenuLateral(contexto);
        menuLateral.loadDrawerLayout();

        loadCreatePedido(getIntent());

        preencherTela();

    }

    private void preencherTela(){
        TextView titulo = (TextView) findViewById(R.id.title);
        TextView nomeLista = (TextView) findViewById(R.id.nomeLista);
        TextView precoLista = (TextView) findViewById(R.id.precoLista);
        TextView precoFrete = (TextView) findViewById(R.id.precoFrete);
        TextView statusPedido = (TextView) findViewById(R.id.statusPedido);
        TextView precoTotal = (TextView) findViewById(R.id.precoTotal);

        RelativeLayout botoesFooter = (RelativeLayout) findViewById(R.id.footer);
        Button btnPagar = (Button) findViewById(R.id.pagar);
        Button btnCancelar = (Button) findViewById(R.id.cancelar);

        titulo.setText("Pedido: #" + pedidoAtual.getIdPedido());
        nomeLista.setText(pedidoAtual.getLista().getNome());
        precoLista.setText(Util.formatReal(pedidoAtual.getLista().getPrecoLista()));
        precoFrete.setText(Util.formatReal(pedidoAtual.getFrete()));
        statusPedido.setText(pedidoAtual.getStatusNome());
        precoTotal.setText(Util.formatReal(pedidoAtual.getPrecoTotal()));


        if(pedidoAtual.getStatus() == 0) {
            botoesFooter.setVisibility(View.VISIBLE);
            btnPagar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pedidoAtual.pagarPedido();
                    pedidoController.update(pedidoAtual);
                    preencherTela();
                }
            });
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pedidoAtual.cancelarPedido();
                    pedidoController.update(pedidoAtual);
                    preencherTela();
                }
            });
        }else{
            botoesFooter.setVisibility(View.INVISIBLE);
        }

    }

    private void loadCreatePedido(Intent intent){
        listaController = new ListaController(contexto);
        long idPedido = intent.getLongExtra("id_pedido", 0);
        if(idPedido != 0){
            pedidoAtual = pedidoController.getPedido(idPedido);
            pedidoAtual.setLista(listaController.getLista(pedidoAtual.getIdLista()));
        } else{
            long idUsuario = intent.getLongExtra("id_usuario", 0);
            long idLista = intent.getLongExtra("id_lista", 0);


            if(idUsuario != 0 && idLista != 0) {
                pedidoAtual = pedidoController.novoPedido(idUsuario, idLista);
            } else{
                Util.alert(this, "Erro ao fazer pedido");
                startActivityForResult(new Intent(contexto, Main.class), 1);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        setResult(2);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){//reload

        } else if(resultCode == 0){//finish
            finish();
        }
    }
}
