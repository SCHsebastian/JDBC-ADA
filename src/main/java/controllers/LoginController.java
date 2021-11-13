package controllers;
import dbconnection.Login;
import dbconnection.LoginAccessDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    static LoginAccessDB loginAccessDB = new LoginAccessDB();
    static Login usuario = null;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private Pane login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField user;

    @FXML
    void inicia(ActionEvent event) throws SQLException{
        usuario = loginAccessDB.getLogin(user.getText(), password.getText());
        if(usuario != null){
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/fxml/principal.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            this.login.getScene().getWindow().hide();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Usuario o contraseña incorrectos");
            alert.setContentText("Por favor, revise los campos.");
            alert.showAndWait();
        }
    }

    @FXML
    void registra(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Registro");
        stage.setScene(scene);
        stage.show();
    }

}