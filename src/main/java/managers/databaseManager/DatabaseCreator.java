package managers.databaseManager;

import managers.dataModels.Result;
import managers.databaseManager.DatabaseConnectionManager;

import java.sql.*;

public class DatabaseCreator {

    public void createTables() throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS results ("
                + "id SERIAL PRIMARY KEY, "
                + "x DOUBLE PRECISION NOT NULL, "
                + "y DOUBLE PRECISION NOT NULL, "
                + "r DOUBLE PRECISION NOT NULL, "
                + "status BOOLEAN NOT NULL, "
                + "current_time TIMESTAMP NOT NULL, "
                + "time_of_calculating DOUBLE PRECISION NOT NULL"
                + ")";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
            System.out.println("Table 'Results' created or already exists.");
        }
    }
}
