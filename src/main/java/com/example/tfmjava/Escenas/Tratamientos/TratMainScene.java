package com.example.tfmjava.Escenas.Tratamientos;

import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.DAO.TratamientoDAO;
import com.example.tfmjava.Objetos.Producto;
import com.example.tfmjava.Objetos.Tratamiento;
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
import java.util.ArrayList;
import java.util.Objects;
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
    void onTratamientoAddClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("ToqHer - Creación de Tratamientos");
        Image img = new Image(Objects.requireNonNull(InitApplication.class.getResource("otherSRC/img/favicon.png")).toString());
        stage.getIcons().add(img);
        FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Tratamientos/TratamientosSubMain.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.showAndWait();
        refreshTable();
    }

    @FXML
    void onTratamientoDelClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Tratamientos");
        alert.setHeaderText(null);
        Tratamiento tratamiento = tablaTratamientos.getSelectionModel().getSelectedItem();
        if (tratamiento==null){
            alert.setContentText("No se ha seleccionado ningún tratamiento");
        } else {
            int numFilas = TratamientoDAO.reiniciarProductosDeTratamientos(tratamiento.getCod_trat());
            if (numFilas>=0){
                numFilas = TratamientoDAO.borrarTratamiento(tratamiento.getCod_trat());
                if (numFilas==1){
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Tratamiento eliminado correctamente");
                } else {
                    alert.setContentText("Error al borrar tratamientos");
                }
            } else {
                alert.setContentText("Error al borrar los productos del tratamiento");
            }
        }
        alert.showAndWait();
        refreshTable();
    }

    @FXML
    void onTratamientoEditClick(ActionEvent event) throws IOException {
        Tratamiento tratamiento = tablaTratamientos.getSelectionModel().getSelectedItem();
        if (tratamiento==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Tratamientos");
            alert.setContentText("No se ha seleccionado ningún tratamiento");
            alert.showAndWait();
        } else {
            Stage stage = new Stage();
            stage.setTitle("ToqHer - Edición de Tratamientos");
            Image img = new Image(Objects.requireNonNull(InitApplication.class.getResource("otherSRC/img/favicon.png")).toString());
            stage.getIcons().add(img);
            FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Tratamientos/TratamientosSubMain.fxml"));
            Scene scene = new Scene(loader.load());
            TratSubScene controller = loader.getController();
            controller.toEdit(tratamiento);
            stage.setScene(scene);
            stage.showAndWait();
        }
        refreshTable();
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
        tablaTratamientos.refresh();
    }
}
