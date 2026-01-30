package utils;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.sql.*;

public class JdbcConnection {

    private static final String DBurl = ConfigProperties.getDBUrl();
    private static final String user = ConfigProperties.getDBUser();
    private static final String password = ConfigProperties.getDBPassword();
    private static final Logger log = LoggerFactory.getLogger(JdbcConnection.class);

    private static Connection con = null;

    public static Connection connectionToDB() {
        try {
            if (con == null || con.isClosed()) {
                log.info(() -> "Connect to DB " + DBurl + " by " + user);
                con = DriverManager.getConnection(DBurl, user, password);
                log.info(() -> "Connection to DB successful");
            }
        } catch (SQLException e) {
            log.error(() -> "Connection to DB failed!\n" + e.getMessage());
            throw new RuntimeException("Cannot connect to DB", e);
        }
        return con;
    }

    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                log.info(() -> "DB connection closed");
            }
        } catch (SQLException e) {
            log.error(() -> "Error closing DB connection: " + e.getMessage());
        }
    }
}
