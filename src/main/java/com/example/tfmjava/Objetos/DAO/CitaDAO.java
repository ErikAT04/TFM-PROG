package com.example.tfmjava.Objetos.DAO;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.Cliente;
import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.Trabajador;
import com.example.tfmjava.Objetos.Tratamiento;
import com.example.tfmjava.Objetos.util.DataBaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CitaDAO {
    public static List<String[]> listarCitas(){
        ArrayList<String[]> citas = new ArrayList<>();

        try(Connection con = DataBaseManager.getConnection()){
            String sql = "SELECT * FROM CITA";
            ResultSet consulta = con.prepareStatement(sql).executeQuery();
            /*
            La tabla en SQL está ordenada de la siguiente forma:
            cod_cita INT PRIMARY KEY,
            fecha_hora DATE NOT NULL,
            observaciones VARCHAR(250) DEFAULT 'NO HAY OBSERVACIONES',
            tratamiento INT NOT NULL REFERENCES TRATAMIENTO(cod_trat),
            trabajadorEncargado INT NOT NULL REFERENCES TRABAJADOR(COD_TRABAJADOR),
            clientePedido VARCHAR(9) NOT NULL REFERENCES CLIENTE(dni)
             */
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
        String sql = "";
        return numFilas;
    }
    public static int borrarCita(int cod_cita){
        return 0;
    }
    public static int actualizarCita(Cita cita){
        return 0;
    }
}
