package com.example.tfmjava.Escenas.Citas;

import com.example.tfmjava.Objetos.Cita;
import com.example.tfmjava.Objetos.Cliente;
import com.example.tfmjava.Objetos.DAO.CitaDAO;
import com.example.tfmjava.Objetos.DAO.ClienteDAO;
import com.example.tfmjava.Objetos.DAO.TrabajadorDAO;
import com.example.tfmjava.Objetos.DAO.TratamientoDAO;
import com.example.tfmjava.Objetos.Trabajador;
import com.example.tfmjava.Objetos.Tratamiento;
import com.example.tfmjava.Objetos.util.Validator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CitasSubScene implements Initializable {
    @FXML
    private TextArea observarTextArea;
    @FXML
    private ComboBox<String> clienteComboBox;
    @FXML
    private ComboBox<String> trabajadorComboBox;
    @FXML
    private DatePicker selectorFecha;
    @FXML
    private TextField selectorHora;
    @FXML
    private ComboBox<String> tratamientoComboBox;

    @FXML
    private ComboBox<String> horas;
    @FXML
    private Button sendBtt;

    private HashMap<String, Cliente> clienteHashMap;
    private HashMap<String, Trabajador> trabajadorHashMap;
    private HashMap<String, Tratamiento> tratamientoHashMap;
    boolean editar=false;
    Cita citaPrevia;

    @FXML
    private Label obserLabel;

    @FXML
    private void onActionButton() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error de acción");
        int numFilas;
        Stage stage = (Stage) this.clienteComboBox.getScene().getWindow();

        if (observarTextArea.getText().isEmpty() || clienteComboBox.getValue().isEmpty() || trabajadorComboBox.getValue().isEmpty() || tratamientoComboBox.getValue().isEmpty() || horas.getValue() == null) {
            alert.setContentText("No se han seleccionado todos los valores.");
        } else {
            String observaciones = observarTextArea.getText();
            Cliente cliente = clienteHashMap.get(clienteComboBox.getValue());
            Trabajador trabajador = trabajadorHashMap.get(trabajadorComboBox.getValue());
            Tratamiento tratamiento = tratamientoHashMap.get(tratamientoComboBox.getValue());
            LocalDate fecha = selectorFecha.getValue();
            Time hora = Time.valueOf(horas.getValue() + ":00");
            Cita cita = new Cita(fecha, hora, observaciones, trabajador, tratamiento, cliente);
            if (editar) {
                cita.setCod_cita(citaPrevia.getCod_cita());
                if (CitaDAO.otherFechaHora(cita)) {
                    alert.setContentText("Ya hay una cita en ese momento con este trabajador");
                } else {
                    numFilas = CitaDAO.actualizarCita(cita);
                    if (numFilas == 1) {
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Actualización");
                        alert.setContentText("Actualización de la cita realizada correctamente");
                        stage.close();
                    }
                }
            } else {
                cita.setCod_cita(0);
                if (CitaDAO.otherFechaHora(cita)) {
                    alert.setContentText("Ya hay una cita en ese momento con este trabajador");
                } else {
                    numFilas = CitaDAO.addCita(cita);
                    if (numFilas == 1) {
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Añadir cita");
                        alert.setContentText("Inserción de nueva cita correctamente");
                        stage.close();
                    } else {
                        alert.setContentText("Hubo un error añadiendo la cita");
                    }
                }
            }
        }
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Cliente> listaClientes = ClienteDAO.listarClientes();
        ArrayList<Tratamiento> listaTratamiento = TratamientoDAO.listarTratamientos();
        ArrayList<Trabajador> listaTrabajadores = TrabajadorDAO.listarTrabajadores();
        ArrayList<String> listaNombreClientes = new ArrayList<>();
        ArrayList<String> listaNombreTrabajadores = new ArrayList<>();
        ArrayList<String> listaNombreTratamientos = new ArrayList<>();
        //Primero, a rellenar el HashMap de trabajadores:
        trabajadorHashMap = new HashMap<>();
        for (Trabajador trabajador : listaTrabajadores){
            String trabajadorName = trabajador.getNombre() + "(" + trabajador.getDni() + ")"; //Por si se llaman varios igual
            trabajadorHashMap.put(trabajadorName, trabajador);
            listaNombreTrabajadores.add(trabajadorName); //Añado el nombre al arraylist
        }
        trabajadorComboBox.getItems().addAll(listaNombreTrabajadores); //Pongo el arraylist de nombres en el comboBox
        //Así con el resto

        clienteHashMap = new HashMap<>();
        for (Cliente cli : listaClientes){
            String clienteName = cli.getNombre() + " " + cli.getApellidos() + "(" + cli.getDni() + ")";
            clienteHashMap.put(clienteName, cli);
            listaNombreClientes.add(clienteName);
        }
        clienteComboBox.getItems().addAll(listaNombreClientes);

        tratamientoHashMap = new HashMap<>();
        for (Tratamiento t : listaTratamiento){
            tratamientoHashMap.put(t.getNombre(), t);
            listaNombreTratamientos.add(t.getNombre());
        }
        tratamientoComboBox.getItems().addAll(listaNombreTratamientos);

        ArrayList<String> horasArrayList = new ArrayList<>();
        int num = 8;
        while (num <=20){
            horasArrayList.add(String.format("%02d", num) + ":00");
            num++;
        }
        horas.getItems().addAll(horasArrayList);
    }

    public void toEdit(Cita cita){
        this.editar=true;
        this.sendBtt.setText("Editar");
        observarTextArea.setText(cita.getObservaciones());
        Cliente cli = cita.getCliente();
        clienteComboBox.setValue(cli.getNombre() + " " + cli.getApellidos() + "(" + cli.getDni() + ")");
        Trabajador t = cita.getTrabajador();
        trabajadorComboBox.setValue(t.getNombre() + "(" + t.getDni() + ")");
        Tratamiento trat = cita.getTratamiento();
        tratamientoComboBox.setValue(trat.getNombre());
        selectorFecha.setValue(cita.getFecha());
        horas.setValue(String.valueOf(cita.getHora()).substring(0,5));
        this.citaPrevia = cita;
    }
    @FXML
    void onObserType(KeyEvent event) {
        int num = observarTextArea.getText().length();
        if (num>250){
            observarTextArea.setText(observarTextArea.getText().substring(0, 29));
            num = observarTextArea.getText().length();
        }
        obserLabel.setText(num + "/30");
        if (num == 250){
            obserLabel.setTextFill(Color.RED);
        } else  {
            obserLabel.setTextFill(Color.GREY);
        }
    }
    @FXML
    void checkCitaDate(){
        if (selectorFecha.getValue().isBefore(LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de fecha");
            alert.setContentText("No puedes poner una fecha ya pasada");
            alert.setHeaderText(null);
            alert.showAndWait();
            selectorFecha.setValue(LocalDate.now());
        }
    }
}
