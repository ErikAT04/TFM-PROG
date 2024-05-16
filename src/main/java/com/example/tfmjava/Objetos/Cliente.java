package com.example.tfmjava.Objetos;

import java.time.LocalDate;
import java.util.Date;

public class Cliente extends Persona {
    private int telf;
    private int edad;
    private LocalDate fnac;
    private String especificaciones;
    public Cliente(String dni, String nombre, String apellidos, int telf, int edad, LocalDate fnac, String especificaciones) {
        super(dni, nombre, apellidos);
        this.telf = telf;
        this.edad = edad;
        this.fnac = fnac;
        this.especificaciones = especificaciones;
    }

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFnac() {
        return fnac;
    }

    public void setFnac(LocalDate fnac) {
        this.fnac = fnac;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }
}