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
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "select * from tratamiento";
            PreparedStatement sentencia = con.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()){
                int cod_trat = resultado.getInt("cod_trat");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                double precio = resultado.getDouble("precio");
                double duracion = resultado.getDouble("duracion_media_horas");
                Tratamiento tratamiento = new Tratamiento(cod_trat,nombre,descripcion,precio,duracion);
                ArrayList<Producto> productos =listarProductosDeTratamiento(tratamiento);
                tratamiento.productos_utilizados = productos;
                tratamientos.add(tratamiento);
            }
        }catch (SQLException e){}
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
        ArrayList<Producto> productos = new ArrayList<>();
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "select * from producto where cod_prod = ANY (SELECT cod_producto FROM producto_tratamiento WHERE cod_tratamiento = ?);";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, tratamiento.getCod_trat());
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()){
                int cod_prod = resultado.getInt("cod_prod");
                String nombre = resultado.getString("nombre");
                String marca = resultado.getString("marca");
                int stock = resultado.getInt("stock");
                Producto prod = new Producto(cod_prod, stock, nombre, marca);
                productos.add(prod);
            }
        }catch (SQLException e){}
        return productos;
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
