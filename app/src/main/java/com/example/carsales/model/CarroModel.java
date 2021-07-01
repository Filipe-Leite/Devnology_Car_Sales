package com.example.carsales.model;


public class CarroModel {

    private Integer codigo;
    private Integer codigovenda;
    private String  modelo;
    private String precovenda;
    private String  preco;


    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoVenda()  {
        return codigovenda;
    }
    public void setCodigoVenda(Integer codigo) {
        this.codigovenda = codigo;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPrecoVenda() {
        return precovenda;
    }
    public void setPrecoVenda(String precodevenda) {
        this.precovenda = precodevenda;
    }

    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }
}