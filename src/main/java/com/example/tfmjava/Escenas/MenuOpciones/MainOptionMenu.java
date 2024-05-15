package com.example.tfmjava.Escenas.MenuOpciones;

import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.*;
import com.example.tfmjava.Objetos.DAO.*;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
    void onPassChangeClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Opciones/ChangeUname.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Cambio de nombre de usuario");
        stage.showAndWait();
    }
    @FXML
    void onUnameChangeClick(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Opciones/ChangeUname.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Cambio de nombre de usuario");
        stage.showAndWait();
    }
    @FXML
    void onPrintDB(ActionEvent event) {

        DirectoryChooser exploradorDeArchivos = new DirectoryChooser();
        File archivoElegido =exploradorDeArchivos.showDialog(currentStage); //Abro el explorador de archivos y le pido que elija dónde guardar el archivo a generar
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(archivoElegido))){
            ArrayList<Cliente> clientes = ClienteDAO.listarClientes();
            ArrayList<Producto> productos = ProductoDAO.listarProductos();
            ArrayList<Trabajador> trabajadors = TrabajadorDAO.listarTrabajadores();
            ArrayList<Tratamiento> tratamientos = TratamientoDAO.listarTratamientos();
            ArrayList<Cita> citas = CitaDAO.listarCitas();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

