
package utility;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import javax.sql.DataSource;


public class DataConnector {
    
    private DataSource dataSource;
    
    public DataConnector(){
        try {
            Context c = new InitialContext();
            dataSource = (DataSource) c.lookup("java:/comp/env/DB12");
        } catch (Exception ex) {
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Connection getDBConnection(HttpServletRequest request) {
        return (Connection) request.getAttribute("db.connection");
    }
    
    public static void setDBConenction(HttpServletRequest request, Connection connection) {
        request.setAttribute("db.connection", connection);
    }

    public static Connection getConnectionForTest() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/it_12", "root", "mysql");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
