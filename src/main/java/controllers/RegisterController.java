package controllers;

import dbconnection.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class RegisterController {

    @FXML
    private Button cancel;

    @FXML
    private Button register;

    @FXML
    private PasswordField tfPass;

    @FXML
    private PasswordField tfPass2;

    @FXML
    private TextField tfUser;

    @FXML
    void cerrar(ActionEvent event) {
        this.cancel.getScene().getWindow().hide();
    }

    @FXML
    void registrar_usuario(ActionEvent event) throws SQLException {
        if (this.tfUser.getText().isEmpty() || this.tfPass.getText().isEmpty() || this.tfPass2.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Algún campo está vacio");
            alert.setContentText("Por favor, rellene todos los campos");
            alert.showAndWait();
        }
        else if (!this.tfPass.getText().equals(this.tfPass2.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Las contraseñas no coinciden");
            alert.setContentText("Por favor, introduzca dos contraseñas iguales");
            alert.showAndWait();
        }
        else {
            LoginController.loginAccessDB.addLogin(new Login(1,tfUser.getText(), tfPass.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro completado");
            alert.setHeaderText("El usuario se ha registrado correctamente");
            alert.showAndWait();
            this.cerrar(event);
        }
    }

}

