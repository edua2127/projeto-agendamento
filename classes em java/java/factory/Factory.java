package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {
    String senha = "edua2127";

    public Connection getConection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/projeto1", "postgres", senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
