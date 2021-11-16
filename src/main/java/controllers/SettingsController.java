package controllers;

import dbconnection.Login;
import dbconnection.LoginAccessDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class SettingsController {

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
    void buscaUsuario(ActionEvent event) {

    }

    @FXML
    void modificaUsuario(ActionEvent event) {
        //Modifica el usuario seleccionado en la tabla seleccionando su id
        Login login = tabla.getSelectionModel().getSelectedItem();
        if (login != null) {
            try {
                login.setName(tfNombreUsuario.getText());
                login.setPassword(tfNuevaContrasenya.getText());
                LoginController.loginAccessDB.updateLogin(login);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onEnter(ActionEvent event) {

    }

    public void initialize() throws SQLException {
        for (Login login: LoginController.loginAccessDB.getLogins()) {
            listaLogins.add(login);
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
