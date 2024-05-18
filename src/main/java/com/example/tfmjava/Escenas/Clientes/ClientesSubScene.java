package com.example.tfmjava.Escenas.Clientes;

import com.example.tfmjava.Objetos.Cliente;
import com.example.tfmjava.Objetos.DAO.ClienteDAO;
import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ClientesSubScene{
    @FXML
    private TextField nombreTF;
    @FXML
    private TextField apellidosTF;
    @FXML
    private TextField dniTF;
    @FXML
    private TextField telTF;
    @FXML
    private TextArea espec;
    @FXML
    private DatePicker fnacChooser;
    @FXML
    private Button sendBtt;
    @FXML
    private Label contadorLabel;
    @FXML
    private Label apellContador;
    @FXML
    private Label nombreContador;
    @FXML
    private Label telfContador;

    @FXML
    private Label dniContador;

    boolean edit = false;
    @FXML
    private void onSendClick(){
        int numFilas = 0;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String accion = " creado ";
        alert.setTitle("Modificación de clientes");
        alert.setHeaderText(null);
        try {
            if (nombreTF.getText().isEmpty() || apellidosTF.getText().isEmpty() || dniTF.getText().isEmpty() || telTF.getText().isEmpty() || fnacChooser.getValue() == null) {
                alert.setContentText("Hay valores que no pueden ser nulos");
            } else if (!Validator.validarDNI(dniTF.getText())){
                alert.setContentText("El DNI no sigue el criterio esperado");
            } else {

                String nombre = nombreTF.getText();
                String apellidos = apellidosTF.getText();
                String dni = dniTF.getText();
                int telf = Integer.parseInt(telTF.getText());
                String especificaciones = (espec.getText().isEmpty()) ? "SIN ESPECIFICAR" : espec.getText();
                LocalDate fechaNac = fnacChooser.getValue();

                LocalDate aux = fechaNac;
                int edad = 0;
                while (aux.isBefore(LocalDate.now())) {
                    aux = aux.plusYears(1);
                    if (aux.isBefore(LocalDate.now())) {
                        edad++;
                    }
                }
                Cliente cliente = new Cliente(dni, nombre, apellidos, telf, edad, fechaNac, especificaciones);
                if (edit) {
                    accion = " modificado ";
                    numFilas = ClienteDAO.actualizarCliente(cliente);
                } else {
                    Cliente clienteBuscar = ClienteDAO.buscarCliente(cliente.getDni());
                    if (clienteBuscar != null) {
                        alert.setContentText("Ya se ha encontrado un cliente con ese DNI");
                    } else {
                        numFilas = ClienteDAO.addCliente(cliente);
                    }
                }
                if (numFilas == 1) {
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Cliente" + accion + "correctamente");
                    Stage stage = (Stage) this.sendBtt.getScene().getWindow();
                    stage.close();
                }
            }
        }catch (Exception e){
            alert.setContentText("El teléfono solo se puede constituir de numeros");
        }
        alert.showAndWait();
    }
    @FXML
    void onObservType(KeyEvent event) {
        int num = espec.getText().length();
        if (num > 250){
            espec.setText(espec.getText().substring(0, 249));
            num = 250;
        }
        if (num == 250){
            contadorLabel.setTextFill(Color.RED);
        } else {
            contadorLabel.setTextFill(Color.GREY);
        }
        contadorLabel.setText(num + "/250");
    }
    public void toEdit(Cliente cliente){
        edit=true;
        this.sendBtt.setText("Editar");
        this.dniTF.setEditable(false);
        this.nombreTF.setText(cliente.getNombre());
        this.apellidosTF.setText(cliente.getApellidos());
        this.dniTF.setText(cliente.getDni());
        this.telTF.setText(String.valueOf(cliente.getTelf()));
        this.espec.setText(cliente.getEspecificaciones());
        this.fnacChooser.setValue(cliente.getFnac());
        onObservType(null); //iniciar el texto
    }

    @FXML
    void onTelfType(KeyEvent event) {
        int num = telTF.getText().length();
        if (num>9){
            telTF.setText(telTF.getText().substring(0, 8));
            num = telTF.getText().length();
        }
        telfContador.setText(num + "/9");
        if (num==9){
            telfContador.setTextFill(Color.RED);
        } else {
            telfContador.setTextFill(Color.GREY);
        }
    }
    @FXML
    void checkBornDate(ActionEvent event) {
        if (fnacChooser.getValue().isAfter(LocalDate.now()) || fnacChooser.getValue().isEqual(LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de fecha");
            alert.setContentText("No puede haber un cliente recién nacido (o ni eso)");
            alert.setHeaderText(null);
            alert.showAndWait();
            fnacChooser.setValue(LocalDate.now().minusDays(1));
        }
    }

    @FXML
    void onApellidoType(KeyEvent event) {
        int num = apellidosTF.getText().length();
        if (num>50){
            apellidosTF.setText(apellidosTF.getText().substring(0, 49));
            num = apellidosTF.getText().length();
        }
        apellContador.setText(num + "/50");
        if (num==50){
            apellContador.setTextFill(Color.RED);
        } else {
            apellContador.setTextFill(Color.GREY);
        }
    }

    @FXML
    void onDNIType(KeyEvent event) {
        int num = dniTF.getText().length();
        if (num>9){
            dniTF.setText(dniTF.getText().substring(0, 8));
            num = dniTF.getText().length();
        }
        dniContador.setText(num + "/9");
        if (num==9){
            dniContador.setTextFill(Color.RED);
        } else {
            dniContador.setTextFill(Color.GREY);
        }
    }

    @FXML
    void onNombreType(KeyEvent event) {
        int num = nombreTF.getText().length();
        if (num>20){
            nombreTF.setText(nombreTF.getText().substring(0, 19));
            num = dniTF.getText().length();
        }
        nombreContador.setText(num + "/20");
        if (num==20){
            nombreContador.setTextFill(Color.RED);
        } else {
            nombreContador.setTextFill(Color.GREY);
        }
    }
}
