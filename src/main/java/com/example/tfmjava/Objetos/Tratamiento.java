package com.example.tfmjava.Objetos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Tratamiento {
    private int cod_trat;
    private String nombre;
    private String descripcion;
    private double precio;
    private double duracion_media_horas;
    public ArrayList<Producto> productos_utilizados;

    public Tratamiento(int cod_trat, String nombre, String descripcion, double precio, double duracion_media_horas) {
        this.cod_trat = cod_trat;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracion_media_horas = duracion_media_horas;
        productos_utilizados = new ArrayList<>();
    }
    public Tratamiento(String nombre, String descripcion, double precio, double duracion_media_horas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracion_media_horas = duracion_media_horas;
        productos_utilizados = new ArrayList<>();
    }
    public int getCod_trat() {
        return cod_trat;
    }

    public void setCod_trat(int cod_trat) {
        this.cod_trat = cod_trat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDuracion_media_horas() {
        return duracion_media_horas;
    }

    public void setDuracion_media_horas(double duracion_media_horas) {
        this.duracion_media_horas = duracion_media_horas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tratamiento that = (Tratamiento) o;
        return getCod_trat() == that.getCod_trat();
    }

    @Override
    public String toString() {
        return
                "Código de tratamiento: " + cod_trat +
                ", Nombre: '" + nombre + '\'' +
                ", Descripción: '" + descripcion + '\'' +
                ", Precio: " + precio +
                ", Duración Media:" + duracion_media_horas;
    }
}
