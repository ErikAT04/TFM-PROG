package com.example.tfmjava.Escenas.Tratamientos;

import com.example.tfmjava.Objetos.DAO.ProductoDAO;
import com.example.tfmjava.Objetos.DAO.TratamientoDAO;
import com.example.tfmjava.Objetos.Producto;
import com.example.tfmjava.Objetos.Tratamiento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
    private Label nombreContador;


    @FXML
    private CheckComboBox<String> prodComBox;

    @FXML
    private Button sendBtt;
    HashMap<String, Producto> prodMap;
    Boolean edit = false;
    Tratamiento prevTratamiento;

    @FXML
    void onSendClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Tratamientos");
        String accion = (edit) ? " editado " : " añadido ";
        int numFilas = 0;
        if (PrecioTField.getText().isEmpty() || descTextArea.getText().isEmpty() || duracionTField.getText().isEmpty() || nombreTField.getText().isEmpty()) {
            alert.setContentText("Alguno de los campos está vacío");
        } else {
            try {
                String nombre = nombreTField.getText();
                String descripcion = descTextArea.getText();
                double duracion_media = Double.parseDouble(duracionTField.getText());
                double precio = Double.parseDouble(PrecioTField.getText());

                Tratamiento tratamiento = new Tratamiento(nombre, descripcion, precio, duracion_media);
                ArrayList<String> productos = new ArrayList<>(prodComBox.getCheckModel().getCheckedItems());
                ArrayList<Producto> productosChecked = new ArrayList<>();
                for (String s : productos) {
                    Producto producto = prodMap.get(s);
                    productosChecked.add(producto);
                }
                if (edit) {
                    tratamiento.setCod_trat(prevTratamiento.getCod_trat());
                    TratamientoDAO.reiniciarProductosDeTratamientos(tratamiento.getCod_trat());
                    numFilas = TratamientoDAO.addAllFilas(productosChecked, tratamiento.getCod_trat());
                    if (numFilas >= 0) {
                        numFilas = TratamientoDAO.actualizarTratamiento(tratamiento);
                    }

                } else {
                    numFilas = TratamientoDAO.addAllFilas(productosChecked, tratamiento.getCod_trat());
                    if (numFilas >= 0) {
                        numFilas = TratamientoDAO.addTratamiento(tratamiento);
                    }
                }
                if (numFilas == 1) {
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Tratamiento" + accion + "correctamente");
                    Stage stage = (Stage) this.descLabel.getScene().getWindow();
                    stage.close();
                } else {
                    alert.setContentText("No se ha podido llevar a cabo la acción");
                }
            }catch (Exception e){
                alert.setContentText("Los valores de precio y tiempo medio son solo numéricos");
            }
        }
        alert.showAndWait();
    }
    void toEdit(Tratamiento trat){
        edit=true;
        this.prevTratamiento = trat;
        descTextArea.setText(trat.getDescripcion());
        duracionTField.setText(String.valueOf(trat.getDuracion_media_horas()));
        PrecioTField.setText(String.valueOf(trat.getPrecio()));
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

    @FXML
    void onDescType(KeyEvent event) {
        int num = descTextArea.getText().length(); //Esto guarda la longitud
        if (descTextArea.getText().length()>250){ //Lo compara
            descTextArea.setText(descTextArea.getText().substring(0,249)); //Actualiza el texto
        }
        if (num == 250){
            descLabel.setTextFill(Color.RED);
        } else {
            descLabel.setTextFill(Color.GREY);
        }
        descLabel.setText(num + "/250");
    }
    @FXML
    void onNameType(KeyEvent event) {
    int num = nombreTField.getText().length();
    if (nombreTField.getText().length()>40){
        nombreTField.setText(nombreTField.getText().substring(0,39));
        num = nombreTField.getText().length();
    }
    if (num == 40){
        nombreContador.setTextFill(Color.RED);
    }else {
        nombreContador.setTextFill(Color.GREY);
    }
        nombreContador.setText(num + "/40");
    }
}
