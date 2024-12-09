package managers.databaseManager;

import managers.databaseManager.DatabaseConnectionManager;

import java.sql.*;

public class DatabaseQueryManager {

    public void executeInsertQuery(String query, Object... parameters) throws SQLException {
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }
            stmt.executeUpdate();
        }
    }

    public ResultSet executeSelectQuery(String query, Object... parameters) throws SQLException {
        Connection conn = DatabaseConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        for (int i = 0; i < parameters.length; i++) {
            stmt.setObject(i + 1, parameters[i]);
        }
        return stmt.executeQuery();
    }
}
