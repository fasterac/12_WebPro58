package factory;

import model.Teacher;
import model.Work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherFactory extends BaseFactory<Teacher> {

    public TeacherFactory(Connection connection) {
        super(connection);
    }

    @Override
    public Teacher create(Teacher model) {
        return null;
    }

    @Override
    public ArrayList<Teacher> all() {
        try {
            sql = "SELECT * FROM teacher " +
                    "JOIN user ON (teacher.user_id = user.id)";
            statement = connection.prepareStatement(sql);

            result = statement.executeQuery();

            ArrayList<Teacher> teachers = new ArrayList<>();
            while(result.next()) {
                teachers.add(buildObject(result));
            }
            return teachers;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Teacher find(int id) {
        try {
            sql = "SELECT * FROM teacher " +
                    "JOIN user ON (teacher.user_id = user.id) " +
                    "WHERE teacher.id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            result = statement.executeQuery();
            if(result.next()) {
                return buildObject(result);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Teacher findByUserID(int user_id) {
        try {
            sql = "SELECT * FROM teacher " +
                    "JOIN user ON (teacher.user_id = user.id) " +
                    "WHERE user.id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);

            result = statement.executeQuery();
            if(result.next()) {
                return buildObject(result);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Teacher update(Teacher model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    Teacher buildObject(ResultSet result) throws SQLException {
        Teacher model = new Teacher();

        model.setTeacher_id(result.getInt("teacher.id"));
        model.setStatus(Work.Status.valueOf(result.getString("teacher.status")));
        model.setPosition(result.getString("teacher.position"));

        new UserFactory(connection).setObject(model, result, "user");

        return model;
    }

}
