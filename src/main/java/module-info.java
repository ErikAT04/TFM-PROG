module com.example.tfmjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.tfmjava to javafx.fxml;
    exports com.example.tfmjava;
    exports com.example.tfmjava.Escenas;
    opens com.example.tfmjava.Escenas to javafx.fxml;
    exports com.example.tfmjava.Escenas.LogInSignUp;
    opens com.example.tfmjava.Escenas.LogInSignUp to javafx.fxml;
}