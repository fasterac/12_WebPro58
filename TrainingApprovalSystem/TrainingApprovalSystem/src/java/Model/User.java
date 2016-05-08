package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        connector.closeConnection();
    }
    
    public void callUserFromUsername(String username){ //used in login process servlet
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
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        connector.closeConnection();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getPosition(){
        DataConnector connector = new DataConnector();
        String posi = connector.executeString("SELECT position FROM it_12.teacher WHERE teacher_id = '"+ this.user_id + "';", "position");
        connector.closeConnection();
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

    public String getRole() {
        return role;
    }

    public String getType() {
        return type;
    }
    
    
    
}
