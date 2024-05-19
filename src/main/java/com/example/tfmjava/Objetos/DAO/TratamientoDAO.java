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
    /*
    cod_trat INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(40) NOT NULL,
    descripcion VARCHAR(250) NOT NULL,
    precio DOUBLE NOT NULL,
    duracion_media_horas DOUBLE NOT NULL
    */
    public static int addTratamiento(Tratamiento tratamiento){
        int cod_trat = 0;
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "INSERT INTO TRATAMIENTO(COD_TRAT, nombre, descripcion, precio, duracion_media_horas) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, tratamiento.getCod_trat());
            sentencia.setString(2, tratamiento.getNombre());
            sentencia.setString(3, tratamiento.getDescripcion());
            sentencia.setDouble(4, tratamiento.getPrecio());
            sentencia.setDouble(5, tratamiento.getDuracion_media_horas());

            sentencia.executeUpdate();

            ResultSet res = con.createStatement().executeQuery("SELECT MAX(COD_TRAT) FROM TRATAMIENTO");
            if (res.next()){
                cod_trat = res.getInt(1);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return cod_trat;
    }
    public static int borrarTratamiento(int COD_TRAT){
        String sql = "DELETE FROM TRATAMIENTO WHERE COD_TRAT = ?";
        String sqlDeleteCita = "DELETE FROM CITA WHERE TRATAMIENTO = ?";
        String sqlDeleteRel = "DELETE FROM PRODUCTO_TRATAMIENTO WHERE COD_TRATAMIENTO = ?";
        int numFilas = 0;
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sqlDeleteRel);
            sentencia.setInt(1, COD_TRAT);
            sentencia.executeUpdate();

            sentencia = con.prepareStatement(sqlDeleteCita);
            sentencia.setInt(1, COD_TRAT);
            sentencia.executeUpdate();

            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, COD_TRAT);
            numFilas=sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error " + e);
        }
        return numFilas;
    }
    /*
    cod_trat INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(40) NOT NULL,
    descripcion VARCHAR(250) NOT NULL,
    precio DOUBLE NOT NULL,
    duracion_media_horas DOUBLE NOT NULL
    */
    public static int actualizarTratamiento(Tratamiento tratamiento){
        int numFilas=0;
        String sql = "UPDATE TRATAMIENTO SET nombre = ?, descripcion = ?, precio = ?, duracion_media_horas = ? WHERE COD_TRAT = ?";
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, tratamiento.getNombre());
            sentencia.setString(2, tratamiento.getDescripcion());
            sentencia.setDouble(3, tratamiento.getPrecio());
            sentencia.setDouble(4, tratamiento.getDuracion_media_horas());
            sentencia.setInt(5, tratamiento.getCod_trat());
            numFilas = sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error " + e);
            numFilas=-1;
        }
        return numFilas;
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
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
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
        }catch (SQLException e) {
        }

        return tratamiento;
    }

    public static int reiniciarProductosDeTratamientos(int cod_trat){
        String sql = "DELETE FROM PRODUCTO_TRATAMIENTO WHERE COD_TRATAMIENTO = ?";
        int numFilas = 0;
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, cod_trat);
            numFilas = sentencia.executeUpdate();
        }catch (SQLException e){
        }
        return numFilas;
    }

    public static int addAllFilas(ArrayList<Producto> productos, int cod_trat) {
        int numFilas = 0;
        String sql = "INSERT INTO PRODUCTO_TRATAMIENTO(COD_PRODUCTO, COD_TRATAMIENTO) VALUES(?, ?)";
        try (Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            for (Producto p : productos){
                sentencia.setInt(1, p.getCod_prod());
                sentencia.setInt(2, cod_trat);
                sentencia.executeUpdate();
                numFilas++;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            numFilas=-1;
        }
        return numFilas;
    }
}
