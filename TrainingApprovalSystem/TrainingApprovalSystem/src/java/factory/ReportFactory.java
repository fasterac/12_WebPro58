package factory;

import model.Report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportFactory extends BaseFactory<Report> {

    public ReportFactory(Connection connection) {
        super(connection);
    }

    @Override
    public Report create(Report model) {
        try {
            sql = "INSERT INTO report VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, model.getForm_id());
            statement.setDate(2, model.getReport_date());
            statement.setString(3, model.getFile_path());

            statement.executeUpdate();

            return model;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Report> all() {
        try {
            sql = "SELECT * FROM report";
            statement = connection.prepareStatement(sql);

            result = statement.executeQuery();

            ArrayList<Report> improvements = new ArrayList<>();
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
    public Report find(int id) {
        try {
            sql = "SELECT * FROM report WHERE form_id = ?";
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
    public Report update(Report model) {
        try {
            sql = "UPDATE report " +
                    "SET report_date = ?, file_path = ? " +
                    "WHERE form_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, model.getReport_date());
            statement.setString(2, model.getFile_path());
            statement.setInt(3, model.getForm_id());

            statement.executeUpdate();

            return model;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    Report buildObject(ResultSet result) throws SQLException {
        Report model = new Report();

        model.setForm_id(result.getInt("form_id"));
        model.setReport_date(result.getDate("report_date"));
        model.setFile_path(result.getString("file_path"));

        return model;
    }

}
