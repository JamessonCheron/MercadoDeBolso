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
import com.example.mercadodebolso.mercadodebolso.model.Item;
import com.example.mercadodebolso.mercadodebolso.view.ItemPopup;
import com.example.mercadodebolso.mercadodebolso.view.ListaView;

import java.util.ArrayList;

/**
 * Created by Uirá Veríssimo on 17/04/2016.
 */
public class AdapterItens extends ArrayAdapter {
    private Context context;
    private Util comum = new Util();
    private ArrayList<Item> selecionados = new ArrayList();
    private ListaController listacontroller;
    private Long idLista;
    private String nomeLista;
    private int selectClick = 0;

    public AdapterItens(Context context, ArrayList<Item> itens, long idLista) {
        super(context, R.layout.custom_lista, itens);
        listacontroller = new ListaController(context);
        this.context = context;
        this.idLista = idLista;
        this.nomeLista = listacontroller.getLista(idLista).getNome();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Item itemAtual = (Item) getItem(position);

        LayoutInflater listaViewInflater = LayoutInflater.from(getContext());
        final View customListaView = listaViewInflater.inflate(R.layout.custom_lista, parent, false);

        TextView nomeLista = (TextView) customListaView.findViewById(R.id.idCustomLista_listas_nome);
        TextView qtdLista = (TextView) customListaView.findViewById(R.id.idCustomLista_listas_qtd);
        TextView precoLista = (TextView) customListaView.findViewById(R.id.idCustomLista_listas_preco);


        nomeLista.setText(itemAtual.getProduto().getNome());
        qtdLista.setText(Integer.toString(itemAtual.getQtd()));
        precoLista.setText(comum.formatReal(itemAtual.getPrecoItem()));


        customListaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectClick == 0) {
                    Intent intentListaView;
                    intentListaView = new Intent(context, ItemPopup.class);
                    intentListaView.putExtra("id_lista", itemAtual.getIdLista());
                    intentListaView.putExtra("id_item", itemAtual.getIdItem());
                    intentListaView.putExtra("quantidade", itemAtual.getQtd());
                    intentListaView.putExtra("id_produto", itemAtual.getProduto().getId());
                    intentListaView.putExtra("modo", "editar");
                    ((Activity) context).startActivityForResult(intentListaView, 1);
                } else {
                    selecionar(v, itemAtual);
                }
            }
        });

        customListaView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selectClick = 1;
                selecionar(v, itemAtual);
                return true;
            }
        });

        return customListaView;
    }

    private void selecionar(View v, Item atual){
        LinearLayout bg = (LinearLayout) v.findViewById(R.id.bgListView);
        TextView bgQtd = (TextView) v.findViewById(R.id.idCustomLista_listas_qtd);
        Button btnRemover = (Button) ((Activity) context).findViewById(R.id.btnRemover);

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Item atual : selecionados) {
                    listacontroller.removerItem(atual.getId());
                }
                ((ListaView) context).loadListaView();
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
        TextView titulo = (TextView) ((Activity) context).findViewById(R.id.nomeLista);
        Button btnAdd = (Button) ((Activity) context).findViewById(R.id.Add);
        Button btnRemover = (Button) ((Activity) context).findViewById(R.id.btnRemover);

        if(selecionados.isEmpty()){
            selectClick = 0;
            titulo.setText(nomeLista);
            btnAdd.setVisibility(View.VISIBLE);
            btnRemover.setVisibility(View.INVISIBLE);
        } else{
            if(selecionados.size() == 1) {
                titulo.setText(selecionados.size() + " selecionado");
            } else{
                titulo.setText(selecionados.size() + " selecionados");
            }
            btnRemover.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.INVISIBLE);
        }
    }
}
