package com.example.tfmjava.Escenas.Clientes;

import com.example.tfmjava.Objetos.Cliente;
import com.example.tfmjava.Objetos.DAO.ClienteDAO;
import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

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

}
