package controllers;

import dbconnection.Login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Date;

public class SettingsController {

    Login este = LoginController.usuario;

    ArrayList<Login> listaLogins = new ArrayList<>();

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCambiar;

    @FXML
    private Button btnModifica;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TableView<Login> tabla;

    @FXML
    private TableColumn<Login, Date> created_at;

    @FXML
    private TableColumn<Login, Integer> id;

    @FXML
    private TableColumn<Login, Integer> premium;

    @FXML
    private TableColumn<Login, String> username;

    @FXML
    private PasswordField tfAntiguaContrasenya;

    @FXML
    private TextField tfNombreUsuario;

    @FXML
    private PasswordField tfNuevaContrasenya;

    @FXML
    private PasswordField tfNuevaContrasenya2;

    @FXML
    private TextField tfUserABuscar;

    @FXML
    private Tab listaMusica;

    @FXML
    private Tab listaUsuarios;

    @FXML
    private Tab perfil;

    @FXML
    private TabPane tabPane;


    @FXML
    void EliminarUsuario(ActionEvent event) {
        //Elimina un usuario de la tabla
        Alert seguro = new Alert(Alert.AlertType.CONFIRMATION);
        seguro.setTitle("Confirmación");
        seguro.setContentText("¿Estás seguro de que quieres eliminar este usuario?");
        seguro.showAndWait();
        if (seguro.getResult() == ButtonType.OK) {
            Login login = tabla.getSelectionModel().getSelectedItem();
            if (login != null) {
                LoginController.loginAccessDB.deleteLogin(login);
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setContentText("No se ha seleccionado ningún usuario");
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setContentText("No se ha eliminado ningún usuario");
        }

    }

    @FXML
    void HacerQuitarPremium(ActionEvent event) {
        //Quita o pone premium a un usuario
        Login login = tabla.getSelectionModel().getSelectedItem();
        if (login != null) {
            if (login.getNivel() == 1) {
                login.setNivel(2);
                LoginController.loginAccessDB.updateLogin(login);
            }
            else if (login.getNivel() == 2) {
                login.setNivel(1);
                LoginController.loginAccessDB.updateLogin(login);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setContentText("No se puede cambiar nivel a un administrador");
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setContentText("No se ha seleccionado ningún usuario");
        }
    }

    @FXML
    void buscaUsuario(ActionEvent event) {
        //Busca un usuario en la tabla
        ArrayList<Login> listaEncontrados = new ArrayList<>();
        String user = tfUserABuscar.getText();
        for (Login login: listaLogins) {
            if (login.getName().contains(user)) {
                listaEncontrados.add(login);
            }
        }
        if (listaEncontrados.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("NOT FOUND");
            alert.setContentText("No existe el usuario");
        }else {
            ObservableList<Login> lista2 = FXCollections.observableArrayList(listaEncontrados);
            tabla.setItems(null);
            tabla.setItems(lista2);
            tabla.getColumns();
        }
    }

    @FXML
    void modificaUsuario(ActionEvent event) {
        //Modifica el usuario seleccionado en la tabla seleccionando su id
        Login login = tabla.getSelectionModel().getSelectedItem();
        if (login != null) {
            tfNombreUsuario.setText(login.getName());
            tfAntiguaContrasenya.setText(login.getPassword());
        }
        tabPane.getSelectionModel().select(perfil);
    }

    @FXML
    void onEnter(ActionEvent event) {
        //Modifica a un usuario seleccionado en la tabla o al usuario normal
        Login editar = null;
        if(checkBox.isSelected() && (tfNuevaContrasenya2.getText().equals(tfNuevaContrasenya.getText()))){
            editar = LoginController.loginAccessDB.getLogin(tfNombreUsuario.getText(),tfAntiguaContrasenya.getText());

            if (editar!=null){
                    editar.setPassword(tfNuevaContrasenya.getText());
                    LoginController.loginAccessDB.updateLogin(editar);
                    if (este.getNivel() == 3) {
                        actualizaTabla();
                    }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Antigua contraseña incorrecta");
                alert.show();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("CheckBox no seleccionado");
            alert.show();
        }
    }

    public void initialize(){
        //Inicializa la tabla
        listaUsuarios.setDisable(true);
        listaMusica.setDisable(true);
        if (este.getNivel() == 3) {
            ensenyaTabla();
            actualizaTabla();
        }
        tfNombreUsuario.setDisable(true);
        tfNombreUsuario.setText(este.getName());
    }

    private void ensenyaTabla() {
        listaUsuarios.setDisable(false);
        listaMusica.setDisable(false);
    }

    private void actualizaTabla(){
        //Actualiza la tabla
        for (Login login: LoginController.loginAccessDB.getLogins()) {
            if (tabla.getItems().contains(login)) {
                tabla.getItems().remove(login);
            }else {
                listaLogins.add(login);
            }
        }
        //Introducimos los usuarios en la tabla

        created_at.setCellValueFactory(new PropertyValueFactory<>("creation_date"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        premium.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        username.setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<Login> lista = FXCollections.observableArrayList(listaLogins);
        tabla.setItems(lista);
        tabla.getColumns();
    }

}
