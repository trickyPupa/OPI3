package managers.databaseManager;

import managers.DebugTool;

import java.sql.*;

public class DatabaseCreator {

    static DebugTool logger = new DebugTool();

    public static void createTables() throws SQLException, ClassNotFoundException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS results ("
                + "id SERIAL PRIMARY KEY, "
                + "x FLOAT NOT NULL, "
                + "y FLOAT NOT NULL, "
                + "r FLOAT NOT NULL, "
                + "status BOOLEAN NOT NULL, "
                + "currentTime TEXT NOT NULL, "
                + "time_of_calculating FLOAT NOT NULL"
                + ")";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
            logger.info("Table 'Results' created or already exists.");
        }
    }
}

