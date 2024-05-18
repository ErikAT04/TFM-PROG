package com.example.tfmjava.Escenas.Tratamientos;

import com.example.tfmjava.Objetos.DAO.ProductoDAO;
import com.example.tfmjava.Objetos.DAO.TratamientoDAO;
import com.example.tfmjava.Objetos.Producto;
import com.example.tfmjava.Objetos.Tratamiento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private CheckComboBox<String> prodComBox;

    @FXML
    private Button sendBtt;
    HashMap<String, Producto> prodMap;
    Boolean edit = false;

    @FXML
    void onSendClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Tratamientos");
        String accion = (edit) ? " editado " : " añadido ";
        int numFilas = 0;
        if (PrecioTField.getText().isEmpty() || descTextArea.getText().isEmpty() || duracionTField.getText().isEmpty() || nombreTField.getText().isEmpty() || prodComBox.getCheckModel().isEmpty()) {
            alert.setContentText("Alguno de los campos está vacío");
        } else {
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
                numFilas = TratamientoDAO.reiniciarProductosDeTratamientos(tratamiento.getCod_trat());
                if (numFilas > 0) {
                    numFilas = TratamientoDAO.addAllFilas(productosChecked, tratamiento.getCod_trat());
                    if (numFilas > 0) {
                        numFilas = TratamientoDAO.actualizarTratamiento(tratamiento);
                    }

                } else {
                    alert.setContentText("Ha habido un error preparando los comandos");
                }
            } else {
                numFilas = TratamientoDAO.addAllFilas(productosChecked, tratamiento.getCod_trat());
                if (numFilas > 0) {
                    numFilas = TratamientoDAO.addTratamiento(tratamiento);
                }
            }
            if (numFilas == 1){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Tratamiento" + accion + "correctamente");
                Stage stage = (Stage) this.descLabel.getScene().getWindow();
                stage.close();
            } else {
                alert.setContentText("No se ha podido llevar a cabo la acción");
            }
        }
        alert.showAndWait();
    }
    void toEdit(Tratamiento trat){
        edit=true;
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
