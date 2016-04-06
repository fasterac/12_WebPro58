
package Utility;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;

import javax.sql.DataSource;


public class DataConnector {
    Statement stmt;
    Connection conn;
    ServletContext ctx;
    
    public DataConnector(){
        try {
            this.conn = getDB12().getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    
    public String execute(String sql, String colName){
        //get only one value
        ResultSet rs = null;
        String returnerValue = "";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            returnerValue = rs.getString(colName);
        } catch (SQLException ex) {
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return returnerValue;
    }

    private DataSource getDB12() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/DB12");
    }
}
