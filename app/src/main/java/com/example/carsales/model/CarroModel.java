package com.example.carsales.model;


public class CarroModel {

    private Integer codigo;
    private Integer codigovenda;
    private String  modelo;
    private String  marca;
    private String  ano;
    private String  placa;
    private String  cor;
    private String  chassi;
    private String  datacompra;
    private String  datavenda;
    private String  preco;
    private String  precovenda;
    private String  lucro;
    private String  comissao;


    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoVenda()  {
        return codigovenda;
    }
    public void setCodigoVenda(Integer codigovenda) {
        this.codigovenda = codigovenda;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAno() {
        return ano;
    }
    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getChassi() {
        return chassi;
    }
    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getDataCompra() {
        return datacompra;
    }
    public void setDataCompra(String datacompra) {
        this.datacompra = datacompra;
    }

    public String getDataVenda() {
        return datavenda;
    }
    public void setDataVenda(String datavenda) {
        this.datavenda = datavenda;
    }

    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getPrecoVenda() {
        return precovenda;
    }
    public void setPrecoVenda(String precodevenda) {
        this.precovenda = precodevenda;
    }

    public String getLucro() {
        return lucro;
    }
    public void setLucro(String lucro) {
        this.lucro = lucro;
    }

    public String getComissão() {
        return comissao;
    }
    public void setComissao(String comissao) {
        this.comissao = comissao;
    }

}