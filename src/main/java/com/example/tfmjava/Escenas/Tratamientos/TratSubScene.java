package com.example.tfmjava.Escenas.Tratamientos;

import com.example.tfmjava.Objetos.DAO.ProductoDAO;
import com.example.tfmjava.Objetos.Producto;
import com.example.tfmjava.Objetos.Tratamiento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TratSubScene implements Initializable {

    @FXML
    private TextField PrecioTField;

    @FXML
    private Label descLabel;

    @FXML
    private TextArea descTextArea;

    @FXML
    private TextField duracionTField;

    @FXML
    private TextField nombreTField;

    @FXML
    private CheckComboBox<String> prodComBox;

    @FXML
    private Button sendBtt;
    HashMap<String, Producto> prodMap;

    @FXML
    void onSendClick(ActionEvent event) {
    }
    void toEdit(Tratamiento trat){
        descTextArea.setText(trat.getDescripcion());
        duracionTField.setText(String.valueOf(trat.getDuracion_media_horas()));
        nombreTField.setText(trat.getNombre());

        IndexedCheckModel<String> modelo = prodComBox.getCheckModel();
        for (Producto p:trat.productos_utilizados){
            String s = p.getNombre() + "-" + p.getMarca();
            modelo.check(s);
        }
        prodComBox.setCheckModel(modelo);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Producto> productos = ProductoDAO.listarProductos();
        prodMap = new HashMap<>();
        ArrayList<String> productoNombre = new ArrayList<>();
        for (Producto p : productos){
            String s = p.getNombre() + "-" + p.getMarca();
            productoNombre.add(s);
            prodMap.put(s, p);
        }
        prodComBox.getItems().addAll(productoNombre);
    }
}
