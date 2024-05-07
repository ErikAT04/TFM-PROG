package com.example.tfmjava.Objetos;

import java.time.LocalDate;

public class Cita {
    private int cod_cita;
    private LocalDate fecha_hora;
    private String observaciones;
    private Tratamiento tratamiento;
    private Trabajador trabajadorEncargado;
    private Cliente clientePedido;

    public Cita(int cod_cita, LocalDate fecha_hora, String observaciones) {
        this.cod_cita = cod_cita;
        this.fecha_hora = fecha_hora;
        this.observaciones = observaciones;
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

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Trabajador getTrabajadorEncargado() {
        return trabajadorEncargado;
    }

    public void setTrabajadorEncargado(Trabajador trabajadorEncargado) {
        this.trabajadorEncargado = trabajadorEncargado;
    }

    public Cliente getClientePedido() {
        return clientePedido;
    }

    public void setClientePedido(Cliente clientePedido) {
        this.clientePedido = clientePedido;
    }
}
