package Model;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class AccountUtilities {
    public static Connection conn;
    public static Statement stmt;
    public static PreparedStatement pstmt;

    public void init(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://database.it.kmitl.ac.th:3306/it_12",
                            "it_12", "ust4Ht3Q");
            stmt = conn.createStatement();
            System.out.println("---Connected---");
                    
        } catch (SQLException sqle) {
            System.out.println("" + sqle);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public ResultSet getAccount(String user, String pass){
        String sql = "SELECT * FROM account WHERE username = '" + user + "' AND password = '" + pass + "'";
        ResultSet rs = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            rs =  pstmt.executeQuery();
                       
        } catch (SQLException ex) {
            Logger.getLogger(AccountUtilities.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
         return rs;
        
    }
}
