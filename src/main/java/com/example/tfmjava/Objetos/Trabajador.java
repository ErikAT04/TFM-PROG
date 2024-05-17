package com.example.tfmjava.Objetos;

import java.util.ArrayList;

public class Trabajador extends Persona{
    private int cod_trabajador;
    private String Horario;

    public Trabajador(String dni, String nombre, String apellidos, int cod_trabajador, String horario) {
        super(dni, nombre, apellidos);
        this.cod_trabajador = cod_trabajador;
        Horario = horario;
    }

    public int getCod_trabajador() {
        return cod_trabajador;
    }

    public void setCod_trabajador(int cod_trabajador) {
        this.cod_trabajador = cod_trabajador;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    @Override
    public String toString() {
        return
                "Código de trabajador: " + cod_trabajador +
                ", Horario: '" + Horario + '\'' +
                ", DNI:'" + dni + '\'' +
                ", Nombre y apellidos: '" + nombre + " " + apellidos + '\'';
    }
}
