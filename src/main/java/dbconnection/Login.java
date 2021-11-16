package dbconnection;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Login {

    private int id;
    private String name;
    private String password;
    private Date creation_date;
    private int nivel = 1;
    private ArrayList<Album> albums;

    public Login(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        creation_date = new Date(System.currentTimeMillis());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreationDate(Date creation_date) {
        this.creation_date = creation_date;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public void addAlbum(Album album) {
        if(albums == null) {
            albums = new ArrayList<>();
        }
        albums.add(album);
    }

    public Login() {
        this.id = 0;
        this.name = "";
        this.password = "";
        this.creation_date = new Date();
    }

    public Login(int id, String name, String password, Date creation_date) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.creation_date = creation_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return id == login.id && name.equals(login.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}