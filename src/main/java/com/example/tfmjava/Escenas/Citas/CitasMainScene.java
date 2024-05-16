package com.example.tfmjava.Escenas.Citas;

import com.example.tfmjava.InitApplication;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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
    void onCitasAddClick(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Citas/CitaSubMain.fxml"));
        Scene scene = new Scene(loader.load());
        CitasSubScene controller = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        refreshTable();
    }

    @FXML
    void onCitasDeleteClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de borrado");
        alert.setHeaderText(null);
        if (tablaCita.getSelectionModel().getSelectedItem()==null){
            alert.setContentText("No se ha seleccionado ningún hueco de la tabla");
        } else {
            String[] seleccion = tablaCita.getSelectionModel().getSelectedItem();
            int numFilas = CitaDAO.borrarCita(Integer.parseInt(seleccion[0])); //No tiene por qué controlarse este parseo, porque siempre va a venir de un valor entero
            if(numFilas==1){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Eliminación");
                alert.setContentText("Elemento eliminado correctamente");
            } else {
                alert.setContentText("Ha ocurrido un problema al tratar de borrar la cita");
            }
        }
        alert.showAndWait();
        refreshTable();
    }
    @FXML
    public void OnCitasEditClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de actualización");
        alert.setHeaderText(null);
        if (tablaCita.getSelectionModel().getSelectedItem()==null){
            alert.setContentText("No se ha seleccionado ningún hueco de la tabla");
            alert.showAndWait();
        } else {
            int cod_cita = Integer.parseInt(tablaCita.getSelectionModel().getSelectedItem()[0]);
            Cita cita = CitaDAO.buscarCita(cod_cita);
            FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Citas/CitaSubMain.fxml"));
            Scene scene = new Scene(loader.load());
            CitasSubScene controller = loader.getController();
            controller.toEdit(cita);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            refreshTable();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCodCita.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[0]));
        colFechaHora.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[1]));
        colObservaciones.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[2]));
        colTratamiento.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[3]));
        colTrabajadorEncargado.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[4]));
        colClientePedido.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()[5]));

        refreshTable();

        /*
        private int cod_cita;
        private LocalDate fecha_hora;
        private String observaciones;
        private String dniCliente;
        private int cod_trabajador;
        private int cod_tratamiento;
         */
    }
    public void refreshTable(){
        ObservableList<String[]> citaObservableList = FXCollections.observableList(CitaDAO.listarCitas());
        tablaCita.setItems(citaObservableList);
    }
}
