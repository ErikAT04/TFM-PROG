package com.example.tfmjava.Objetos.util;

public class Resultado {
    private boolean accionCorrecta;
    private String mensaje;
    public Resultado(boolean accionCorrecta, String mensaje){
        this.accionCorrecta = accionCorrecta;
        this.mensaje = mensaje;
    }
    public String getMensaje(){
        return this.mensaje;
    }
    public boolean accionRealizada(){
        return this.accionCorrecta;
    }
}
