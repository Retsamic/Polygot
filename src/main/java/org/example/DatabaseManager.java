package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
        private final String dbUrl;
        private final String dbUser;
        private final String dbPass;

        private static DatabaseManager instance;

        private DatabaseManager() {
            this.dbUrl = ConfigLoader.getDbUrl();
            this.dbUser = ConfigLoader.getDbUser();
            this.dbPass = ConfigLoader.getDbPassword();
        }

        public static DatabaseManager getInstance() {
            if (instance == null) {
                instance = new DatabaseManager();
            }
            return instance;
        }

        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(dbUrl, dbUser, dbPass);
        }
}
