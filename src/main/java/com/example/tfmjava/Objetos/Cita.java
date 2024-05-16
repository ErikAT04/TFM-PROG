package com.example.tfmjava.Objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cita {
    private int cod_cita;
    private LocalDate fecha_hora;
    private String observaciones;
    private Cliente cliente;
    private Trabajador trabajador;
    private Tratamiento tratamiento;

    public Cita(int cod_cita, LocalDate fecha_hora, String observaciones, Trabajador trabajador, Tratamiento tratamiento, Cliente cliente) {
        this.cod_cita = cod_cita;
        this.fecha_hora = fecha_hora;
        this.observaciones = observaciones;
        this.trabajador = trabajador;
        this.tratamiento = tratamiento;
        this.cliente = cliente;
    }
    public Cita(LocalDate fecha_hora, String observaciones, Trabajador trabajador, Tratamiento tratamiento, Cliente cliente) {
        this.fecha_hora = fecha_hora;
        this.observaciones = observaciones;
        this.trabajador = trabajador;
        this.tratamiento = tratamiento;
        this.cliente = cliente;
    }

    public int getCod_cita() {
        return cod_cita;
    }

    public void setCod_cita(int cod_cita) {
        this.cod_cita = cod_cita;
    }

    public LocalDate getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDate fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return getCod_cita() == cita.getCod_cita();
    }
}
