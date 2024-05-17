package com.example.tfmjava.Escenas.Productos;

import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.DAO.ProductoDAO;
import com.example.tfmjava.Objetos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProdMainScene implements Initializable {
    public ObservableList<Producto> listaProductos;
    public Button addProductoId;
    public Button editProductoId;
    public Button delProductoId;
    @FXML
    private TableView<Producto> tablaProducto;
    @FXML
    private TableColumn colCodProducto;
    @FXML
    private TableColumn colNombreProducto;
    @FXML
    private TableColumn colMarcaProducto;
    @FXML
    private TableColumn colStock;
    @FXML
    public void onProductoAddClick(ActionEvent actionEvent) {
    }
    @FXML
    public void onProductoEditClick(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Borrar producto");
        Producto producto = tablaProducto.getSelectionModel().getSelectedItem();
        if (producto==null){
            alert.setContentText("No se ha seleccionado ningún producto.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Productos/ProductosSubMain.fxml"));
            Scene scene = loader.load();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        }
        tableRefresh();
    }
    @FXML
    public void onProductoDelClick(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Borrar producto");
        Producto producto = tablaProducto.getSelectionModel().getSelectedItem();
        if (producto==null){
            alert.setContentText("No se ha seleccionado ningún producto.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Productos/ProductosSubMain.fxml"));
            Scene scene = loader.load();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        }
        tableRefresh();
    }
    @FXML
    void stockDown(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bajada de stock");
        alert.setHeaderText(null);
        Producto producto = tablaProducto.getSelectionModel().getSelectedItem();
        if (producto==null){
            alert.setContentText("No se ha seleccionado ninguna fila");
        } else {
            if (producto.getStock()==0){
                alert.setContentText("No se puede bajar más el stock");
            } else {
                int filasActualizadas = ProductoDAO.restarStock(producto.getCod_prod());
                if (filasActualizadas == 1){
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Se ha actualizado el stock correctamente");
                } else {
                    alert.setContentText("Ha ocurrido un error");
                }
            }
        }
        alert.showAndWait();
        tableRefresh();
    }

    @FXML
    void stockUp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bajada de stock");
        alert.setHeaderText(null);
        Producto producto = tablaProducto.getSelectionModel().getSelectedItem();
        if (producto == null) {
            alert.setContentText("No se ha seleccionado ninguna fila");
        } else {
            alert.setContentText("No se puede bajar más el stock");
            int filasActualizadas = ProductoDAO.sumarStock(producto.getCod_prod());
            if (filasActualizadas == 1) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Se ha actualizado el stock correctamente");
            } else {
                alert.setContentText("Ha ocurrido un error");
            }

        }
        alert.showAndWait();
        tableRefresh();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCodProducto.setCellValueFactory(new PropertyValueFactory<>("cod_prod"));
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMarcaProducto.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableRefresh();
    }

    private void tableRefresh() {
        ArrayList<Producto> productos = ProductoDAO.listarProductos();
        ObservableList<Producto> productosList = FXCollections.observableList(productos);
        tablaProducto.setItems(productosList);
        tablaProducto.refresh();
    }

}
