package com.example.mercadodebolso.mercadodebolso.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.util.Util;
import com.example.mercadodebolso.mercadodebolso.controller.ListaController;
import com.example.mercadodebolso.mercadodebolso.model.Lista;
import com.example.mercadodebolso.mercadodebolso.view.ListaView;
import com.example.mercadodebolso.mercadodebolso.view.Main;

import java.util.ArrayList;

/**
 * Created by Uirá Veríssimo on 17/04/2016.
 */
public class AdapterListas extends ArrayAdapter {
    Context context;
    private ArrayList<Lista> selecionados = new ArrayList();
    private int selectClick = 0;
    private ListaController listacontroller;
    private Util comum = new Util();

    public AdapterListas(Context context, ArrayList<Lista> listas) {
        super(context, R.layout.custom_lista, listas);
        this.context = context;
        listacontroller = new ListaController(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Lista listaAtual = (Lista) getItem(position);

        LayoutInflater listaViewInflater = LayoutInflater.from(getContext());
        final View customListaView = listaViewInflater.inflate(R.layout.custom_lista, parent, false);

        TextView nomeLista = (TextView) customListaView.findViewById(R.id.idCustomLista_listas_nome);
        TextView qtdLista = (TextView) customListaView.findViewById(R.id.idCustomLista_listas_qtd);
        TextView precoLista = (TextView) customListaView.findViewById(R.id.idCustomLista_listas_preco);

        nomeLista.setText(listaAtual.getNome());
        qtdLista.setText(Integer.toString(listaAtual.getQtdItens()));
        precoLista.setText(comum.formatReal(listaAtual.getPrecoLista()));


        customListaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectClick == 0) {
                    Intent intentListaView;
                    intentListaView = new Intent(context, ListaView.class);
                    intentListaView.putExtra("id_lista", listaAtual.getId());

                    ((Activity) context).startActivityForResult(intentListaView, 1);
                } else {
                    selecionar(v, listaAtual);
                }
            }
        });

        customListaView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selectClick = 1;
                selecionar(v, listaAtual);
                return true;
            }
        });

        return customListaView;
    }

    private void selecionar(View v, Lista atual){
        LinearLayout bg = (LinearLayout) v.findViewById(R.id.bgListView);
        TextView bgQtd = (TextView) v.findViewById(R.id.idCustomLista_listas_qtd);
        Button btnRemover = (Button) ((Activity) context).findViewById(R.id.btnRemover);

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Lista atual : selecionados) {
                    listacontroller.removerLista(atual.getId());
                }
                ((Main) context).loadListas();
                selecionados.clear();
                toggleViewRemover();
            }
        });

        if(selecionados.contains(atual)){
            selecionados.remove(atual);
            bg.setBackgroundColor(ContextCompat.getColor(context, R.color.quaseBranco));
            bgQtd.setBackgroundColor(ContextCompat.getColor(context, R.color.quaseBranco));
        }else{
            selecionados.add(atual);
            bg.setBackgroundColor(ContextCompat.getColor(context, R.color.selecioado));
            bgQtd.setBackgroundColor(ContextCompat.getColor(context, R.color.selecioado));
        }
        toggleViewRemover();
    }
    private void toggleViewRemover() {
        Button btnRemover = (Button) ((Activity) context).findViewById(R.id.btnRemover);
        TextView titulo = (TextView) ((Activity) context).findViewById(R.id.tituloMain);

        if(selecionados.isEmpty()){
            selectClick = 0;
            btnRemover.setVisibility(View.INVISIBLE);
            titulo.setText(R.string.app_name);
        } else{
            if(selecionados.size() == 1) {
                titulo.setText(selecionados.size() + " selecionado");
            } else{
                titulo.setText(selecionados.size() + " selecionados");
            }
            btnRemover.setVisibility(View.VISIBLE);
        }
    }
}
