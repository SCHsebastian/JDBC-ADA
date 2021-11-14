package dbconnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Album {
    private int id;
    private String name;
    private String description;
    private ArrayList<Cancion> canciones;

    public Album(){
        this.canciones = new ArrayList<>();
    }

    public Album(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ArrayList<Album> getAlbums() {
        Connection connection2 = DatabaseConnection.getConnection();
        ArrayList<Album> albums = new ArrayList<>();
        String query = "SELECT * FROM albums";
        try {
            connection2.createStatement().executeQuery(query);
            java.sql.ResultSet rs = connection2.createStatement().executeQuery(query);
            while (rs.next()) {
                Album album = new Album();
                album.id = rs.getInt("id");
                album.name = rs.getString("name");
                album.description = rs.getString("description");
                albums.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    public void addCancion(Cancion cancion){
        this.canciones.add(cancion);
    }

    public void removeCancion(Cancion cancion){
        this.canciones.remove(cancion);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", name=" + name + ", description=" + description + ", canciones=" + canciones + '}';
    }
}
