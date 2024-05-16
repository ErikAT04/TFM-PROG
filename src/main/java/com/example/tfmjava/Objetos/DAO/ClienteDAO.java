package com.example.tfmjava.Objetos.DAO;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.Cliente;
import com.example.tfmjava.Objetos.util.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class ClienteDAO {
    public static ArrayList<Cliente> listarClientes(){

        ArrayList<Cliente> clientes = new ArrayList<>();

        try(Connection con= DataBaseManager.getConnection()){
            String sql = "SELECT * FROM Cliente";
            ResultSet consulta = con.prepareStatement(sql).executeQuery();

            while(consulta.next()){
                String DNI = consulta.getString("dni");
                String nombre = consulta.getString("nombre");
                String apellidos = consulta.getString("apellidos");
                int tlf = consulta.getInt("telf");
                int edad = consulta.getInt("edad");
                String fnac = consulta.getString("fnac");
                String especificaciones = consulta.getString("especificaciones");
                LocalDate localDate = LocalDate.parse(fnac, DateTimeFormatter.ofPattern("yyyy-MM-dd"));


                Cliente cliente = new Cliente(DNI,nombre,apellidos,tlf,edad,localDate,especificaciones);
                clientes.add(cliente);
            }

        } catch(SQLException e){
            System.out.println("Error " + e);
        }
        return clientes;
    }
    public static int addCliente(Cliente cliente){
        int numFilas=0;
        String sql = "INSERT INTO (dni, nombre, apellidos, telf, edad, fnac, especificaciones) VALUES (STR_TO_DATE(?,'%d-%m-%Y'), ?, ?, ?, ?)";
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, cliente.getDni());
            sentencia.setString(2, cliente.getNombre());
            sentencia.setString(3, cliente.getApellidos());
            sentencia.setInt(4, cliente.getTelf());
            sentencia.setInt(5, cliente.getEdad());
            sentencia.setString(6, String.valueOf(cliente.getFnac()));
            sentencia.setInt(7, cliente.getEdad());

            numFilas = sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error de inserción");
        }
        return numFilas;
    }
    public static int borrarCliente(String dni){
        String sql = "DELETE FROM CLIENTE WHERE DNI = ?";
        int numFilas = 0;
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, dni);

            numFilas=sentencia.executeUpdate();
        }catch (SQLException e){}
        return numFilas;
    }

    public static int actualizarCliente(Cliente cliente){
        int numFilas = 0;

        String sql = "UPDATE CITA SET , nombre = ?, apellidos = ?, telf = ?, edad = ?, fnac = STR_TO_DATE(?,'%d-%m-%Y') WHERE dni = ?";
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, cliente.getNombre());
            sentencia.setString(2, cliente.getApellidos());
            sentencia.setInt(3, cliente.getTelf());
            sentencia.setInt(4, cliente.getEdad());
            sentencia.setString(5, String.valueOf(cliente.getFnac()));
            sentencia.setString(6, cliente.getDni());
            numFilas= sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error " + e);
        }
        return numFilas;
    }
    public static Cliente buscarCliente(String dni){
        Cliente cliente = null;
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "SELECT * FROM CLIENTE WHERE DNI = ?";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, dni);
            ResultSet consulta = sentencia.executeQuery();

            if (consulta.next()){
                String nombre = consulta.getString("nombre");
                String apellidos = consulta.getString("apellidos");
                int telf = consulta.getInt("telf");
                int edad = consulta.getInt("edad");
                String fnacS = consulta.getString("fnac");
                String espec = consulta.getString("especificaciones");
                LocalDate fnac = LocalDate.parse(fnacS);
                cliente = new Cliente(dni, nombre, apellidos, telf, edad, fnac, espec);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return cliente;
    }
}
