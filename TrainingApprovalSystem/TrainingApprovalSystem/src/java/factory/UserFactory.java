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
        return setObject(new User(), result, "user");
    }

    User setObject(User model, ResultSet result, String uniqueName) throws SQLException {
        model.setId(result.getInt(uniqueName + ".id"));
        model.setUsername(result.getString(uniqueName + ".username"));
        model.setPassword(result.getString(uniqueName + ".password"));
        model.setPname_th(result.getString(uniqueName + ".pname_th"));
        model.setFname_th(result.getString(uniqueName + ".fname_th"));
        model.setLname_th(result.getString(uniqueName + ".lname_th"));
        model.setPname_en(result.getString(uniqueName + ".pname_en"));
        model.setFname_en(result.getString(uniqueName + ".fname_en"));
        model.setLname_en(result.getString(uniqueName + ".lname_en"));
        model.setEmail(result.getString(uniqueName + ".email"));
        model.setMobile(result.getString(uniqueName + ".mobile"));
        model.setRole(User.Role.valueOf(result.getString(uniqueName + ".role")));
        model.setType(User.Type.valueOf(result.getString(uniqueName + ".type")));

        return model;
    }

    User buildObject(ResultSet result, String uniqueName) throws SQLException {
        return setObject(new User(), result, uniqueName);
    }
    
}
