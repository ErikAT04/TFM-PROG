package com.example.tfmjava.Escenas.Trabajadores;

import com.example.tfmjava.Objetos.DAO.TrabajadorDAO;
import com.example.tfmjava.Objetos.Persona;
import com.example.tfmjava.Objetos.Trabajador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TrabajadoresMainScene implements Initializable {

    @FXML
    private Button DelTrabajadorId;

    @FXML
    private Button addTrabajadorId;

    @FXML
    private TableColumn colApellidoTrabajador;

    @FXML
    private TableColumn colCodTrabajador;

    @FXML
    private TableColumn colDNITrabajador;

    @FXML
    private TableColumn colHorario;

    @FXML
    private TableColumn colNombreTrabajador;
    @FXML
    private TableView<Trabajador> tablaTrabajador;

    @FXML
    void onTrabajadorAddClick(ActionEvent event) {

    }

    @FXML
    void onTrabajadorDelClick(ActionEvent event) {

    }

    @FXML
    void onTrabajadorEditClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //String dni, String nombre, String apellidos, int cod_trabajador, String horario
        colCodTrabajador.setCellValueFactory(new PropertyValueFactory<>("cod_trabajador"));
        colDNITrabajador.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNombreTrabajador.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoTrabajador.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        refreshTable();
    }

    public void refreshTable() {
        ObservableList<Trabajador> trabajadorObservableList = FXCollections.observableArrayList(TrabajadorDAO.listarTrabajadores());
        tablaTrabajador.setItems(trabajadorObservableList);
    }
}
