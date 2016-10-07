package com.example.mercadodebolso.mercadodebolso.controller;

/**
 * Created by jamesson.ribeiro on 05/04/2016.
 */

import android.content.Context;

import com.example.mercadodebolso.mercadodebolso.dao.factory.FactoryProdutoDao;
import com.example.mercadodebolso.mercadodebolso.dao.interfaces.InterfaceProdutoDao;
import com.example.mercadodebolso.mercadodebolso.model.Produto;

import java.util.ArrayList;

public class ProdutoController {
    private InterfaceProdutoDao produtoDao;
    private Context context;

    public ProdutoController(Context context){
        this.produtoDao= FactoryProdutoDao.getInstance("SQL", context);
    }

    public void excluirProduto(long id){
        produtoDao.remover(id);
    }

    public void exibirTodosOsProdutos(){
        ArrayList<Produto> todos = produtoDao.listarTudo();
        for(int i = 0; i < todos.size(); i++){
            System.out.println("id: " + todos.get(i).getId() + " | Nome: "+ todos.get(i).getNome() + " | Preço: " + todos.get(i).getPreco());
        }
    }

    public String getNomeProduto(long id){
        return getProduto(id).getNome();
    }

    public Produto getProduto(long id){
        Produto produto = (Produto) produtoDao.getById(id);
        return produto;
    }

    public ArrayList<Produto> buscarProdutosByName(String nomeProduto){
        ArrayList<Produto> lista = produtoDao.buscar(nomeProduto);
        return lista;
    }

    public InterfaceProdutoDao getDAO() {
        return produtoDao;
    }

    public void novoProduto(String nome, double preco) {
        Produto novo;
        novo = produtoDao.buscarPorNomeUnico(nome); //busca no banco de dados se ja existe algum produto com exatamenente o mesmo nome
        if (novo == null) {
            //se nao existir, cria um novo
            novo = new Produto(nome, preco);
            produtoDao.inserir(novo);
        } else {
            // senão atualiza o preço
            novo.setPreco(preco);
            produtoDao.update(novo);
        }
    }


    public void iniciarDAO() {
        novoProduto("Achocolatado Líquido 200ml", 1.79);
        novoProduto("Azeite 500ml", 18.50);
        novoProduto("Leite em Pó 400g", 11.92);
        novoProduto("Leite em Caixa 1L", 3.19);
        novoProduto("Creme de Leite 300g", 3.59);
        novoProduto("Óleo de Soja 900ml ", 3.39);
        novoProduto("Açúcar Refinado", 2.89);
        novoProduto("Leite Condensado", 4.19);
        novoProduto("Achocolatado em Pó 800g", 10.59);
        novoProduto("Café 500g", 9.39);
        novoProduto("Feijão 1Kg", 4.95);
        novoProduto("Refrigerante 1,5L", 4.99);
        novoProduto("Sabão em Pó 2Kg", 18.90);
        novoProduto("Amaciante 2L", 10.50);
        novoProduto("Lã de Aço 60g", 1.89);
        novoProduto("Álcool 1L", 6.85);
        novoProduto("Detergente 500ml", 1.65);
        novoProduto("Alvejante 1,5L", 16.90);
        novoProduto("Limpador 500ml", 4.21);
        novoProduto("Água Sanitária 2L", 3.89);
        novoProduto("Água 1,5L", 1.89);
        novoProduto("Cerveja 269ml", 2.29);
        novoProduto("Margarina 500g", 5.52);
        novoProduto("Molho de Tomate 340g", 1.29);
        novoProduto("Guardanapo 50uni.", 1.65);
        novoProduto("Queijo Mussarela 500g", 8.95);
        novoProduto("Pão de Forma 500g", 5.25);
        novoProduto("Biscoito de Chocolate 140g", 1.69);
        novoProduto("Desodorante Aerosol 177ml", 12.90);
        novoProduto("Creme Dental 90g", 2.19);
        novoProduto("Shampoo 350ml", 7.69);
        novoProduto("Condicionador 350ml", 9.59);
    }
}