package com.example.carsales.Uteis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseUtil extends SQLiteOpenHelper {

    //NOME DA BASE DE DADOS
    private static final String NOME_BASE_DE_DADOS   = "SISTEMA.db";

    //VERSÃO DO BANCO DE DADOS
    private static final int    VERSAO_BASE_DE_DADOS = 1;

    //CONSTRUTOR
    public DatabaseUtil(Context context){

        super(context,NOME_BASE_DE_DADOS,null,VERSAO_BASE_DE_DADOS);
    }

    /*NA INICIALIZAÇÃO DA CLASSE VAMOS CRIAR A TABELA QUE VAMOS USAR*/
    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder stringBuilderCreateTable = new StringBuilder();

        stringBuilderCreateTable.append(" CREATE TABLE tb_carro (");
        stringBuilderCreateTable.append("        id_carro      INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTable.append("        ds_modelo        TEXT    NOT NULL,               ");
        stringBuilderCreateTable.append("        ds_marca         TEXT    NOT NULL,               ");
        stringBuilderCreateTable.append("        ds_ano           TEXT    NOT NULL,               ");
        stringBuilderCreateTable.append("        ds_placa         TEXT    NOT NULL,               ");
        stringBuilderCreateTable.append("        ds_cor           TEXT    NOT NULL,               ");
        stringBuilderCreateTable.append("        ds_chassi        TEXT    NOT NULL,               ");
        stringBuilderCreateTable.append("        ds_data          TEXT    NOT NULL,               ");
        stringBuilderCreateTable.append("        ds_preco         REAL    NOT NULL)               ");

        db.execSQL(stringBuilderCreateTable.toString());

        StringBuilder stringBuilderCreateTableVendido = new StringBuilder();

        stringBuilderCreateTableVendido.append(" CREATE TABLE tb_vendido (");
        stringBuilderCreateTableVendido.append("        id_vendido      INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTableVendido.append("        id_carro        INTEGER    NOT NULL,               ");
        stringBuilderCreateTableVendido.append("        ds_modelo        TEXT      NOT NULL,               ");
        stringBuilderCreateTableVendido.append("        ds_marca         TEXT      NOT NULL,               ");
        stringBuilderCreateTableVendido.append("        ds_ano           TEXT      NOT NULL,               ");
        stringBuilderCreateTableVendido.append("        ds_placa         TEXT      NOT NULL,               ");
        stringBuilderCreateTableVendido.append("        ds_cor           TEXT      NOT NULL,               ");
        stringBuilderCreateTableVendido.append("        ds_chassi        TEXT      NOT NULL,               ");
        stringBuilderCreateTableVendido.append("        ds_data          TEXT      NOT NULL,               ");
        stringBuilderCreateTableVendido.append("        ds_data_venda    TEXT      NULL,                   ");
        stringBuilderCreateTableVendido.append("        ds_mes           TEXT      NULL,                   ");
        stringBuilderCreateTableVendido.append("        ds_preco         REAL      NOT NULL,               ");
        stringBuilderCreateTableVendido.append("        ds_preco_venda   REAL      NULL,                   ");
        stringBuilderCreateTableVendido.append("        ds_comissao      REAL      NULL)                   ");

        db.execSQL(stringBuilderCreateTableVendido.toString());


    }
    /*SE TROCAR A VERSÃO DO BANCO DE DADOS VOCÊ PODE EXECUTAR ALGUMA ROTINA
      COMO CRIAR COLUNAS, EXCLUIR ENTRE OUTRAS */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS tb_carro");
        onCreate(db);

    }

    /*MÉTODO QUE VAMOS USAR NA CLASSE QUE VAI EXECUTAR AS ROTINAS NO
    BANCO DE DADOS*/
    public SQLiteDatabase GetConexaoDataBase(){

        return this.getWritableDatabase();
    }
}
