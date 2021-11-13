package dbconnection;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        LoginAccessDB loginAccessDB = new LoginAccessDB();
        for (Login login: loginAccessDB.getLogins()) {
            System.out.println(login.getName());
        }
    }
}


