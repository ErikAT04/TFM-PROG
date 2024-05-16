package com.example.tfmjava.Escenas.Citas;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.DAO.CitaDAO;
import com.example.tfmjava.Objetos.Persona;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    public TableColumn<String[], String> colCodCita;
    @FXML
    public TableColumn<String[], String> colClientePedido;
    @FXML
    public TableColumn<String[], String> colTrabajadorEncargado;
    @FXML
    public TableColumn<String[], String> colTratamiento;
    @FXML
    public TableColumn<String[], String> colObservaciones;
    @FXML
    public TableColumn<String[], String> colFechaHora;
    @FXML
    public TableView<String[]> tablaCita;
    @FXML
    void onCitasAdd(ActionEvent event) {

    }

    @FXML
    void onCitasDelete(ActionEvent event) {

    }
    @FXML
    public void OnCitasSelect(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCodCita.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[0]));
        colFechaHora.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[1]));
        colObservaciones.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[2]));
        colTratamiento.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[3]));
        colTrabajadorEncargado.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[4]));
        colClientePedido.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[5]));

        ObservableList<String[]> citaObservableList = FXCollections.observableList(CitaDAO.listarCitas());
        tablaCita.setItems(citaObservableList);

        /*
        private int cod_cita;
        private LocalDate fecha_hora;
        private String observaciones;
        private String dniCliente;
        private int cod_trabajador;
        private int cod_tratamiento;
         */
    }

}
