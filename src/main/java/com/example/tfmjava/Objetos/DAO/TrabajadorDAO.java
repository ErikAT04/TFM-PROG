package com.example.tfmjava.Objetos.DAO;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.Trabajador;
import com.example.tfmjava.Objetos.util.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrabajadorDAO {
    public static ArrayList<Trabajador> listarTrabajadores(){
        ArrayList<Trabajador> trabajadores = new ArrayList<>();

        return trabajadores;
    }
    public static int addTrabajador(Trabajador trabajador){
        return 0;
    }
    public static int borrarTrabajador(String dni){
        return 0;
    }
    public static int actualizarTrabajador(Trabajador trabajador){
        return 0;
    }

    public static Trabajador buscarTrabajador(int codTrabajador) {
        Trabajador trabajador = null;
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "SELECT * FROM TRABAJADOR WHERE COD_TRABAJADOR = ?";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, codTrabajador);

            ResultSet consulta = sentencia.executeQuery();

            if (consulta.next()){
                String dni = consulta.getString("dni");
                String nombre = consulta.getString("nombre");
                String apellidos = consulta.getString("apellidos");
                String horario = consulta.getString("horario");
                trabajador = new Trabajador(dni, nombre, apellidos,codTrabajador, horario);
            }


                /*
                    COD_TRABAJADOR INT PRIMARY KEY AUTO_INCREMENT,
                    dni VARCHAR(9) UNIQUE,
                    nombre VARCHAR(20) NOT NULL,
                    apellidos VARCHAR(50) NOT NULL,
                    horario VARCHAR(60) NOT NULL
                 */
        }catch (SQLException e){

        }
        return trabajador;
    }
}
