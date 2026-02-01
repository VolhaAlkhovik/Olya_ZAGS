package utils;

import java.sql.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class JdbcConnection {

  private static final String DBURL = ConfigProperties.getDBUrl();
  private static final String USER = ConfigProperties.getDBUser();
  private static final String PASSWORD = ConfigProperties.getDBPassword();
  private static final Logger LOG = LoggerFactory.getLogger(JdbcConnection.class);

  private static Connection con = null;

  public static Connection connectionToDB() {
    try {
      if (con == null || con.isClosed()) {
        LOG.info(() -> "Connect to DB " + DBURL + " by " + USER);
        con = DriverManager.getConnection(DBURL, USER, PASSWORD);
        LOG.info(() -> "Connection to DB successful");
      }
    } catch (SQLException e) {
      LOG.error(() -> "Connection to DB failed!\n" + e.getMessage());
      throw new RuntimeException("Cannot connect to DB", e);
    }
    return con;
  }

  public static void closeConnection() {
    try {
      if (con != null && !con.isClosed()) {
        con.close();
        LOG.info(() -> "DB connection closed");
      }
    } catch (SQLException e) {
      LOG.error(() -> "Error closing DB connection: " + e.getMessage());
    }
  }
}
