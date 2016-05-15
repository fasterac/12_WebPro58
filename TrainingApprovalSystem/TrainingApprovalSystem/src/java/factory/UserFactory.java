package factory;

import Model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserFactory extends BaseFactory<User> {

    public UserFactory(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> all() {
        try {
            sql = "SELECT * FROM user ON (user.user_id = form.user_id) "
                    + "JOIN teacher ON (user.user_id = teacher.teacher_id) ";
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            
            ArrayList<User> users = new ArrayList<>();
            while(result.next()) {
                users.add(buildObject(result));
            }
            return users;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User find(int id) {
        try {
            sql = "SELECT * FROM user ON (user.user_id = form.user_id) "
                    + "JOIN teacher ON (user.user_id = teacher.teacher_id) "
                    + "WHERE user_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            result = statement.executeQuery();
            
            if(result.next()) {
                return buildObject(result);
            } else {
                throw new RuntimeException("No user at user_id = " + id);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public User findByUsername(String username) {
        try {
            sql = "SELECT * FROM user "
                    + "LEFT JOIN teacher ON (user.user_id = teacher.teacher_id) "
                    + "WHERE user.username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            
            result = statement.executeQuery();
            
            if(result.next()) {
                return buildObject(result);
            } else {
                throw new RuntimeException("No user at username = " + username);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public User findByTeacherId(int teacher_id) {
        try {
            sql = "SELECT * FROM user ON (user.user_id = form.user_id) "
                    + "LEFT JOIN teacher ON (user.user_id = teacher.teacher_id) "
                    + "WHERE teacher_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, teacher_id);
            
            result = statement.executeQuery();
            
            if(result.next()) {
                return buildObject(result);
            } else {
                throw new RuntimeException("No user at teacher_id = " + teacher_id);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    User buildObject(ResultSet result) throws SQLException {
        User model = new User();
        
        model.setUser_id(result.getInt("user.user_id"));
        model.setFirstname(result.getString("user.firstname"));
        model.setLastname(result.getString("user.lastname"));
        model.setUsername(result.getString("user.username"));
        model.setPassword(result.getString("user.password"));
        model.setPosition(result.getString("teacher.position"));
        model.setRole(result.getString("user.role"));
        model.setType(result.getString("user.type"));
        
        return model;
    }
    
}
