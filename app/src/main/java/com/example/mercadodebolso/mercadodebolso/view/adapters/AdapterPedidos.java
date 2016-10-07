package com.example.mercadodebolso.mercadodebolso.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.controller.ListaController;
import com.example.mercadodebolso.mercadodebolso.model.Item;
import com.example.mercadodebolso.mercadodebolso.model.Pedido;
import com.example.mercadodebolso.mercadodebolso.view.ItemPopup;
import com.example.mercadodebolso.mercadodebolso.view.PedidoView;

import java.util.ArrayList;

/**
 * Created by Cais - Automação on 07/06/2016.
 */
public class AdapterPedidos extends ArrayAdapter {
    private Context context;
    private ListaController listaController;
    public AdapterPedidos(Context context, ArrayList<Pedido> pedidos) {
        super(context, R.layout.custom_lista_pedidos, pedidos);
        this.context = context;
        listaController = new ListaController(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Pedido pedidoAtual = (Pedido) getItem(position);

        LayoutInflater listaViewInflater = LayoutInflater.from(getContext());
        final View customListaView = listaViewInflater.inflate(R.layout.custom_lista_pedidos, parent, false);

        TextView titulo = (TextView) customListaView.findViewById(R.id.titulo);
        TextView subtitulo = (TextView) customListaView.findViewById(R.id.subtitulo);

        titulo.setText("#"+pedidoAtual.getIdPedido() + " - " + listaController.getLista(pedidoAtual.getIdLista()).getNome());
        subtitulo.setText(pedidoAtual.getStatusNome());


        customListaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentListaView;
                intentListaView = new Intent(context, PedidoView.class);
                intentListaView.putExtra("id_pedido", pedidoAtual.getIdPedido());

                ((Activity) context).startActivityForResult(intentListaView, 1);

            }
        });

        return customListaView;
    }

}
