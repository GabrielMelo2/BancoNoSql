package com.example.bancosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Criar objeto para cada componente da tela
        EditText campoNome = findViewById(R.id.campoNome);
        EditText campoPreco = findViewById(R.id.campoPreco);
        EditText campoQuantidade = findViewById(R.id.campoQuantidade);
        Button btCadastrar = findViewById(R.id.btCadastrar);

        //Evento do botão
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recuperar os valores digitados
                String nome = campoNome.getText().toString();
                double preco = Double.parseDouble(campoPreco.getText().toString());
                int qtde = Integer.parseInt(campoQuantidade.getText().toString());
                //Criar um objeto da classe BancoDeDados
                BancoDeDados bd = new BancoDeDados(MainActivity.this, 1);
                //Testar o retorno do método cadastrar()
                if(bd.cadastrar(nome, preco, qtde)){
                    Toast.makeText(MainActivity.this,
                            "Produto cadastrado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,
                            "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}