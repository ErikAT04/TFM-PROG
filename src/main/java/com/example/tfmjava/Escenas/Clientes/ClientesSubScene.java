package com.example.tfmjava.Escenas.Clientes;

import com.example.tfmjava.Objetos.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private TextField espec;
    @FXML
    private DatePicker fnacChooser;
    @FXML
    private Button sendBtt;

    Cliente prevCliente;
    boolean edit = false;

    private void onSendClick(){
        if (nombreTF.getText().isEmpty() || apellidosTF.getText().isEmpty() || dniTF.getText().isEmpty() || telTF.getText().isEmpty() || espec.getText().isEmpty() || fnacChooser.getValue()==null){

        }
    }

    public void toEdit(Cliente cliente){
        edit=true;
        this.sendBtt.setText("Editar");
        this.prevCliente = cliente;
        this.nombreTF.setText(cliente.getNombre());
        this.apellidosTF.setText(cliente.getApellidos());
        this.dniTF.setText(cliente.getDni());
        this.telTF.setText(String.valueOf(cliente.getTelf()));
        this.espec.setText(cliente.getEspecificaciones());
        this.fnacChooser.setValue(cliente.getFnac());
    }

}
