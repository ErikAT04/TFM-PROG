package com.example.tfmjava.Objetos.DAO;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.Cliente;
import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.Trabajador;
import com.example.tfmjava.Objetos.Tratamiento;
import com.example.tfmjava.Objetos.util.DataBaseManager;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CitaDAO {
    public static List<String[]> listarCitas(){
        ArrayList<String[]> citas = new ArrayList<>();

        try(Connection con = DataBaseManager.getConnection()){
            String sql = "SELECT * FROM CITA";
            ResultSet consulta = con.prepareStatement(sql).executeQuery();

            while (consulta.next()){
                String cod_cita = consulta.getString("cod_cita");
                String fecha_hora = consulta.getString("fecha_hora");
                String observaciones = consulta.getString("observaciones");
                int cod_trat = consulta.getInt("tratamiento");
                int cod_trabajador = consulta.getInt("trabajadorEncargado");
                String DNI_cliente = consulta.getString("clientePedido");

                Cliente cliente = ClienteDAO.buscarCliente(DNI_cliente);
                String cli = cliente.getNombre() + " " + cliente.getApellidos() + "(" + cliente.getDni() + ")";

                Trabajador trabajador = TrabajadorDAO.buscarTrabajador(cod_trabajador);
                String trab = trabajador.getNombre() + " " + trabajador.getApellidos() + "(" + trabajador.getDni() + ")";

                Tratamiento tratamiento = TratamientoDAO.buscarTratamiento(cod_trat);
                String trat = tratamiento.getNombre() + "(" + tratamiento.getCod_trat() + ")";

                String[] cita = {cod_cita, fecha_hora, observaciones, trat, trab, cli};
                citas.add(cita);

            }
        }catch (SQLException e){
            System.out.println(e.toString());
        }

        return citas;
    }
    public static int addCita(Cita cita){
        int numFilas=0;
        String sql = "INSERT INTO CITA(fecha_hora, observaciones, tratamiento, trabajadorEncargado, clientePedido) VALUES (STR_TO_DATE(?,'%Y-%m-%d'), ?, ?, ?, ?)";
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, String.valueOf(cita.getFecha_hora()));
            sentencia.setString(2, cita.getObservaciones());
            sentencia.setInt(3, cita.getTratamiento().getCod_trat());
            sentencia.setInt(4, cita.getTrabajador().getCod_trabajador());
            sentencia.setString(5, cita.getCliente().getDni());

            numFilas = sentencia.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error de inserción");
        }
        return numFilas;

    }
    public static int borrarCita(int cod_cita){
        String sql = "DELETE FROM CITA WHERE COD_CITA = ?";
        int numFilas = 0;
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, cod_cita);

            numFilas=sentencia.executeUpdate();
        }catch (SQLException e){}
        return numFilas;
    }
    public static int actualizarCita(Cita cita){
        int numFilas = 0;
                    /*
            La tabla en SQL está ordenada de la siguiente forma:
            cod_cita INT PRIMARY KEY,
            fecha_hora DATE NOT NULL,
            observaciones VARCHAR(250) DEFAULT 'NO HAY OBSERVACIONES',
            tratamiento INT NOT NULL REFERENCES TRATAMIENTO(cod_trat),
            trabajadorEncargado INT NOT NULL REFERENCES TRABAJADOR(COD_TRABAJADOR),
            clientePedido VARCHAR(9) NOT NULL REFERENCES CLIENTE(dni)
             */
        String sql = "UPDATE CITA SET fecha_hora = STR_TO_DATE(?,'%Y-%m-%d'), observaciones = ?, tratamiento = ?, trabajadorEncargado = ?, clientePedido = ? WHERE cod_cita = ?";
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, String.valueOf(cita.getFecha_hora()));
            sentencia.setString(2, cita.getObservaciones());
            sentencia.setInt(3, cita.getTratamiento().getCod_trat());
            sentencia.setInt(4, cita.getTrabajador().getCod_trabajador());
            sentencia.setString(5, cita.getCliente().getDni());
            sentencia.setInt(6, cita.getCod_cita());
            numFilas= sentencia.executeUpdate();
        }catch (SQLException e){}
        return numFilas;
    }
    public static Cita buscarCita(int cod_cita){
        Cita cita = null;
        String sql = "SELECT * FROM CITA WHERE COD_CITA = ?";
        try(Connection con = DataBaseManager.getConnection()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, cod_cita);
            ResultSet consulta = sentencia.executeQuery();

            while (consulta.next()) {
                LocalDate fecha_hora = consulta.getDate("fecha_hora").toLocalDate();
                String observaciones = consulta.getString("observaciones");
                int cod_trat = consulta.getInt("tratamiento");
                int cod_trabajador = consulta.getInt("trabajadorEncargado");
                String DNI_cliente = consulta.getString("clientePedido");
                Cliente cliente = ClienteDAO.buscarCliente(DNI_cliente);
                Trabajador trabajador = TrabajadorDAO.buscarTrabajador(cod_trabajador);
                Tratamiento tratamiento = TratamientoDAO.buscarTratamiento(cod_trat);


                cita= new Cita(cod_cita, fecha_hora, observaciones, trabajador, tratamiento, cliente);
            }
        }catch (SQLException e){}
        return cita;
    }

    public static boolean otherFechaHora(Cita cita) {
        boolean isThereAnyOther = true;
        try(Connection con = DataBaseManager.getConnection()){
            String sql = "SELECT * FROM CITA WHERE COD_CITA != ? AND FECHA_HORA = STR_TO_DATE(?,'%Y-%m-%d') AND trabajadorEncargado = ?";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, cita.getCod_cita());
            sentencia.setString(2, String.valueOf(cita.getFecha_hora()));
            sentencia.setInt(3, cita.getTrabajador().getCod_trabajador());
            ResultSet consulta = sentencia.executeQuery();
            isThereAnyOther = consulta.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return isThereAnyOther;
    }
}
