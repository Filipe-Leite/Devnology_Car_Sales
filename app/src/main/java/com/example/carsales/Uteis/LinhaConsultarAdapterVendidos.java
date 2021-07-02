package com.example.carsales.Uteis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carsales.ConsultarCarrosVendidosActivity;
import com.example.carsales.R;
import com.example.carsales.model.CarroModel;
import com.example.carsales.repository.CarroRepository;

import java.util.ArrayList;
import java.util.List;

public class LinhaConsultarAdapterVendidos extends BaseAdapter {

    //CRIANDO UM OBJETO LayoutInflater PARA FAZER LINK A NOSSA VIEW(activity_linha_consultar.xml)
    private static LayoutInflater layoutInflater = null;

    //CRIANDO UMA LISTA DE CARROS
    List<CarroModel> carroModels = new ArrayList<CarroModel>();

    //CIRANDO UM OBJETO DA NOSSA CLASSE QUE FAZ ACESSO AO BANCO DE DADOS
    CarroRepository carroRepository;

    //CRIANDO UM OBJETO DA NOSSA ATIVIDADE QUE CONTEM A LISTA
    private ConsultarCarrosVendidosActivity consultarCarrosVendidosActivity;

    //CONSTRUTOR QUE VAI RECEBER A NOSSA ATIVIDADE COMO PARAMETRO E A LISTA DE CARROS QUE VAI RETORNAR
    //DA NOSSA BASE DE DADOS
    public LinhaConsultarAdapterVendidos(ConsultarCarrosVendidosActivity consultarCarrosVendidosActivity, List<CarroModel> carroModels) {

        this.carroModels = carroModels;
        this.consultarCarrosVendidosActivity = consultarCarrosVendidosActivity;
        this.layoutInflater = (LayoutInflater) this.consultarCarrosVendidosActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.carroRepository = new CarroRepository(consultarCarrosVendidosActivity);
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
    //ESSE MÉTODO SETA OS VALORES DE UM ITEM DA NOSSA LISTA DE CARROS VENDIDOS PARA UMA LINHA DO NOSSO LISTVIEW
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //CRIANDO UM OBJETO DO TIPO View PARA ACESSAR O NOSSO ARQUIVO DE LAYOUT activity_linha_consultar_vendidos.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha_consultar_vendidos,null);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar_vendidos.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewCodigo                 = (TextView) viewLinhaLista.findViewById(R.id.textViewCodigo);

        //CAMPO QUE VAI MOSTRAR O MODELO DO CARRO
        TextView textViewModelo                 = (TextView) viewLinhaLista.findViewById(R.id.textViewModelo);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewPlaca                  = (TextView) viewLinhaLista.findViewById(R.id.textViewPlaca);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewLucro                  = (TextView) viewLinhaLista.findViewById(R.id.textViewLucro);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewDataVenda              = (TextView) viewLinhaLista.findViewById(R.id.textViewDataVenda);

        //CAMPO QUE VAI MOSTRAR O PREÇO DO CARRO
        TextView textViewPrecoCompra            = (TextView) viewLinhaLista.findViewById(R.id.textViewPrecoCompra);

        //CAMPO QUE VAI MOSTRAR O PREÇO DO CARRO
        TextView textViewPrecoVenda             = (TextView) viewLinhaLista.findViewById(R.id.textViewPrecoVenda);

        //SETANDO O CÓDIGO NO CAMPO DA NOSSA VIEW
        textViewCodigo.setText(String.valueOf(carroModels.get(position).getCodigo()));

        //SETANDO O MODELO NO CAMPO DA NOSSA VIEW
        textViewModelo.setText(carroModels.get(position).getModelo());

        //SETANDO O DATA DE VENDA NO CAMPO DA NOSSA VIEW
        textViewDataVenda.setText(carroModels.get(position).getDataVenda());

        //SETANDO O LUCRO NO CAMPO DA NOSSA VIEW
        textViewLucro.setText(carroModels.get(position).getLucro());

        //SETANDO O PLACA NO CAMPO DA NOSSA VIEW
        textViewPlaca.setText(carroModels.get(position).getPlaca());

        //SETANDO O PREÇO DE COMPRA NO CAMPO DA NOSSA VIEW
        textViewPrecoCompra.setText(carroModels.get(position).getPreco());

        //SETANDO O PREÇO DE VENDA NO CAMPO DA NOSSA VIEW
        textViewPrecoVenda.setText(carroModels.get(position).getPrecoVenda());

        return viewLinhaLista;
    }
}