package com.example.mercadodebolso.mercadodebolso.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.model.Lista;

import java.util.ArrayList;

/**
 * Created by Uirá Veríssimo on 17/04/2016.
 */
public class CustomAdapterListaListas extends ArrayAdapter {
    CustomAdapterListaListas(Context context, ArrayList<Lista> listas) {
        super(context, R.layout.custom_lista, listas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Lista listaAtual = (Lista) getItem(position);

        LayoutInflater listaViewInflater = LayoutInflater.from(getContext());
        View customListaView = listaViewInflater.inflate(R.layout.custom_lista, parent, false);

        TextView nomeLista = (TextView) customListaView.findViewById(R.id.idCustomLista_listas_nome);
        TextView qtdLista = (TextView) customListaView.findViewById(R.id.idCustomLista_listas_qtd);
        TextView precoLista = (TextView) customListaView.findViewById(R.id.idCustomLista_listas_preco);

        nomeLista.setText(listaAtual.getNome());
        qtdLista.setText(listaAtual.getQtdItens());
        precoLista.setText(listaAtual.getPrecoLista().toString());

        return customListaView;
    }
}
