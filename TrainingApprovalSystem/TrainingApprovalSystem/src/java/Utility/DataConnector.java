
package Utility;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import javax.sql.DataSource;


public class DataConnector {
    Statement stmt;
    Connection conn;
    ServletContext ctx;
    
    private DataSource dataSource;
    
    public DataConnector(){
        try {
            Context c = new InitialContext();
            dataSource = (DataSource) c.lookup("java:/comp/env/DB12");
            conn = getConnection();
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
    
    public int update(String sql){        
        int updateResult = 0;
        try {
            stmt = conn.createStatement();
            updateResult = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResult;
    }
    
    public void updateQuery(String sql){        
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet execute(String sql){
        //getvarier value
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public String executeString(String sql, String colName){
        //get only one value
        ResultSet rs = null;
        String returnerValue = "";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            returnerValue = rs.getString(colName);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return returnerValue;
    }
    
    public void closeConnection() {
        if(this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
