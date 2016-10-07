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
import com.example.mercadodebolso.mercadodebolso.util.Util;
import com.example.mercadodebolso.mercadodebolso.model.Produto;
import com.example.mercadodebolso.mercadodebolso.view.ItemPopup;


import java.util.ArrayList;

/**
 * Created by Cais - Automação on 06/05/2016.
 */
public class AdapterBuscaProduto extends ArrayAdapter {
    Context context;
    private long idLista;
    private Util comum = new Util();

    public AdapterBuscaProduto(Context context, ArrayList<Produto> produtos, long idLista) {
        super(context, R.layout.custom_lista, produtos);
        this.context = context;
        this.idLista = idLista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Produto produtoAtual = (Produto) getItem(position);

        LayoutInflater listaViewInflater = LayoutInflater.from(getContext());
        final View customListaView = listaViewInflater.inflate(R.layout.custom_lista_produtos, parent, false);

        TextView nomeLista = (TextView) customListaView.findViewById(R.id.idCustomLista_produtos_nome);
        TextView precoLista = (TextView) customListaView.findViewById(R.id.idCustomLista_produtos_preco);

        nomeLista.setText(produtoAtual.getNome());
        precoLista.setText(comum.formatReal(produtoAtual.getPreco()));

        customListaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentListaView = new Intent(context, ItemPopup.class);
                intentListaView.putExtra("id_produto",  produtoAtual.getId());
                intentListaView.putExtra("id_lista", idLista);
                ((Activity) context).startActivityForResult(intentListaView, 1);
            }
        });

        return customListaView;
    }
}
