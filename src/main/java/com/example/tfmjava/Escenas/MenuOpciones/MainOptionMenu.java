package com.example.tfmjava.Escenas.MenuOpciones;

import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.*;
import com.example.tfmjava.Objetos.DAO.*;
import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.LogInSignUp.UsuarioDAO;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Estás seguro de que quieres borrar tu cuenta?\nEs una acción IRREVERSIBLE");
        alert.setHeaderText("Confirmación de eliminación");
        alert.setTitle("Eliminar cuenta");
        boolean bool = false;
        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK){
            bool = UsuarioDAO.eraseUser(UsuarioDAO.userForLogin(DataBaseManager.username));
        }
        Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Eliminar cuenta");
        if (bool) {
            alert.setHeaderText("Cuenta eliminada correctamente");
            alert.setContentText("¡Hasta la vista!");
            alert.showAndWait();
            returnToBeginning();
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Ha habido un problema");
            alert.showAndWait();
        }
    }
    @FXML
    void onLogOffClick(ActionEvent event) {
        DataBaseManager.username=null;
        DataBaseManager.password=null;
        DataBaseManager.dbName=null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cierre de sesión");
        alert.setHeaderText(null);
        alert.setContentText("Sesión cerrada correctamente, ¡vuelva pronto!");
        alert.showAndWait();
        returnToBeginning();
    }

    private void returnToBeginning() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        this.previousStage.close(); //Cerramos el stage de antes
        this.currentStage.close(); //Cerramos el actual
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("LogInSignUp/LogInSignUpOpt.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Inserte nombre de aplicacion aqui");
            stage.show();
        }catch (IOException e){
            alert.setTitle("Error");
            alert.setContentText("Hubo un error");
            alert.showAndWait();
        }
    }

    @FXML
    void onPassChangeClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Image img = new Image(InitApplication.class.getResource("otherSRC/img/favicon.png").toString());
        stage.getIcons().add(img);
        FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Opciones/ChangeUname.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Cambio de nombre de usuario");
        stage.showAndWait();
    }
    @FXML
    void onUnameChangeClick(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Image img = new Image(InitApplication.class.getResource("otherSRC/img/favicon.png").toString());
        stage.getIcons().add(img);
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
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(archivoElegido + "/DataBaseAt(" + LocalDate.now()+ " " + LocalDateTime.now().getHour()+"."+LocalDateTime.now().getMinute()+"."+ LocalDateTime.now().getSecond()+").txt"))){
            ArrayList<Cliente> clientes = ClienteDAO.listarClientes();
            ArrayList<Producto> productos = ProductoDAO.listarProductos();
            ArrayList<Trabajador> trabajadores = TrabajadorDAO.listarTrabajadores();
            ArrayList<Tratamiento> tratamientos = TratamientoDAO.listarTratamientos();
            List<String[]> citas = CitaDAO.listarCitas();
            String str;
            str = "Base de datos de " + DataBaseManager.username + ", " + LocalDate.now();
            writer.write(str);

            writer.newLine();
            writer.newLine(); //Dos saltos de línea

            writer.write("Trabajadores: ");
            writer.newLine();
            for (Trabajador t : trabajadores){
                writer.write(t.toString());
                writer.newLine();
            }
            writer.newLine();

            writer.write("Clientes: ");
            writer.newLine();
            for (Cliente c : clientes){
                writer.write(c.toString());
                writer.newLine();
            }
            writer.newLine();

            writer.write("Productos: ");
            writer.newLine();
            for (Producto p : productos){
                writer.write(p.toString());
                writer.newLine();
            }
            writer.newLine();

            writer.write("Tratamientos: ");
            writer.newLine();
            for (Tratamiento t : tratamientos){
                writer.write(t.toString());
                writer.newLine();
            }
            writer.newLine();

            writer.write("Citas: ");
            writer.newLine();
            for (String[] s : citas){
                String res ="";
                res += "Codigo de cita: " + s[0] + ", ";
                res += "Fecha y hora: " + s[1] + ", ";
                res += "Observaciones: " + s[2] + ", ";
                res += "Tratamiento: " + s[3] + ", ";
                res += "Trabajador encargado: " + s[4] + ", ";
                res += "Cliente atendido: " + s[5];
                writer.write(res);
                writer.newLine();
            }
            writer.newLine();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Archivo Guardado");
            alert.setContentText("Archivo guardado correctamente");
            alert.setHeaderText(null);
            alert.showAndWait();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

