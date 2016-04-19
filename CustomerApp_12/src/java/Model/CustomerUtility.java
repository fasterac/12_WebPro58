package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Customer;

public class CustomerUtility {
    
    Connection conn;
    Statement stmt;
    
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://database.it.kmitl.ac.th:3306/it_12", "it_12", "ust4Ht3Q");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerUtility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int insertData(Customer cus){
        String sqlCmd = 
               "insert into mvccustomer values('" + cus.getFirstName() + 
               "','" + cus.getLastName() + 
                "','" + cus.getCompany()+ 
               "','" + cus.getMobile() + 
               "','" + cus.getEmail() + 
               "'," + cus.getAddr() + "');";
       
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
