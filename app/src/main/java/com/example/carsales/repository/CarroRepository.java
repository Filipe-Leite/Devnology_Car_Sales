package com.example.carsales.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.carsales.Uteis.DatabaseUtil;
import com.example.carsales.model.CarroModel;

import java.util.ArrayList;
import java.util.List;

public class CarroRepository {

    DatabaseUtil databaseUtil;

    /***
     * CONSTRUTOR
     * @param context
     */
    public CarroRepository(Context context) {

        databaseUtil = new DatabaseUtil(context);

    }

    /***
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param carroModel
     */
    public void Salvar(CarroModel carroModel) {

        ContentValues contentValues = new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("ds_modelo", carroModel.getModelo());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        databaseUtil.GetConexaoDataBase().insert("tb_carro", null, contentValues);

    }

    /***
     * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
     * @param carroModel
     */
    public void Atualizar(CarroModel carroModel) {

        ContentValues contentValues = new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/
        contentValues.put("ds_nome", carroModel.getModelo());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        databaseUtil.GetConexaoDataBase().update("tb_carro", contentValues, "id_carro = ?", new String[]{Integer.toString(carroModel.getCodigo())});
    }

    /***
     * EXCLUI UM REGISTRO PELO CÓDIGO
     * @param codigo
     * @return
     */
    public Integer Excluir(int codigo) {

        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        return databaseUtil.GetConexaoDataBase().delete("tb_carro", "id_carro = ?", new String[]{Integer.toString(codigo)});
    }

    /***
     * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
     * @param codigo
     * @return
     */
    public CarroModel GetCarro(int codigo) {


        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_carro WHERE id_carro= " + codigo, null);

        cursor.moveToFirst();

        ///CRIANDO UMA NOVA PESSOAS
        CarroModel carroModel = new CarroModel();

        //ADICIONANDO OS DADOS DA CARRO
        carroModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_carro")));
        carroModel.setModelo(cursor.getString(cursor.getColumnIndex("ds_modelo")));

        //RETORNANDO A PESSOA
        return carroModel;
    }

    /***
     * CONSULTA TODAS AS PESSOAS CADASTRADAS NA BASE
     * @return
     */
    public List<CarroModel> SelecionarTodos(){

        List<CarroModel> carros = new ArrayList<CarroModel>();


        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_carro,      ");
        stringBuilderQuery.append("        ds_modelo       ");
        stringBuilderQuery.append("  FROM  tb_carro       ");
        stringBuilderQuery.append(" ORDER BY ds_modelo       ");

        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();

        CarroModel carroModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()) {

            /* CRIANDO UMA NOVA PESSOAS */
            carroModel = new CarroModel();

            //ADICIONANDO OS DADOS DA PESSOA
            carroModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_carro")));
            carroModel.setModelo(cursor.getString(cursor.getColumnIndex("ds_modelo")));


            //ADICIONANDO UMA PESSOA NA LISTA
            carros.add(carroModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }
        //RETORNANDO A LISTA DE PESSOAS
        return carros;
    }
}

