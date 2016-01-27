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
        String sql = "INSERT INTO CustomerMVC VALUES(0, ?, ?, ?, ?, ? ,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, company);
        preparedStatement.setString(4, mobile);
        preparedStatement.setString(5, email);
        preparedStatement.setString(6, addr);

        preparedStatement.executeUpdate(); // execute the sql script (update)
        preparedStatement.close(); // close statement (optional for security)


        ResultSet resultSet = preparedStatement.executeQuery(); // execute the sql script (query - search)
        resultSet.last(); // set cursor to the last row of result

        return resultSet.getInt("id");
    }
    
    
    
    public ResultSet getData(){
        String username = null;
        String sql = "SELECT * FROM CustomerMVC";
        ResultSet rs = null;
        
        try {
            
            pstmt = conn.prepareStatement(sql);
            rs =  pstmt.executeQuery();
                       
        } catch (SQLException ex) {
            Logger.getLogger(CustomerUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;
        
    }
}
