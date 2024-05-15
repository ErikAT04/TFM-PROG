module com.example.tfmjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;

    opens com.example.tfmjava to javafx.fxml, javafx.base;
    opens com.example.tfmjava.Escenas.LogInSignUp to javafx.fxml;
    opens com.example.tfmjava.Escenas.Citas to javafx.fxml;
    opens com.example.tfmjava.Escenas to javafx.fxml;
    opens com.example.tfmjava.Escenas.Trabajadores to javafx.fxml;
    opens com.example.tfmjava.Escenas.Tratamientos to javafx.fxml;
    opens com.example.tfmjava.Escenas.Productos to javafx.fxml;
    opens com.example.tfmjava.Escenas.Clientes to javafx.fxml;
    opens com.example.tfmjava.Escenas.MenuOpciones to javafx.fxml;
    opens com.example.tfmjava.Objetos to javafx.fxml, javafx.base;

    exports com.example.tfmjava.Escenas.LogInSignUp;
    exports com.example.tfmjava.Escenas.Citas;
    exports com.example.tfmjava;
    exports com.example.tfmjava.Escenas;
    exports com.example.tfmjava.Escenas.Trabajadores;
    exports com.example.tfmjava.Escenas.MenuOpciones;
    exports com.example.tfmjava.Escenas.Clientes;
    exports com.example.tfmjava.Escenas.Productos;
    exports com.example.tfmjava.Escenas.Tratamientos;
    exports com.example.tfmjava.Objetos;
}