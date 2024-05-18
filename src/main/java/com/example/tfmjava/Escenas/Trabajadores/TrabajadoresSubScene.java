package com.example.tfmjava.Escenas.Trabajadores;

import com.example.tfmjava.Objetos.DAO.TrabajadorDAO;
import com.example.tfmjava.Objetos.Trabajador;
import com.example.tfmjava.Objetos.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TrabajadoresSubScene implements Initializable {

    @FXML
    private TextField apellidoTField;

    @FXML
    private TextField dniTField;

    @FXML
    private CheckComboBox<String> horarioComBox;

    @FXML
    private TextField nombreTField;

    @FXML
    private Button sendBtt;
    @FXML
    private Label apellContador;
    @FXML
    private Label dniContador;
    @FXML
    private Label nombreContador;

    Boolean op=false;

    ArrayList <String> diasSemana = new ArrayList<>();

    int codT;

    @FXML
    void onSendBtt(ActionEvent event) {
        String apellido = this.apellidoTField.getText();
        String dni = this.dniTField.getText();
        String nombre = this.nombreTField.getText();
        ArrayList<String> diasHorario = new ArrayList<>(horarioComBox.getCheckModel().getCheckedItems());

        if (apellido.isEmpty() || dni.isEmpty() || nombre.isEmpty() || diasHorario.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El campo no puede estar vacío");
            alert.showAndWait();
        } else if(!Validator.validarDNI(dni)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El DNI no cumple el criterio esperado");
            alert.showAndWait();
        } else {
            String horario = "";
            for (String s : diasHorario) {
                horario = horario + s + ", ";
            }
            horario = horario.substring(0, horario.length() - 3);
            if (op) {
                int numFilas = 0;
                Trabajador trabajador = new Trabajador(dni, nombre, apellido, codT, horario);
                numFilas = TrabajadorDAO.actualizarTrabajador(trabajador);
                if (numFilas == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Actualización");
                    alert.setContentText("Se ha actualizado el trabajador con éxito");
                    alert.showAndWait();
                    Stage stage = (Stage) this.horarioComBox.getScene().getWindow();
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error...");
                    alert.setContentText("No se ha podido editar al trabajador");
                }
            } else {
            int numFilas = 0;
            Trabajador trabajador = new Trabajador(dni,nombre, apellido, horario);
            numFilas = TrabajadorDAO.addTrabajador(trabajador);
            if (numFilas == 1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Creación");
                alert.setContentText("Trabajador añadido correctamente");
                alert.showAndWait();
                Stage stage = (Stage) this.horarioComBox.getScene().getWindow();
                stage.close();
            } {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error...");
                alert.setContentText("Error al añadir el trabajador");
                alert.showAndWait();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diasSemana.add("Lunes");
        diasSemana.add("Martes");
        diasSemana.add("Miércoles");
        diasSemana.add("Jueves");
        diasSemana.add("Viernes");
        diasSemana.add("Sábado");
        diasSemana.add("Domingo");
        horarioComBox.getItems().addAll(diasSemana);
    }
    public void toEdit(Trabajador trabajador) {
        op = true;
        codT=trabajador.getCod_trabajador();
        this.dniTField.setText(trabajador.getDni());
        this.apellidoTField.setText(trabajador.getApellidos());
        this.nombreTField.setText(trabajador.getNombre());
        if (trabajador.getHorario().contains("Lunes")){
         horarioComBox.getCheckModel().check("Lunes");
        }
        if (trabajador.getHorario().contains("Martes")){
            horarioComBox.getCheckModel().check("Martes");
        }
        if (trabajador.getHorario().contains("Miércoles")){
            horarioComBox.getCheckModel().check("Miércoles");
        }
        if (trabajador.getHorario().contains("Jueves")){
            horarioComBox.getCheckModel().check("Jueves");
        }
        if (trabajador.getHorario().contains("Viernes")){
            horarioComBox.getCheckModel().check("Viernes");
        }
        if (trabajador.getHorario().contains("Sábado")){
            horarioComBox.getCheckModel().check("Sábado");
        }
        if (trabajador.getHorario().contains("Domingo")){
            horarioComBox.getCheckModel().check("Domingo");
        }
    }
    @FXML
    void onApellidoType(KeyEvent event) {
        String s = apellidoTField.getText();
        if (apellidoTField.getText().length()>50){ //Conteo del text field
            s = s.substring(0, 49); //Hace un substring que ocupa desde la posicion 0 a la 49 (50 posiciones en total). Substring es un string dentro de otro, digase, se queda con lo que tu selecciones
            apellidoTField.setText(s);
        }
        if (s.length()==50){
            apellContador.setTextFill(Color.RED);
        } else {
            apellContador.setTextFill(Color.GREY);
        }
        apellContador.setText(s.length() + "/50");
    }

    @FXML
    void onDNIType(KeyEvent event) {
        String s = dniTField.getText();
        if (dniTField.getText().length()>9){
            s = s.substring(0,8);
        }
        if (s.length()==9){
            dniContador.setTextFill(Color.RED);
        } else {
            dniContador.setTextFill(Color.GREY);
        }
        dniTField.setText(s.length() + "/9");
    }

    @FXML
    void onNombreType(KeyEvent event) {
        String s = nombreTField.getText();
        if (nombreTField.getText().length() > 20) {
            s = s.substring(0, 19);
        }
        if (s.length() == 20) {
            nombreContador.setTextFill(Color.RED);
        } else {
            nombreContador.setTextFill(Color.GREY);
        }
        nombreTField.setText(s.length() + "/20");
    }
}

