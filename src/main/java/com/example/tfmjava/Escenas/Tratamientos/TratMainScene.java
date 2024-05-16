package com.example.tfmjava.Escenas.Tratamientos;

import com.example.tfmjava.Objetos.DAO.TratamientoDAO;
import com.example.tfmjava.Objetos.Tratamiento;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TratMainScene implements Initializable {

    @FXML
    private Button EditTratamiento;

    @FXML
    private Button addTratamientoId;

    @FXML
    private TableColumn colCodTratamiento;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private TableColumn colHorasMedias;

    @FXML
    private TableColumn colPrecio;

    @FXML
    private Button delTratamientoId;

    @FXML
    private TableColumn colNombreTratamiento;

    @FXML
    private TableView<Tratamiento> tablaTratamientos;

    @FXML
    void onTratamientoAddClick(ActionEvent event) {

    }

    @FXML
    void onTratamientoDelClick(ActionEvent event) {

    }

    @FXML
    void onTratamientoEditClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCodTratamiento.setCellValueFactory(new PropertyValueFactory<>("cod_trat"));
        colNombreTratamiento.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colHorasMedias.setCellValueFactory(new PropertyValueFactory<>("duracion_media_horas"));
        refreshTable();
    }
    public void refreshTable() {
        ObservableList<Tratamiento> tratamientos = FXCollections.observableList(TratamientoDAO.listarTratamientos());
        tablaTratamientos.setItems(tratamientos);
    }
}
