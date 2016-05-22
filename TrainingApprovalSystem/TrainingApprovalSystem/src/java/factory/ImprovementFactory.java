package factory;

import model.Improvement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImprovementFactory extends BaseFactory<Improvement> {

    public ImprovementFactory(Connection connection) {
        super(connection);
    }

    @Override
    public Improvement create(Improvement model) {
        try {
            sql = "INSERT INTO improvement VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, model.getForm_id());
            statement.setString(2, model.getDetail());
            statement.setInt(3, model.getDate_duration());
            statement.setInt(4, model.getReport_sent_duration());

            statement.executeUpdate();

            return model;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Improvement> all() {
        try {
            sql = "SELECT * FROM improvement";
            statement = connection.prepareStatement(sql);

            result = statement.executeQuery();

            ArrayList<Improvement> improvements = new ArrayList<>();
            while(result.next()) {
                improvements.add(buildObject(result));
            }
            return improvements;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Improvement find(int id) {
        try {
            sql = "SELECT * FROM improvement WHERE form_id = ?";
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

    @Override
    public Improvement update(Improvement model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    Improvement buildObject(ResultSet result) throws SQLException {
        Improvement model = new Improvement();

        model.setForm_id(result.getInt("improvement.form_id"));
        model.setDetail(result.getString("improvement.detail"));
        model.setDate_duration(result.getInt("improvement.date_duration"));
        model.setReport_sent_duration(result.getInt("improvement.report_sent_duration"));

        return model;
    }

}
