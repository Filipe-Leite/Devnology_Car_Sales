package com.example.carsales;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //DECLARANDO UM OBJETO LISTVIEW
    ListView listViewOpcoes;

    //MÉTODO onCreate EXECUTADO NA INICIALIZAÇÃO DA ACTIVITY
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //DETERMINA O CONTEÚDO DA NOSSA ACTIVITY
        setContentView(R.layout.activity_main);


        /*CARREGA O MÉTODO DE CRIAÇÃO DOS COMPONENTES*/
        this.CriarComponentes();

        /*CARREGA AS OPÇÕES DA LISTA*/
        this.CarregaOpcoesLista();
    }
    //VINCULA O COMPONENTE DA NOSSA TELA AO OBJETO DA NOSSA ATIVIDADE
    protected void CriarComponentes(){

        //VINCULANDO A LISTA DA TELA AO LISTVIEW QUE DECLARAMOS
        listViewOpcoes = (ListView) this.findViewById(R.id.listViewOpcoes);
    }

    //CRIA A OPÇÕES DA NOSSA LISTA E ADICIONA AO LISTVIEW DA NOSSA TELA.
    protected  void CarregaOpcoesLista(){

        String[] itens = new String[2];

        itens[0] = "Cadastrar";
        itens[1] = "Consultar";

        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itens);


        listViewOpcoes.setAdapter(arrayItens);

    }
}