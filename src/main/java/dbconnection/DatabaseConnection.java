package dbconnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    static DatabaseConnection connection;

    //Me creo la clase conecction utilizando el patron singleton
    //ESTE ES EL PATRON SINGLETON

    public static DatabaseConnection getInstance(){
        //este metodo será el que yo utilice desde fuera para
        // obtener la conexion única a la BBDD

        if(connection==null) {
            connection=new DatabaseConnection();
        }
        return connection;
    }

    public static Connection getConnection(){

        Properties props = new Properties();
        Connection con = null;
        try {
            props.load(new FileReader("src/main/java/dbconnection/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String user=props.getProperty("user");
        String password=props.getProperty("password");
        String url=props.getProperty("url");

        try {
            con = DriverManager.getConnection(url,user,password);
            if (con != null){
                System.out.println("The connection to the database was succesfull!");
            }
        } catch (SQLException ex){
            if (ex.getErrorCode() == 28000){
                System.out.println("The connection to the database was not succesfull!");
            }
        }
        return con;
    }



}
