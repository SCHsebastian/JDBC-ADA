package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class PrincipalController {

    @FXML
    private Label titulo;

    @FXML
    private Slider volumen;

    @FXML
    void adelantar(ActionEvent event) {

    }

    @FXML
    void adelante(ActionEvent event) {

    }

    @FXML
    void album(ActionEvent event) {

    }

    @FXML
    void atras(ActionEvent event) {

    }

    @FXML
    void atrasar(ActionEvent event) {

    }

    @FXML
    void bucle(ActionEvent event) {

    }

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    void megusta(ActionEvent event) {

    }

    @FXML
    void mezclar(ActionEvent event) {

    }

    @FXML
    void pausar(ActionEvent event) {

    }

    @FXML
    void perfil(ActionEvent event) {

    }

    @FXML
    void premium(ActionEvent event) {

    }

    @FXML
    void settings(ActionEvent event) {

    }

    @FXML
    void silenciar(ActionEvent event) {

    }

    public void initialize() {
        titulo.setText("Bienvenido "+LoginController.usuario.getName());
    }

}