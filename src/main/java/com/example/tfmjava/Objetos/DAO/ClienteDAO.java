package com.example.tfmjava.Objetos.DAO;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.Cliente;
import com.example.tfmjava.Objetos.util.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class ClienteDAO {
    public static ArrayList<Cliente> listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();

        return clientes;
    }
    public static int addCliente(Cliente cliente){
        return 0;
    }
    public static int borrarCliente(String dni){
        return 0;
    }
    public static int actualizarCliente(Cliente cliente){
        return 0;
    }
    public static Cliente buscarCliente(String dni){
        Cliente cliente = null;
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "SELECT * FROM CLIENTE WHERE DNI = ?";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, dni);
            ResultSet consulta = sentencia.executeQuery();
            /*
            dni VARCHAR(9) PRIMARY KEY,
            nombre VARCHAR(20) NOT NULL,
            apellidos VARCHAR(50) NOT NULL,
            telf INT NOT NULL,
            edad INT NOT NULL,
            fnac DATE NOT NULL,
            especificaciones VARCHAR(250) DEFAULT 'SIN ESPECIFICAR'
             */
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
