package com.example.tfmjava.Escenas;

import com.example.tfmjava.Escenas.MenuOpciones.MainOptionMenu;
import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainScene implements Initializable {

    @FXML
    private Button citasBtt;

    @FXML
    private Button clientesBtt;

    @FXML
    private Button productosBtt;

    @FXML
    private Button trabajadoresBtt;

    @FXML
    private Button tratamientosBtt;

    @FXML
    private Label welcomeLabel;

    @FXML
    void onCitasAction(ActionEvent event) {
        openThisScene("Citas/CitasMain.fxml", this.citasBtt);
    }

    @FXML
    void onClientesAction(ActionEvent event) {
        openThisScene("Clientes/ClientesMain.fxml", this.clientesBtt);
    }

    @FXML
    void onOthersAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Opciones/OptMenu.fml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            Stage thisStage = (Stage) this.welcomeLabel.getScene().getWindow(); //Lo utilizaré si cierro sesión en las opciones o borro la cuenta.
            MainOptionMenu controller = loader.getController();
            controller.addStageValue(thisStage);
            stage.showAndWait();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onProductosAction(ActionEvent event) {
        openThisScene("Productos/ProductosMain.fxml", this.productosBtt);
    }

    @FXML
    void onTrabajadoresAction(ActionEvent event) {
        openThisScene("Trabajadores/TrabajadoresMain.fxml", this.trabajadoresBtt);
    }

    @FXML
    void onTratamientosAction(ActionEvent event) {
        openThisScene("Tratamientos/TratamientosMain.fxml", this.tratamientosBtt);
    }

    void openThisScene(String fxml, Button btt)  {
        try {
            FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource(fxml));
            Scene scene = null;
            scene = new Scene(loader.load());
            Stage stage = (Stage) btt.getScene().getWindow();
            stage.setScene(scene);
            if (!stage.isShowing()){
                stage.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setText(welcomeLabel.getText() + "\n" + DataBaseManager.username);
    }
}

