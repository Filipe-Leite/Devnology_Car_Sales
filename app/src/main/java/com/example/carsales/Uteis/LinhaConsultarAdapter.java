package com.example.carsales.Uteis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carsales.ConsultarActivity;
import com.example.carsales.R;
import com.example.carsales.VenderActivity;
import com.example.carsales.model.CarroModel;
import com.example.carsales.repository.CarroRepository;

import java.util.ArrayList;
import java.util.List;

public class LinhaConsultarAdapter extends BaseAdapter {

    //CRIANDO UM OBJETO LayoutInflater PARA FAZER LINK A NOSSA VIEW(activity_linha_consultar.xml)
    private static LayoutInflater layoutInflater = null;

    //CRIANDO UMA LISTA DE CARROS
    List<CarroModel> carroModels = new ArrayList<CarroModel>();

    //CIRANDO UM OBJETO DA NOSSA CLASSE QUE FAZ ACESSO AO BANCO DE DADOS
    CarroRepository carroRepository;

    //CRIANDO UM OBJETO DA NOSSA ATIVIDADE QUE CONTEM A LISTA
    private ConsultarActivity consultarActivity;

    //CONSTRUTOR QUE VAI RECEBER A NOSSA ATIVIDADE COMO PARAMETRO E A LISTA DE CARROS QUE VAI RETORNAR
    //DA NOSSA BASE DE DADOS
    public LinhaConsultarAdapter(ConsultarActivity consultarActivity, List<CarroModel> carroModels) {

        this.carroModels = carroModels;
        this.consultarActivity = consultarActivity;
        this.layoutInflater = (LayoutInflater) this.consultarActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.carroRepository = new CarroRepository(consultarActivity);
    }
    //RETORNA A QUANTIDADE DE REGISTROS DA LISTA
    @Override
    public int getCount(){

        return carroModels.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    //ESSE MÉTODO SETA OS VALORES DE UM ITEM DA NOSSA LISTA DE PESSOAS PARA UMA LINHA DO NOSSO LISVIEW
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //CRIANDO UM OBJETO DO TIPO View PARA ACESSAR O NOSSO ARQUIVO DE LAYOUT activity_linha_consultar.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha_consultar,null);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewCodigo          = (TextView) viewLinhaLista.findViewById(R.id.textViewCodigo);

        //CAMPO QUE VAI MOSTRAR O MODELO DO CARRO
        TextView textViewModelo            = (TextView) viewLinhaLista.findViewById(R.id.textViewModelo);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewMarca          = (TextView) viewLinhaLista.findViewById(R.id.textViewMarca);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewAno          = (TextView) viewLinhaLista.findViewById(R.id.textViewAno);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewPlaca         = (TextView) viewLinhaLista.findViewById(R.id.textViewPlaca);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewCor          = (TextView) viewLinhaLista.findViewById(R.id.textViewCor);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewChassi          = (TextView) viewLinhaLista.findViewById(R.id.textViewChassi);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewData          = (TextView) viewLinhaLista.findViewById(R.id.textViewData);

        //CAMPO QUE VAI MOSTRAR O PREÇO DO CARRO
        TextView textViewPreco            = (TextView) viewLinhaLista.findViewById(R.id.textViewPreco);

        //CRIANDO O BOTÃO  EXCLUIR PARA DELETARMOS UM REGISTRO DO BANCO DE DADOS
        Button buttonExcluir             = (Button)   viewLinhaLista.findViewById(R.id.buttonExcluir);

        //CRIANDO O BOTÃO PARA VENDER UM REGISTRO CADASTRADO
        Button   buttonVender            = (Button)   viewLinhaLista.findViewById(R.id.buttonVender);

        //SETANDO O CÓDIGO NO CAMPO DA NOSSA VIEW
        textViewCodigo.setText(String.valueOf(carroModels.get(position).getCodigo()));

        //SETANDO O MODELO NO CAMPO DA NOSSA VIEW
        textViewModelo.setText(carroModels.get(position).getModelo());

        //SETANDO O MODELO NO CAMPO DA NOSSA VIEW
        textViewMarca.setText(carroModels.get(position).getMarca());

        //SETANDO O MODELO NO CAMPO DA NOSSA VIEW
        textViewAno.setText(carroModels.get(position).getAno());

        //SETANDO O MODELO NO CAMPO DA NOSSA VIEW
        textViewPlaca.setText(carroModels.get(position).getPlaca());

        //SETANDO O MODELO NO CAMPO DA NOSSA VIEW
        textViewCor.setText(carroModels.get(position).getCor());

        //SETANDO O MODELO NO CAMPO DA NOSSA VIEW
        textViewChassi.setText(carroModels.get(position).getChassi());

        //SETANDO O MODELO NO CAMPO DA NOSSA VIEW
        textViewData.setText(carroModels.get(position).getDataCompra());

        //SETANDO O PREÇO NO CAMPO DA NOSSA VIEW
        textViewPreco.setText(carroModels.get(position).getPreco());

        //CRIANDO EVENTO CLICK PARA O BOTÃO DE EXCLUIR REGISTRO
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //EXCLUINDO UM REGISTRO
                carroRepository.Excluir(carroModels.get(position).getCodigo());

                //MOSTRA A MENSAGEM APÓS EXCLUIR UM REGISTRO
                Toast.makeText(consultarActivity, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();

                //CHAMA O MÉTODO QUE ATUALIZA A LISTA COM OS REGISTROS QUE AINDA ESTÃO NA BASE
                AtualizarLista();

            }
        });
        //CRIANDO EVENTO CLICK PARA O BOTÃO QUE VAI REDIRECIONAR PARA A TELA DE EDIÇÃO
        // DO REGISTRO.
        buttonVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //EXCLUINDO DA LISTA DE CARROS DISPONÍVEIS
                carroRepository.Transferir(carroModels.get(position).getCodigo());


                Intent intentRedirecionar = new Intent(consultarActivity, VenderActivity.class);

                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intentRedirecionar.putExtra("id_vendido",carroModels.get(position).getCodigo());

                consultarActivity.startActivity(intentRedirecionar);

                consultarActivity.finish();

            }
        });

        return viewLinhaLista;
    }
    //ATUALIZA A LISTA DEPOIS DE EXCLUIR UM REGISTRO
    public void AtualizarLista(){

        this.carroModels.clear();
        this.carroModels = carroRepository.SelecionarTodos();
        this.notifyDataSetChanged();
    }

}