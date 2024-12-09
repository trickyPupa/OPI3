package managers.databaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String DB_USER = "naku";
    private static final String DB_PASSWORD = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
