package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.media.*;

import java.io.IOException;
import java.util.ArrayList;

public class PrincipalController {

    private MediaPlayer mediaPlayer;
    private Media media;
    static ArrayList<String> playlist = new ArrayList<>();

    boolean isPlaying = false;

    @FXML
    private Button btnPremium1;

    @FXML
    private Button btnPremium2;

    @FXML
    private Label titulo;

    @FXML
    private Slider volumen;

    @FXML
    private StackPane contentPane;

    // Acciones del menu

    @FXML
    void perfil(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/fxml/principalPages/perfil.fxml"));
        contentPane.getChildren().removeAll();
        contentPane.getChildren().setAll(home);
    }

    @FXML
    void premium(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/fxml/principalPages/premium.fxml"));
        contentPane.getChildren().removeAll();
        contentPane.getChildren().setAll(home);
    }

    @FXML
    void settings(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/fxml/principalPages/settings.fxml"));
        contentPane.getChildren().removeAll();
        contentPane.getChildren().setAll(home);
    }

    @FXML
    void home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/principalPages/home.fxml"));
        contentPane.getChildren().removeAll();
        contentPane.getChildren().setAll(root);
    }

    @FXML
    void miMusica(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/fxml/principalPages/miMusica.fxml"));
        contentPane.getChildren().removeAll();
        contentPane.getChildren().setAll(home);
    }

    /// Acciones del slider

    @FXML
    void adelantar(ActionEvent event) {

    }

    @FXML
    void adelante(ActionEvent event) {

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
    void megusta(ActionEvent event) {

    }

    @FXML
    void mezclar(ActionEvent event) {

    }

    @FXML
    void pausar(ActionEvent event) {
        if (isPlaying) {
            mediaPlayer.pause();
        }else {
            mediaPlayer.play();
        }
    }

    @FXML
    void silenciar(ActionEvent event) {

    }

    /// Eventos

    public void initialize() {
        titulo.setText("Bienvenido "+LoginController.usuario.getName());
        if (LoginController.usuario.getNivel() == 2 || LoginController.usuario.getNivel() == 3) {
            btnPremium1.setVisible(false);
            btnPremium2.setVisible(false);
        }
        try {
            Parent home = FXMLLoader.load(getClass().getResource("/fxml/principalPages/home.fxml"));
            contentPane.getChildren().removeAll();
            contentPane.getChildren().setAll(home);
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

}