package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerUtility {
    
    Connection conn;
    Statement stmt;
    
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://database.it.kmitl.ac.th:3306/it_12";
            String user = "it_12";
            String pwd = "ust4Ht3Q";
            conn = DriverManager.getConnection(url,user,pwd);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerUtility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int insertData(String firstName, String lastName, String mobile, String email, String addr){
        System.out.println("insert into CustomerMVC values('" + firstName + 
               "','" + lastName + 
               "','" + mobile + 
               "','" + email + 
               "'," + addr + ")");
        String sqlCmd = 
               "insert into CustomerMVC values('" + firstName + 
               "','" + lastName + 
               "','" + mobile + 
               "','" + email + 
               "'," + addr + ")";
       
        int num = 0;
        try {
            num = stmt.executeUpdate(sqlCmd);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }
    
    /*public void getData(){
        try {
            String sql = "SELECT * FROM CustomerMVC WHERE firstname = ? AND lastname = ?";
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);

            return queryCustomer(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
