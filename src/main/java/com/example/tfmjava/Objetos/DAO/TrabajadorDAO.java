package com.example.tfmjava.Objetos.DAO;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.Producto;
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
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "SELECT * FROM TRABAJADOR";
            ResultSet consulta = con.prepareStatement(sql).executeQuery();

            while (consulta.next()){
                int COD_TRABAJADOR = consulta.getInt("cod_trabajador");
                String dni = consulta.getString("dni");
                String nombre = consulta.getString("nombre");
                String apellidos = consulta.getString("apellidos");
                String horario = consulta.getString("horario");

                Trabajador trabajador = new Trabajador(dni,nombre,apellidos,COD_TRABAJADOR,horario);
                trabajadores.add(trabajador);
            }

        }catch (SQLException e){
            System.out.println(e.toString());
        }
        return trabajadores;
    }
    public static int addTrabajador(Trabajador trabajador){
        int filasInsertadas = 0;
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "INSERT INTO TRABAJADOR(COD_TRABAJADOR, dni, nombre, apellidos, horario) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, trabajador.getCod_trabajador());
            sentencia.setString(2, trabajador.getDni());
            sentencia.setString(3, trabajador.getNombre());
            sentencia.setString(4, trabajador.getApellidos());
            sentencia.setString(5, trabajador.getHorario());

            filasInsertadas = sentencia.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return filasInsertadas;
    }
    public static int borrarTrabajador(int COD_TRABAJADOR){
        String sql = "DELETE FROM TRABAJADOR WHERE COD_TRABAJADOR = ?";
        String sqlDeleteCita = "DELETE FROM CITA WHERE trabajadorEncargado = ?";
        int numFilas = 0;
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sqlDeleteCita);
            sentencia.setInt(1, COD_TRABAJADOR);
            sentencia.executeUpdate();

            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, COD_TRABAJADOR);

            numFilas=sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error " + e);
        }
        return numFilas;
    }
    public static int actualizarTrabajador(Trabajador trabajador){
        int numFilas=0;
        String sql = "UPDATE TRABAJADOR SET dni = ?, nombre = ?, apellidos = ?, horario = ? WHERE COD_TRABAJADOR = ?";
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, trabajador.getDni());
            sentencia.setString(2, trabajador.getNombre());
            sentencia.setString(3, trabajador.getApellidos());
            sentencia.setString(4, trabajador.getHorario());
            sentencia.setInt(5, trabajador.getCod_trabajador());
            numFilas = sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error " + e);
        }
        return numFilas;
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
        }catch (SQLException e){

        }
        return trabajador;
    }
}
