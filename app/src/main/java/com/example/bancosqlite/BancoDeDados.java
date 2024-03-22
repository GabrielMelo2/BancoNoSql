package com.example.bancosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {
    //Utilizar uma String para cada comando
    private String criaTabela = "CREATE TABLE produto (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(40) NOT NULL, " +
            "preco REAL NOT NULL, " +
            "quantidade INTEGER NOT NULL);";

    //O construtor recebe o ambiente da tela que irá chamar o banco
    //de dados através do Context
    //E recebe o número da versão do banco de dados, geralmente
    //inicia-se em 1 na primeira versão
    public BancoDeDados(@Nullable Context context, int version) {
        //O Factory é uma classe utilizada para refazer o comando
        //SELECT em um padrão diferente. No caso vamos utilizar o
        //comando SELECT padrão, então passamos null.
        super(context, "BancoApp", null, version);
    }

    @Override
    //O método abaixo é utilizado para criar as estruturas iniciais
    //do banco de dados e deixar tudo pronto para o aplicativo
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Executar 1 comando SQL por vez. Se precisar executar
        //outros comandos, é só chamar o método execSQL outra vez
        sqLiteDatabase.execSQL(criaTabela);
    }

    @Override
    //Método executado antes do onCreate
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //Habilitar a chave estrangeira
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    //Esse método so é chamado se houver mudança do int version
    //no construtor
    //O parâmetro int i é a versão antiga
    //O parâmetro int i1 é a versão nova
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Exemplo
        if (i1 == 2) { //Se for a versão nova número 2
            //Executar os comandos para versão 2 do banco de dados
            //sqLiteDatabase.execSQL();
        }
    }

    //Método para realizar o cadastro de um Produto (INSERT)
    public boolean cadastrar(String nome, double preco, int qtde) {
        //Criar um objeto da classe SQLiteDatabase
        SQLiteDatabase banco = getWritableDatabase(); //Abre a conexão
        //Passar os valores de cada coluna
        ContentValues valores = new ContentValues();
        //Nome da coluna e o valor que vai nela
        valores.put("nome", nome);
        valores.put("preco", preco);
        valores.put("quantidade", qtde);

        //Executar o comando INSERT e testar se deu certo ou não
        if (banco.insert("produto", //Nome da tabela
                null, //null indica que todas as colunas tem um valor
                valores) != -1) { //Se o insert der certo
            return true;
        } else {
            return false;
        }
    }

    public List<Produto> buscarTodos() {
        SQLiteDatabase banco = getWritableDatabase();
        Cursor retorno = banco.query("produto", null, null, null, null, null, null);

        List<Produto> lista = new ArrayList<>();
        if (retorno.moveToFirst()) {
            do {
                Produto p = new Produto(retorno.getInt(0), retorno.getString(1), retorno.getDouble(2), retorno.getInt(3));
            } while (retorno.moveToNext());

        }
        return lista;
    }

}
