package com.example.tfmjava.Objetos.DAO;

import com.example.tfmjava.Objetos.Cliente;
import com.example.tfmjava.Objetos.Producto;
import com.example.tfmjava.Objetos.Trabajador;
import com.example.tfmjava.Objetos.Tratamiento;
import com.example.tfmjava.Objetos.util.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {
    public static int addProducto(Producto producto){
        int filasInsertadas = 0;
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "INSERT INTO PRODUCTO(nombre, marca, stock) VALUES(?, ?, ?)";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, producto.getNombre());
            sentencia.setString(2, producto.getMarca());
            sentencia.setInt(2, producto.getStock());

            filasInsertadas = sentencia.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


        return filasInsertadas;
    }
    public static ArrayList<Producto> listarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "SELECT * FROM PRODUCTO";
            ResultSet consulta = con.prepareStatement(sql).executeQuery();

            while (consulta.next()){
                int cod_Prod = consulta.getInt("cod_prod");
                int stock = consulta.getInt("stock");
                String nombre = consulta.getString("nombre");
                String marca = consulta.getString("marca");

                Producto producto = new Producto(cod_Prod,stock,nombre,marca);
                productos.add(producto);
            }

        }catch (SQLException e){
            System.out.println(e.toString());
        }

        return productos;
    }
    public static int ActualizarProducto(Producto producto){
        int numFilas=0;
        String sql = "UPDATE PRODUCTO SET stock= ?, nombre = ?, marca = ? WHERE cod_prod = ?) ";
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, producto.getStock());
            sentencia.setString(2, producto.getNombre());
            sentencia.setString(3, producto.getMarca());
            sentencia.setInt(4, producto.getCod_prod());
            numFilas = sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error " + e);
        }
        return numFilas;
    }
    public static int borrarProducto(int cod_prod){
        String sql = "DELETE FROM PRODUCTO WHERE cod_prod = ?";
        int numFilas = 0;
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, cod_prod);

            numFilas=sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error " + e);
        }
        return numFilas;
    }
    public static int sumarStock(int codProducto){
        String sql = "UPDATE PRODUCTO SET STOCK = STOCK + 1 WHERE cod_prod = ?";
        int numFilas = 0;
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, codProducto);
            numFilas = sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error " + e);
        }
        return  numFilas;
    }

    public static int restarStock(int codProducto){
        String sql = "UPDATE PRODUCTO SET STOCK = STOCK - 1 WHERE cod_prod = ?";
        int numFilas = 0;
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, codProducto);
            numFilas = sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error " + e);
        }
        return  numFilas;
    }

}
