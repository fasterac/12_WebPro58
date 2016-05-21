package factory;

import model.User;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<User> all() {
        try {
            sql = "SELECT * FROM user";
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
            sql = "SELECT * FROM user WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            result = statement.executeQuery();
            
            if(result.next()) {
                return buildObject(result);
            } else {
                throw new RuntimeException("No user at id = " + id);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public User findByUsername(String username) {
        try {
            sql = "SELECT * FROM user WHERE username = ?";
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

    @Override
    public User update(User model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    User buildObject(ResultSet result) throws SQLException {
        return setObject(new User(), result);
    }

    User setObject(User model, ResultSet result) throws SQLException {
        model.setId(result.getInt("user.id"));
        model.setUsername(result.getString("user.username"));
        model.setPassword(result.getString("user.password"));
        model.setPname_th(result.getString("user.pname_th"));
        model.setFname_th(result.getString("user.fname_th"));
        model.setLname_th(result.getString("user.lname_th"));
        model.setPname_en(result.getString("user.pname_en"));
        model.setFname_en(result.getString("user.fname_en"));
        model.setLname_en(result.getString("user.lname_en"));
        model.setEmail(result.getString("user.email"));
        model.setMobile(result.getString("user.mobile"));
        model.setRole(User.Role.valueOf(result.getString("user.role")));
        model.setType(User.Type.valueOf(result.getString("user.type")));

        return model;
    }
    
}
