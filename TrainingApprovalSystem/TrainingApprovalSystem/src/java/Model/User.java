package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Utility.DataConnector;


public class User {
    
    private int user_id;
    private String firstname, lastname, username, password, role, type;
    //private DataConnector connector = new DataConnector();
    
    public void createUser(int user_id, String firstname, String lastname, String username, String password,
    String role, String type){
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.type = type;
    }
    
    public void callUser(String username){
        DataConnector connector = new DataConnector();
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM user WHERE username = '"+ username + "';";
            rs = connector.execute(sql);
            rs.next();
            this.user_id = rs.getInt("user_id");
            this.firstname = rs.getString("firstname");
            this.lastname = rs.getString("lastname");
            this.username = rs.getString("username");
            this.password = rs.getString("password");
            this.role = rs.getString("role");
            this.type = rs.getString("type");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public String getPosition(){
        DataConnector connector = new DataConnector();
        String posi = connector.execute("SELECT position FROM it_12.teacher WHERE teacher_id = '"+ this.user_id + "';", "position");
        return posi;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
    
    
}
