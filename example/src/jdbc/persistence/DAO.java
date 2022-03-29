package jdbc.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";

    protected void connectDatabase() throws Exception {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Error al conectarse");
        }
    }

    protected void disconnectDatabase() throws Exception {
        try {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new Exception("Error al desconectarse");
        }
    }

    protected void insertModifyDelete(String sql) throws Exception {
        try {
            connectDatabase();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new Exception("Error al ejecutar rollback");
            }
            throw new Exception("Error al ejecutar sentencia");
        } finally {
            disconnectDatabase();
        }
    }

    protected void queryDatabase(String sql) throws Exception {
        try {
            connectDatabase();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new Exception("Error al consultar");
        }
    }
}
