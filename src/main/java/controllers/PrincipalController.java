package controllers;

import dbconnection.Cancion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import java.awt.Desktop;
import java.net.URI;
import javafx.scene.layout.StackPane;
import javafx.scene.media.*;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrincipalController {

    private MediaPlayer mediaPlayer;
    private Media media;
    static ArrayList<Cancion> playlist = new ArrayList<>();
    static ArrayList<Media> mediaList = new ArrayList<>();
    boolean isPlaying = false;


    @FXML
    private Label nombreCancion;

    @FXML
    private Button btnPremium1;

    @FXML
    private Button btnPremium2;

    @FXML
    private Label titulo= new Label("Bienvenido");

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
    void premium(ActionEvent event) throws IOException, URISyntaxException {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=FSGBWZBTXZR3E"));
        }
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
        mediaPlayer.seek(mediaPlayer.getTotalDuration());
    }

    @FXML
    void adelante(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.millis(500)));
    }

    @FXML
    void atras(ActionEvent event) {

    }

    @FXML
    void atrasar(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.millis(500)));
    }

    @FXML
    void bucle(ActionEvent event) {
        while (mediaPlayer.getTotalDuration() == mediaPlayer.getCurrentTime()) {
            mediaPlayer.seek(mediaPlayer.getStartTime());
        }
    }

    @FXML
    void megusta(ActionEvent event) throws SQLException {
        Cancion xd = LoginController.loginAccessDB.getCancion(media.getSource());
        LoginController.loginAccessDB.addLike(xd);
    }

    @FXML
    void mezclar(ActionEvent event) {
        mediaList.sort((o1, o2) -> {
            return (int) (Math.random() * 3 - 1);
        });
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
        mediaPlayer.setVolume(0);
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
            contentPane.getChildren().add(home);
        }catch (Exception e) {
            e.printStackTrace();
        }
        for (Cancion url :
                playlist) {
            mediaList.add(new Media(url.getUrl()));
        }
       // media = mediaList.get(0);
       // mediaPlayer = new MediaPlayer(media);
        // nomCancion.setText(media.getSource().substring(media.getSource().lastIndexOf("/") + 1));
    }
}