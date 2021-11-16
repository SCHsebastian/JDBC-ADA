package dbconnection;

public class Cancion {
    private int id;
    private String nombre;
    private String artista;
    private String duracion;
    private String url;
    private boolean isPremium=false;

    public Cancion(int id, String nombre, String artista, String duracion, String url){
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.duracion = duracion;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean isPpremium) {
        this.isPremium = isPremium;
    }

    public String toString(){
        return "id: " + id + " nombre: " + nombre + " artista: " + artista + " duracion: " + duracion + " url: " + url;
    }

}
