package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
        private static  String dbUrl;
        private static String dbUser;
        private static String dbPass;

        private static DatabaseManager instance;

        private DatabaseManager() {
            dbUrl = ConfigLoader.getDbUrl();
            dbUser = ConfigLoader.getDbUser();
            dbPass = ConfigLoader.getDbPassword();
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
