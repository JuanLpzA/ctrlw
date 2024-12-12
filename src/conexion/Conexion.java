
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Arrunategui
 */
public class Conexion {
    public static Connection conectar(){
    
        try {
            Connection cn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-A7C34VN2\\\\MSSQL:1433;databaseName=bd_controlware;encrypt=true;trustServerCertificate=true","sa","123456");
            return cn;
         } catch (SQLException e) {
            System.out.println("Error en la conexion local " + e);
        }
        return null;
    }
    
}
