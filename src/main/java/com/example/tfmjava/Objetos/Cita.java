package com.example.tfmjava.Objetos;

import java.time.LocalDate;

public class Cita {
    private int cod_cita;
    private LocalDate fecha_hora;
    private String observaciones;
    private String dniCliente;
    private int cod_trabajador;
    private int cod_tratamiento;

    public Cita(int cod_cita, LocalDate fecha_hora, String observaciones, int cod_trabajador, int cod_tratamiento, String dniCliente) {
        this.cod_cita = cod_cita;
        this.fecha_hora = fecha_hora;
        this.observaciones = observaciones;
        this.cod_trabajador = cod_trabajador;
        this.cod_tratamiento = cod_tratamiento;
        this.dniCliente = dniCliente;
    }
    public Cita(LocalDate fecha_hora, String observaciones, int cod_trabajador, int cod_tratamiento, String dniCliente) {
        this.fecha_hora = fecha_hora;
        this.observaciones = observaciones;
        this.cod_trabajador = cod_trabajador;
        this.cod_tratamiento = cod_tratamiento;
        this.dniCliente = dniCliente;
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

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public int getCod_trabajador() {
        return cod_trabajador;
    }

    public void setCod_trabajador(int cod_trabajador) {
        this.cod_trabajador = cod_trabajador;
    }

    public int getCod_tratamiento() {
        return cod_tratamiento;
    }

    public void setCod_tratamiento(int cod_tratamiento) {
        this.cod_tratamiento = cod_tratamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return getCod_cita() == cita.getCod_cita();
    }
}
