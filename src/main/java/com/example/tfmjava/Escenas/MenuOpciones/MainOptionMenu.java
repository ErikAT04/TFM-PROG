package com.example.tfmjava.Escenas.MenuOpciones;

import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainOptionMenu {
    Stage previousStage;
    Stage currentStage;

    public void addStageValue(Stage previousStage, Stage currentStage) {
        this.previousStage = previousStage;
        this.currentStage = currentStage; //Lo paso aquí directamente, ya que, si ya cargué el objeto previamente, quizá era buena idea reciclarlo
    }

    @FXML
    private Button eraseBtt;

    @FXML
    private Label optLabel;

    @FXML
    void onEraseAccountClick(ActionEvent event) {

    }
    @FXML
    void onLogOffClick(ActionEvent event) {
        this.previousStage.close(); //Cerramos el stage de antes
        this.currentStage.close(); //Cerramos el actual
        DataBaseManager.username=null;
        DataBaseManager.password=null;
        DataBaseManager.dbName=null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cierre de sesión");
        alert.setHeaderText(null);
        alert.setContentText("Sesión cerrada correctamente, ¡vuelva pronto!");
        alert.showAndWait();
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("LogInSignUp/LogInSignUpOpt.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Inserte nombre de aplicacion aqui");
            stage.show();
        }catch (IOException e){
            alert.setTitle("Error");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error");
        }
    }
    @FXML
    void onPassChangeClick(ActionEvent event) {

    }
    @FXML
    void onUnameChangeClick(ActionEvent event) {

    }
    @FXML
    void onPrintDB(ActionEvent event) {

    }
}

