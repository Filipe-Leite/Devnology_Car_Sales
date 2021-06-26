package com.example.carsales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.carsales.Uteis.LinhaConsultarAdapter;
import com.example.carsales.model.CarroModel;
import com.example.carsales.repository.CarroRepository;

import java.util.List;

public class ConsultarActivity extends AppCompatActivity {

    //CRIANDO UM OBJETO DO TIPO ListView PARA RECEBER OS REGISTROS DE UM ADAPTER
    ListView listViewCarros;

    //CRIANDO O BOTÃO VOLTAR PARA RETORNAR PARA A TELA COM AS OPÇÕES
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        //VINCULANDO O LISTVIEW DA TELA AO OBJETO CRIADO
        listViewCarros = (ListView)this.findViewById(R.id.listViewCarros);

        //VINCULANDO O BOTÃO VOLTAR DA TELA AO OBJETO CRIADO
        buttonVoltar    = (Button)this.findViewById(R.id.buttonVoltar);

        //CHAMA O MÉTODO QUE CARREGA AS PESSOAS CADASTRADAS NA BASE DE DADOS
        this.CarregarCarrosCadastrados();

        //CHAMA O MÉTODO QUE CRIA O EVENTO PARA O BOTÃO VOLTAR
        this.CriarEvento();
    }
    //MÉTODO QUE CRIA EVENTO PARA O BOTÃO VOLTAR
    protected  void CriarEvento(){

        buttonVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //REDIRECIONA PARA A TELA PRINCIPAL
                Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMainActivity);

                //FINALIZA A ATIVIDADE ATUAL
                finish();
            }
        });
    }
    //MÉTODO QUE CONSULTA AS PESSOAS CADASTRADAS
    protected  void CarregarCarrosCadastrados(){

        CarroRepository carroRepository =  new CarroRepository(this);

        //BUSCA AS PESSOAS CADASTRADAS
        List<CarroModel> carros = carroRepository.SelecionarTodos();

        //SETA O ADAPTER DA LISTA COM OS REGISTROS RETORNADOS DA BASE
        listViewCarros.setAdapter(new LinhaConsultarAdapter(this, carros));
    }
}