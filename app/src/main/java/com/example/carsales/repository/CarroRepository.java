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
        contentValues.put("ds_marca", carroModel.getMarca());
        contentValues.put("ds_ano", carroModel.getAno());
        contentValues.put("ds_placa", carroModel.getPlaca());
        contentValues.put("ds_cor", carroModel.getCor());
        contentValues.put("ds_chassi", carroModel.getChassi());
        contentValues.put("ds_data", carroModel.getDataCompra());
        contentValues.put("ds_preco", carroModel.getPreco());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        databaseUtil.GetConexaoDataBase().insert("tb_carro", null, contentValues);

    }

    public void SalvarVenda(CarroModel carroModel) {

        ContentValues contentValues = new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("ds_preco_venda", carroModel.getPreco());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        databaseUtil.GetConexaoDataBase().insert("tb_vendido", null, contentValues);

    }

    public Integer Transferir(int codigo){

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_carro WHERE id_carro= " + codigo, null);

        cursor.moveToFirst();

        ContentValues contentValues = new ContentValues();

        CarroModel carroModel = new CarroModel();

        carroModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_carro")));
        carroModel.setModelo(cursor.getString(cursor.getColumnIndex("ds_modelo")));
        carroModel.setMarca(cursor.getString(cursor.getColumnIndex("ds_marca")));
        carroModel.setAno(cursor.getString(cursor.getColumnIndex("ds_ano")));
        carroModel.setPlaca(cursor.getString(cursor.getColumnIndex("ds_placa")));
        carroModel.setCor(cursor.getString(cursor.getColumnIndex("ds_cor")));
        carroModel.setChassi(cursor.getString(cursor.getColumnIndex("ds_chassi")));
        carroModel.setDataCompra(cursor.getString(cursor.getColumnIndex("ds_data")));
        carroModel.setPreco(cursor.getString(cursor.getColumnIndex("ds_preco")));

        contentValues.put("id_carro", carroModel.getCodigo());
        contentValues.put("ds_modelo", carroModel.getModelo());
        contentValues.put("ds_marca", carroModel.getMarca());
        contentValues.put("ds_ano", carroModel.getAno());
        contentValues.put("ds_placa", carroModel.getPlaca());
        contentValues.put("ds_cor", carroModel.getCor());
        contentValues.put("ds_chassi", carroModel.getChassi());
        contentValues.put("ds_data", carroModel.getDataCompra());
        contentValues.put("ds_preco", carroModel.getPreco());

        databaseUtil.GetConexaoDataBase().insert("tb_vendido", null,contentValues);
        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        return databaseUtil.GetConexaoDataBase().delete("tb_carro", "id_carro = ?", new String[]{Integer.toString(codigo)});
    }

    /***
     * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
     * @param carroModel
     */
    public void Atualizar(CarroModel carroModel) {

        ContentValues contentValues =  new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/

        contentValues.put("ds_preco_venda",      carroModel.getPrecoVenda());
        contentValues.put("ds_data_venda",       carroModel.getDataVenda());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        databaseUtil.GetConexaoDataBase().update("tb_vendido", contentValues, "id_vendido = ?", new String[]{Integer.toString(carroModel.getCodigo())});
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
     * CONSULTA UMA CARRO CADASTRADA PELO CÓDIGO
     * @param codigo
     * @return
     */
    public CarroModel GetCarro(int codigo) {


        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_vendido WHERE id_vendido= " + codigo, null);

        cursor.moveToFirst();

        ///CRIANDO UM NOVO CARRO
        CarroModel carroModel = new CarroModel();

        //ADICIONANDO OS DADOS DO CARRO
        carroModel.setCodigoVenda(cursor.getInt(cursor.getColumnIndex("id_vendido")));
        carroModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_carro")));
        carroModel.setModelo(cursor.getString(cursor.getColumnIndex("ds_modelo")));
        carroModel.setPreco(cursor.getString(cursor.getColumnIndex("ds_preco")));
        carroModel.setPrecoVenda(cursor.getString(cursor.getColumnIndex("ds_preco_venda")));

        //RETORNANDO O CARRO
        return carroModel;
    }

    /***
     * CONSULTA TODOS OS CARROS CADASTRADAS NA BASE
     * @return
     */
    public List<CarroModel> SelecionarTodos(){

        List<CarroModel> carros = new ArrayList<CarroModel>();


        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_carro,      ");
        stringBuilderQuery.append("        ds_modelo,       ");
        stringBuilderQuery.append("        ds_marca,       ");
        stringBuilderQuery.append("        ds_ano,       ");
        stringBuilderQuery.append("        ds_placa,       ");
        stringBuilderQuery.append("        ds_cor,       ");
        stringBuilderQuery.append("        ds_chassi,       ");
        stringBuilderQuery.append("        ds_data,       ");
        stringBuilderQuery.append("        ds_preco       ");
        stringBuilderQuery.append("  FROM  tb_carro       ");
        stringBuilderQuery.append(" ORDER BY ds_modelo       ");

        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();

        CarroModel carroModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()) {

            /* CRIANDO UM NOVO CARRO */
            carroModel = new CarroModel();

            //ADICIONANDO OS DADOS DO CARRO
            carroModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_carro")));
            carroModel.setModelo(cursor.getString(cursor.getColumnIndex("ds_modelo")));
            carroModel.setMarca(cursor.getString(cursor.getColumnIndex("ds_marca")));
            carroModel.setAno(cursor.getString(cursor.getColumnIndex("ds_ano")));
            carroModel.setPlaca(cursor.getString(cursor.getColumnIndex("ds_placa")));
            carroModel.setCor(cursor.getString(cursor.getColumnIndex("ds_cor")));
            carroModel.setChassi(cursor.getString(cursor.getColumnIndex("ds_chassi")));
            carroModel.setDataCompra(cursor.getString(cursor.getColumnIndex("ds_data")));
            carroModel.setPreco(cursor.getString(cursor.getColumnIndex("ds_preco")));


            //ADICIONANDO UM CARROS NA LISTA
            carros.add(carroModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }
        //RETORNANDO A LISTA DE CARROS
        return carros;
    }

    public List<CarroModel> SelecionarTodosVendidos() {

        List<CarroModel> carrosvendidos = new ArrayList<CarroModel>();


        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_vendido,      ");
        stringBuilderQuery.append("        id_carro,        ");
        stringBuilderQuery.append("        ds_modelo,       ");
        stringBuilderQuery.append("        ds_marca,        ");
        stringBuilderQuery.append("        ds_ano,          ");
        stringBuilderQuery.append("        ds_cor,          ");
        stringBuilderQuery.append("        ds_placa,        ");
        stringBuilderQuery.append("        ds_chassi,       ");
        stringBuilderQuery.append("        ds_data,         ");
        stringBuilderQuery.append("        ds_data_venda,   ");
        stringBuilderQuery.append("        ds_mes,          ");
        stringBuilderQuery.append("        ds_preco,        ");
        stringBuilderQuery.append("        ds_preco_venda,  ");
        stringBuilderQuery.append("        (ds_preco_venda - ds_preco) as ds_lucro,        ");
        stringBuilderQuery.append("        ds_comissao      ");
        stringBuilderQuery.append("  FROM  tb_vendido       ");
        stringBuilderQuery.append(" ORDER BY ds_modelo      ");

        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();

        CarroModel carroModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()) {

            /* CRIANDO UM NOVO CARRO */
            carroModel = new CarroModel();

            //ADICIONANDO OS DADOS DO CARRO
            carroModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_vendido")));
            carroModel.setCodigoVenda(cursor.getInt(cursor.getColumnIndex("id_carro")));
            carroModel.setModelo(cursor.getString(cursor.getColumnIndex("ds_modelo")));
            carroModel.setMarca(cursor.getString(cursor.getColumnIndex("ds_marca")));
            carroModel.setAno(cursor.getString(cursor.getColumnIndex("ds_ano")));
            carroModel.setCor(cursor.getString(cursor.getColumnIndex("ds_cor")));
            carroModel.setChassi(cursor.getString(cursor.getColumnIndex("ds_chassi")));
            carroModel.setDataCompra(cursor.getString(cursor.getColumnIndex("ds_data")));
            carroModel.setDataVenda(cursor.getString(cursor.getColumnIndex("ds_data_venda")));
            carroModel.setMes(cursor.getString(cursor.getColumnIndex("ds_mes")));
            carroModel.setPlaca(cursor.getString(cursor.getColumnIndex("ds_placa")));
            carroModel.setPreco(cursor.getString(cursor.getColumnIndex("ds_preco")));
            carroModel.setPrecoVenda(cursor.getString(cursor.getColumnIndex("ds_preco_venda")));
            carroModel.setLucro(cursor.getString(cursor.getColumnIndex("ds_lucro")));



            carroModel.setComissao(cursor.getString(cursor.getColumnIndex("ds_comissao")));

            //ADICIONANDO UM CARROS NA LISTA
            carrosvendidos.add(carroModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }
        //RETORNANDO A LISTA DE CARROS
        return carrosvendidos;

    }
}

