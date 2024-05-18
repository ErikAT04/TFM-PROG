package com.example.tfmjava.Objetos;

import java.sql.Time;
import java.time.LocalDate;

public class Cita {
    private int cod_cita;
    private LocalDate fecha;
    private Time hora;
    private String observaciones;
    private Cliente cliente;
    private Trabajador trabajador;
    private Tratamiento tratamiento;

    public Cita(int cod_cita, LocalDate fecha, Time hora, String observaciones, Trabajador trabajador, Tratamiento tratamiento, Cliente cliente) {
        this.cod_cita = cod_cita;
        this.fecha = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
        this.trabajador = trabajador;
        this.tratamiento = tratamiento;
        this.cliente = cliente;
    }
    public Cita(LocalDate fecha, Time hora, String observaciones, Trabajador trabajador, Tratamiento tratamiento, Cliente cliente) {
        this.fecha = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
        this.trabajador = trabajador;
        this.tratamiento = tratamiento;
        this.cliente = cliente;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getCod_cita() {
        return cod_cita;
    }

    public void setCod_cita(int cod_cita) {
        this.cod_cita = cod_cita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha_hora) {
        this.fecha = fecha_hora;
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
