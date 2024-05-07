package com.example.tfmjava.Objetos;

import java.util.Date;

public class Cliente extends Persona {
    int telf;
    int edad;
    Date fnac;
    String especificaciones;
    public Cliente(String dni, String nombre, String apellidos, int telf, int edad, Date fnac, String especificaciones) {
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

    public Date getFnac() {
        return fnac;
    }

    public void setFnac(Date fnac) {
        this.fnac = fnac;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }
}