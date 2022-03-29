
package tienda.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sebas
 */
public class DAO {
    
    protected Connection conexion;
    protected Statement statement;
    protected ResultSet resultSet;
    
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/tienda?useSSL=false";
    
    protected void conectarDatabase() throws Exception {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Error al conectarse");
        }
    }

    protected void desconectarDatabase() throws Exception {
        try {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new Exception("Error al desconectarse");
        }
    }
    
    protected void insertarModificarEliminar(String sql) throws Exception {
        try {
            conectarDatabase();
            statement = conexion.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                throw new Exception("Error al ejecutar rollback");
            }
            throw new Exception("Error al ejecutar sentencia");
        } finally {
            desconectarDatabase();
        }
    }
    
    protected void consultarDatabase(String sql) throws Exception {
        try {
            conectarDatabase();
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new Exception("Error al consultar");
        }
    }
}
