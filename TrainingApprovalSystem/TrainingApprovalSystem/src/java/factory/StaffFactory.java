package factory;

import model.Staff;
import model.Work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffFactory extends BaseFactory<Staff> {

    public StaffFactory(Connection connection) {
        super(connection);
    }

    @Override
    public Staff create(Staff model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Staff> all() {
        try {
            sql = "SELECT * FROM staff " +
                    "JOIN user ON (staff.user_id = user.id)" +
                    "JOIN work_section ON (staff.work_section_id = work_section.id)";
            statement = connection.prepareStatement(sql);

            result = statement.executeQuery();

            ArrayList<Staff> staffs = new ArrayList<>();
            if(result.next()) {
                staffs.add(buildObject(result));
            }
            return staffs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Staff find(int id) {
        try {
            sql = "SELECT * FROM staff " +
                    "JOIN user ON (staff.user_id = user.id)" +
                    "JOIN work_section ON (staff.work_section_id = work_section.id) " +
                    "WHERE staff.id = ?";
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

    public Object findByUserID(int user_id) {
        try {
            sql = "SELECT * FROM staff " +
                    "JOIN user ON (staff.user_id = user.id)" +
                    "JOIN work_section ON (staff.work_section_id = work_section.id) " +
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
    public Staff update(Staff model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    Staff buildObject(ResultSet result) throws SQLException {
        Staff model = new Staff();

        Work.Section section = new Work.Section();
        section.setId(result.getInt("work_section.id"));
        section.setName_th(result.getString("work_section.name_th"));
        section.setName_en(result.getString("work_section.name_en"));

        model.setWork_section(section);

        new UserFactory(connection).setObject(model, result, "user");

        return model;
    }
}
