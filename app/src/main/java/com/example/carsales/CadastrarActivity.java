package com.example.carsales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carsales.model.CarroModel;
import com.example.carsales.repository.CarroRepository;

public class CadastrarActivity extends AppCompatActivity {


    /*COMPONENTES DA TELA*/
    EditText editTextModelo;
    EditText editTextPreco;
    Button buttonSalvar;
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
        this.CriarComponentes();

        //CRIA OS EVENTOS DOS COMPONENTES
        this.CriarEventos();
    }

    //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected void CriarComponentes() {

        editTextModelo = (EditText) this.findViewById(R.id.editTextModelo);
        editTextPreco = (EditText) this.findViewById(R.id.editTextPreco);
        buttonSalvar = (Button) this.findViewById(R.id.buttonSalvar);
        buttonVoltar = (Button) this.findViewById(R.id.buttonVoltar);

    }

    //CRIA OS EVENTOS DOS COMPONENTES
    protected void CriarEventos() {

        //CRIANDO EVENTO NO BOTÃO SALVAR
        buttonSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Salvar_onClick();
            }
        });
        //CRIANDO EVENTO NO BOTÃO VOLTAR
        buttonVoltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        });
    }

    //VALIDA OS CAMPOS E SALVA AS INFORMAÇÕES NO BANCO DE DADOS
    protected void Salvar_onClick() {

            /*CRIANDO UM OBJETO PESSOA*/
            CarroModel carroModel = new CarroModel();

            /*SETANDO O VALOR DO CAMPO MODELO*/
            carroModel.setModelo(editTextModelo.getText().toString().trim());

            /*SETANDO O VALOR DO CAMPO PRECO*/
            carroModel.setPreco(editTextPreco.getText().toString().trim());

            /*SALVANDO UM NOVO REGISTRO*/
            new CarroRepository(this).Salvar(carroModel);
            LimparCampos();
        }

        //LIMPA OS CAMPOS APÓS SALVAR AS INFORMAÇÕES
        protected void LimparCampos () {

            editTextModelo.setText(null);
            editTextPreco.setText(null);
        }
}