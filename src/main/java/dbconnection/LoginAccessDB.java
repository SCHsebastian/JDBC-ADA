package dbconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginAccessDB {
    private DatabaseConnection con = DatabaseConnection.getInstance();
    private Connection connection = con.getConnection();

    public List<Login> getLogins(){
        try {
            String sql = "SELECT * FROM login";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Login> logins = new ArrayList<Login>();
            while (resultSet.next()){
                Login login = new Login();
                login.setId(resultSet.getInt(1));
                login.setName(resultSet.getString(2));
                login.setPassword(resultSet.getString(3));
                login.setCreationDate(resultSet.getDate(4));
                login.setNivel(resultSet.getInt(5));
                logins.add(login);
            }
            return logins;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error al obtener");
        }
        return null;
    }

    public Login getLogin(String name, String password){
        if (name == "" || password == ""){
            return null;
        }
        try {
            String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int uno = resultSet.getInt(1);
                String dos = resultSet.getString(2);
                String tres = resultSet.getString(3);
                Date cuatro = resultSet.getDate(4);
                Login login = new Login(uno, dos, tres, cuatro);
                login.setNivel(resultSet.getInt(5));
                return login;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener");
        }
        return null;
    }

    public Login getLogin(int id){
        if (id < 0){
            return null;
        }
        try {
            String sql = "SELECT * FROM login WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery(sql);
            Login login = new Login();
            while (resultSet.next()){
                login.setId(resultSet.getInt(1));
                login.setName(resultSet.getString(2));
                login.setPassword(resultSet.getString(3));
                login.setCreationDate(resultSet.getDate(4));
                login.setNivel(resultSet.getInt(5));
            }
            return login;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error al obtener");
            return null;
        }
    }

    public int addLogin(Login login){
        if (login == null){
            return -1;
        }try {
            String sql = "INSERT INTO login (username, password, created_at, nivel) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login.getName());
            statement.setString(2, login.getPassword());
            statement.setDate(3,new java.sql.Date(System.currentTimeMillis()));
            statement.setInt(4, login.getNivel());
            statement.executeUpdate();
            return login.getId();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error al insertar");
            return -1;
        }
    }

    public void updateLogin(Login login){
        if (login == null){
            return;
        }
        try {
            String sql = "UPDATE login SET username = ?, password = ?, created_at = ?, nivel = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login.getName());
            statement.setString(2, login.getPassword());
            statement.setDate(3, (Date) login.getCreation_date());
            statement.setInt(4, login.getNivel());
            statement.setInt(5,login.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error al actualizar el login");
        }
    }

    public void deleteLogin(Login login){
        if (login == null){
            return;
        }
        try {
            String sql = "DELETE FROM login WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, login.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error al eliminar");
        }
    }

    public Cancion getCancion(String url){
        try {
            String sql = "SELECT * FROM cancion WHERE url = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, url.toString());
            ResultSet resultSet = statement.executeQuery();
            Cancion cancion = new Cancion(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3), resultSet.getString(4),resultSet.getString(5) );
            return cancion;
        }   catch (SQLException e) {
            System.out.println("Error al conseguir cancion");
        }
        return null;
    }

    public void addLike(Cancion cancion){
        try {
            String sql = "UPDATE cancion SET likes = likes + 1 WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cancion.getId());
            statement.executeUpdate();
        }   catch (SQLException e) {
            System.out.println("Error al añadir like");
        }

    }

    public void dropDatabase(){
        try {
            String sql = "DROP DATABASE IF EXISTS severo.ad";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error al borrar la base de datos");
        }
    }
}