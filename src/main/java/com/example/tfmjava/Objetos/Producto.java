package com.example.tfmjava.Objetos;

import java.util.Objects;

public class Producto {
    private int cod_prod;
    private int stock;
    private String nombre;
    private String marca;

    public Producto(int cod_prod, int stock, String nombre, String marca) {
        this.cod_prod = cod_prod;
        this.stock = stock;
        this.nombre = nombre;
        this.marca = marca;
    }

    public Producto(int stock, String nombre, String marca) {
        this.stock = stock;
        this.nombre = nombre;
        this.marca = marca;
    }

    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return cod_prod == producto.cod_prod;
    }
}
