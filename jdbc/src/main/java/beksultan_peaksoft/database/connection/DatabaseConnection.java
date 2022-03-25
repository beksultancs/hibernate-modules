package beksultan_peaksoft.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Beksultan
 */
public class DatabaseConnection {

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5678/postgres"
            );
        } catch (SQLException sqlException) {
            System.err.println("error: failed to connect to database");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
