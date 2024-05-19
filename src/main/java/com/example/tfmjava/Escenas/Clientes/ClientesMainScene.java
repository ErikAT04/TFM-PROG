package com.example.tfmjava.Escenas.Clientes;

import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.Cliente;
import com.example.tfmjava.Objetos.DAO.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClientesMainScene implements Initializable {
    public TableColumn colDNICliente;
    public TableColumn colNombreCliente;
    public TableColumn colApellidosCliente;
    public TableColumn colTlfCliente;
    public TableColumn colEdadCliente;
    public TableColumn colFechaNacCliente;
    public TableColumn colEspecificacionesCliente;
    public Button AddClienteId;
    public Button EditClienteId;
    public Button DelClienteId;
    public TableView<Cliente> clienteTable;
    ObservableList<Cliente> clienteObservableList;
    public void onClienteAddClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Clientes/ClientesSubMain.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("ToqHer - Creación de Clientes");
        Image img = new Image(Objects.requireNonNull(InitApplication.class.getResource("otherSRC/img/favicon.png")).toString());
        stage.getIcons().add(img);
        stage.setScene(scene);
        stage.showAndWait();
        tableRefresh();
    }

    public void onClienteEditClick(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de edición");
        alert.setHeaderText(null);
        Cliente cliente = clienteTable.getSelectionModel().getSelectedItem();
        if(cliente==null){
            alert.setContentText("No se ha seleccionado un cliente válido");
            alert.showAndWait();
        }else {
            Stage stage = new Stage();
            stage.setTitle("ToqHer - Edición de Clientes");
            Image img = new Image(Objects.requireNonNull(InitApplication.class.getResource("otherSRC/img/favicon.png")).toString());
            stage.getIcons().add(img);
            FXMLLoader loader = new FXMLLoader(InitApplication.class.getResource("Clientes/ClientesSubMain.fxml"));
            Scene scene = new Scene(loader.load());
            ClientesSubScene controller = loader.getController();
            controller.toEdit(cliente);
            stage.setScene(scene);
            stage.showAndWait();
        }
        tableRefresh();
    }

    public void onClienteDeleteClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de borrado");
        alert.setHeaderText(null);
        Cliente cliente = clienteTable.getSelectionModel().getSelectedItem();
        if (cliente==null){
            alert.setContentText("No se ha seleccionado un usuario");
        } else {
            int numFilas = ClienteDAO.borrarCliente(cliente.getDni());
            if (numFilas==1){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Cliente borrado correctamente");
                alert.setTitle("Borrado de clientes");
            } else {
                alert.setContentText("Ha ocurrido un error. Contacte con el equipo que le ha vendido este producto");
            }
        }
        alert.showAndWait();
        tableRefresh();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDNICliente.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidosCliente.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colTlfCliente.setCellValueFactory(new PropertyValueFactory<>("telf"));
        colEdadCliente.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colFechaNacCliente.setCellValueFactory(new PropertyValueFactory<>("fnac"));
        colEspecificacionesCliente.setCellValueFactory(new PropertyValueFactory<>("especificaciones"));
        tableRefresh();
    }
    public void tableRefresh(){
        clienteObservableList = FXCollections.observableList(ClienteDAO.listarClientes());
        clienteTable.setItems(clienteObservableList);
        clienteTable.refresh();
    }
}
