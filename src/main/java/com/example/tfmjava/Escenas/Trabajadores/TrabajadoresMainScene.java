package com.example.tfmjava.Escenas.Trabajadores;

import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.DAO.TrabajadorDAO;
import com.example.tfmjava.Objetos.Persona;
import com.example.tfmjava.Objetos.Trabajador;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
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
    void onTrabajadorAddClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Trabajadores/TrabajadoresSubMain.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("ToqHer - Creación de trabajadores");
        Image img = new Image(Objects.requireNonNull(InitApplication.class.getResource("otherSRC/img/favicon.png")).toString());
        stage.getIcons().add(img);
        stage.setScene(scene);
        stage.showAndWait();
        refreshTable();
    }

    @FXML
    void onTrabajadorDelClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Borrar trabajador");
        alert.setHeaderText(null);
        Trabajador trabajador = tablaTrabajador.getSelectionModel().getSelectedItem();
        if (trabajador == null) {
            alert.setContentText("Selecciona algún trabajador para continuar");
        } else {
            int numfilas = TrabajadorDAO.borrarTrabajador(trabajador.getCod_trabajador());
            if (numfilas==1){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Trabajador borrado correctamente");
            }
        }
        alert.showAndWait();
        refreshTable();
    }

    @FXML
    void onTrabajadorEditClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Editar trabajador");
        alert.setHeaderText(null);
        Trabajador trabajador = tablaTrabajador.getSelectionModel().getSelectedItem();
        if (trabajador == null) {
            alert.setContentText("Seleccione algún trabajador");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Trabajadores/TrabajadoresSubMain.fxml"));
            Scene scene = new Scene(loader.load());
            TrabajadoresSubScene controller = loader.getController();
            controller.toEdit(trabajador);
            Stage stage = new Stage();
            stage.setTitle("ToqHer - Edición de Trabajadores");
            Image img = new Image(Objects.requireNonNull(InitApplication.class.getResource("otherSRC/img/favicon.png")).toString());
            stage.getIcons().add(img);
            stage.setScene(scene);
            stage.showAndWait();

        }
        refreshTable();
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
        tablaTrabajador.refresh();
    }
}
