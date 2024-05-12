package com.example.tfmjava.Objetos.LogInSignUp;

import java.util.Objects;

public class Usuario {
    String uname;
    String passwd;
    String db;
    int id;
    public Usuario(String uname, String passwd, String db, int id){
        this.uname = uname;
        this.passwd = passwd;
        this.db = db;
        this.id = id;
    }
    public Usuario(String uname, String passwd){
        this.uname = uname;
        this.passwd = passwd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(uname, usuario.uname);
    }
}
