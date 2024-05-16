package com.example.tfmjava.Objetos.DAO;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.Producto;
import com.example.tfmjava.Objetos.Trabajador;
import com.example.tfmjava.Objetos.Tratamiento;
import com.example.tfmjava.Objetos.util.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TratamientoDAO {
    public static ArrayList<Tratamiento> listarTratamientos(){
        ArrayList<Tratamiento> tratamientos= new ArrayList<>();
        return tratamientos;
    }
    public static int addTratamiento(Tratamiento tratamiento){
        return 0;
    }
    public static int borrarTratamiento(int cod_tratamiento){
        return 0;
    }
    public static int actualizarTratamiento(int cod_cita){
        return 0;
    }
    public static ArrayList<Producto> listarProductosDeTratamiento (Tratamiento tratamiento){
        return null;
    }

    public static Tratamiento buscarTratamiento(int codTrat) {
        Tratamiento tratamiento=null;
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "SELECT * FROM TRATAMIENTO WHERE COD_TRAT = ?";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, codTrat);
            ResultSet consulta = sentencia.executeQuery();
            if (consulta.next()){
                String nombre = consulta.getString("nombre");
                String descripcion = consulta.getString("descripcion");
                double precio = consulta.getDouble("precio");
                double duracion_media = consulta.getDouble("duracion_media_horas");
                tratamiento = new Tratamiento(codTrat, nombre, descripcion, precio, duracion_media);
            }
            /*
                cod_trat INT PRIMARY KEY AUTO_INCREMENT,
                nombre VARCHAR(40) NOT NULL,
                descripcion VARCHAR(250) NOT NULL,
                precio DOUBLE NOT NULL,
                duracion_media_horas DOUBLE NOT NULL
             */
        }catch (SQLException e){

        }

        return tratamiento;
    }
}
