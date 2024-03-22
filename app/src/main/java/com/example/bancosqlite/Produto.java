package com.example.bancosqlite;

public class Produto {
    public int id;
    public String nome;
    public double preco;
    int quantidade;

    public Produto(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}
