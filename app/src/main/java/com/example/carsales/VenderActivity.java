package com.example.carsales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carsales.model.CarroModel;
import com.example.carsales.repository.CarroRepository;


public class VenderActivity extends AppCompatActivity {

    /*COMPONENTES DA TELA*/
    EditText editTextCodigo;
    EditText editTextPrecoVenda;
    EditText editTextDataVenda;
    Button buttonVender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender);

        //CHAMA O MÉTODO PARA CRIAR OS COMPONENTES DA TELA
        this.CriarComponentes();

        //CHAMA O MÉTODO QUE CRIA EVENTOS PARA OS COMPONENTES
        this.CriarEventos();

        //CARREGA OS VALORES NOS CAMPOS DA TELA.
        this.CarregaValoresCampos();
    }


    //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected void CriarComponentes() {

        editTextCodigo     = (EditText) this.findViewById(R.id.editTextCodigo);
        editTextPrecoVenda = (EditText) this.findViewById(R.id.editTextPrecoVenda);
        editTextDataVenda  = (EditText) this.findViewById(R.id.editTextDataVenda);
        buttonVender       = (Button) this.findViewById(R.id.buttonVender);
    }

    //CRIA OS EVENTOS DOS COMPONENTES
    protected void CriarEventos() {

        //CRIANDO EVENTO NO BOTÃO SALVAR
        buttonVender.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Alterar_onClick();
            }
        });
    }


    //ALTERA UM REGISTRO
    protected void Alterar_onClick() {

        /*CRIANDO UM OBJETO CARRO*/
        CarroModel carroModel = new CarroModel();

        carroModel.setCodigo(Integer.parseInt(editTextCodigo.getText().toString()));

        /*SETANDO O VALOR DO CAMPO DE VENDA*/
        carroModel.setPrecoVenda(editTextPrecoVenda.getText().toString().trim());

        /*SETANDO O VALOR DO CAMPO DE VENDA*/
        carroModel.setDataVenda(editTextDataVenda.getText().toString().trim());

        /*ALTERANDO O REGISTRO*/
        new CarroRepository(this).Atualizar(carroModel);


        Intent intentRedirecionar = new Intent(getApplicationContext(), ConsultarActivity.class);

        startActivity(intentRedirecionar);

        finish();


    }

    //CARREGA OS VALORES NOS CAMPOS APÓS RETORNAR DO SQLITE
    protected void CarregaValoresCampos() {

        CarroRepository carroRepository = new CarroRepository(this);

        //PEGA O ID CARRO QUE FOI PASSADO COMO PARAMETRO ENTRE AS TELAS

        Bundle extra = this.getIntent().getExtras();
        int id_carro = extra.getInt("id_vendido");

        //CONSULTA UMA VENDA POR ID
        CarroModel carroModel = carroRepository.GetCarro(id_carro);

        //SETA O CÓDIGO NA VIEW
        editTextCodigo.setText(String.valueOf(carroModel.getCodigoVenda()));

        //SETA O NOME NA VIEW
        editTextDataVenda.setText(carroModel.getDataVenda());

        //SETA O PRECO DE VENDA NA VIEW
        editTextPrecoVenda.setText(carroModel.getPrecoVenda());


    }

}