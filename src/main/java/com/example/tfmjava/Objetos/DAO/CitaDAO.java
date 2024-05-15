package com.example.tfmjava.Objetos.DAO;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.util.DataBaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CitaDAO {
    public static ArrayList<Cita> listarCitas(){
        ArrayList<Cita> citas = new ArrayList<>();

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
                int cod_cita = consulta.getInt("cod_cita");
                String fecha_hora = consulta.getString("fecha_hora");
                String observaciones = consulta.getString("observaciones");
                int cod_trat = consulta.getInt("tratamiento");
                int cod_trabajador = consulta.getInt("trabajadorEncargado");
                String DNI_cliente = consulta.getString("clientePedido");
                LocalDate fechaLoc = LocalDate.parse(fecha_hora);
                Cita c = new Cita(cod_cita, fechaLoc, observaciones, cod_trat, cod_trabajador, DNI_cliente);
                citas.add(c);
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
