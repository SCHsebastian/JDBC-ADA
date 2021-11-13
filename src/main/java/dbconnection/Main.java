package dbconnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        Properties props = new Properties();
        props.load(new FileReader("src/config.properties"));
        String user=props.getProperty("user");
        String password=props.getProperty("password");
        String url=props.getProperty("url");

        Connection con = null;

        try {
            con = DriverManager.getConnection(url,user,password);
            if (con != null){
                System.out.println("The connection to the database was succesfull!");
            }
            con.close();
        } catch (SQLException ex){
            if (ex.getErrorCode() == 28000){
                System.out.println("The connection to the database was not succesfull!");
            }
        }

    }

}
