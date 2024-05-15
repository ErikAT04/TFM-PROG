package com.example.tfmjava.Escenas.Citas;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.DAO.CitaDAO;
import com.example.tfmjava.Objetos.Persona;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CitasMainScene implements Initializable {
    @FXML
    public TableColumn colCodCita;
    @FXML
    public TableColumn colClientePedido;
    @FXML
    public TableColumn colTrabajadorEncargado;
    @FXML
    public TableColumn colTratamiento;
    @FXML
    public TableColumn colObservaciones;
    @FXML
    public TableColumn colFechaHora;
    @FXML
    public TableView<Cita> tablaCita;

    ObservableList<Cita> citaObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Cita> citas = CitaDAO.listarCitas();
        citaObservableList = FXCollections.observableList(citas);

        colCodCita.setCellValueFactory(new PropertyValueFactory<>("cod_cita"));
        colClientePedido.setCellValueFactory(new PropertyValueFactory<>("dniCliente"));
        colTratamiento.setCellValueFactory(new PropertyValueFactory<>("cod_tratamiento"));
        colTrabajadorEncargado.setCellValueFactory(new PropertyValueFactory<>("cod_trabajador"));
        colFechaHora.setCellValueFactory(new PropertyValueFactory<>("fecha_hora"));
        colObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        /*
        private int cod_cita;
        private LocalDate fecha_hora;
        private String observaciones;
        private String dniCliente;
        private int cod_trabajador;
        private int cod_tratamiento;
         */
        tablaCita.getItems().addAll(citaObservableList);
    }
}
