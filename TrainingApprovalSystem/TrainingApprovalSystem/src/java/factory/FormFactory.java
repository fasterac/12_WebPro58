package factory;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class FormFactory extends BaseFactory<Form> {

    public FormFactory(Connection connection) {
        super(connection);
    }

    @Override
    public Form create(Form model) {
        try {
            sql = "INSERT INTO form VALUES (0, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, 'PENDING', ?, NULL)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, model.getUser().getId());
            statement.setString(2, model.getCourse_name());
            statement.setString(3, model.getOrganizer_name());
            statement.setString(4, model.getLocation_name());
            statement.setDate(5, new Date(model.getStart_date().getTime()));
            statement.setDate(6, new Date(model.getStart_date().getTime()));
            statement.setBoolean(7, model.isUse_expense());
            
            statement.executeUpdate();

            result = statement.getGeneratedKeys();
            if(result.next()) {
                model.setId(result.getInt(1));

                sql = "INSERT INTO course_detail VALUES (?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, model.getId());
                statement.setString(2, model.getCourse_file_path());

                statement.executeUpdate();
            }

            if(model.getExpense() != null) {
                Expense expense = model.getExpense();
                expense.setForm_id(model.getId());

                model.setExpense(new ExpenseFactory(connection).create(expense));
            }

            if(model.getImprovement() != null) {
                Improvement improvement = model.getImprovement();
                improvement.setForm_id(model.getId());

                model.setImprovement(new ImprovementFactory(connection).create(improvement));
            }
            
            return model;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Form> all() {
        try {
            sql = "SELECT * FROM form\n" +
                    "  LEFT JOIN course_detail ON (form.id = course_detail.form_id)\n" +
                    "  LEFT JOIN expense ON (form.id = expense.form_id)\n" +
                    "  LEFT JOIN improvement ON (form.id = improvement.form_id)\n" +
                    "  LEFT JOIN report ON (form.id = report.form_id)\n" +
                    "  LEFT JOIN user user ON (user.id = form.user_id)\n" +
                    "  LEFT JOIN user approver ON (approver.id = form.approver_id)";
            
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            
            ArrayList<Form> forms = new ArrayList<>();
            while(result.next()) {
                forms.add(buildObject(result));
            }
            return forms;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Form> findAllByUserID(int user_id) {
        try {
            sql = "SELECT * FROM form\n" +
                    "  LEFT JOIN course_detail ON (form.id = course_detail.form_id)\n" +
                    "  LEFT JOIN expense ON (form.id = expense.form_id)\n" +
                    "  LEFT JOIN improvement ON (form.id = improvement.form_id)\n" +
                    "  LEFT JOIN report ON (form.id = report.form_id)\n" +
                    "  LEFT JOIN user user ON (user.id = form.user_id)\n" +
                    "  LEFT JOIN user approver ON (approver.id = form.approver_id)\n" +
                    "  WHERE form.user_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            result = statement.executeQuery();

            ArrayList<Form> forms = new ArrayList<>();
            while(result.next()) {
                forms.add(buildObject(result));
            }
            return forms;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Form find(int id) {
        try {
            sql = "SELECT * FROM form\n" +
                    "  LEFT JOIN course_detail ON (form.id = course_detail.form_id)\n" +
                    "  LEFT JOIN expense ON (form.id = expense.form_id)\n" +
                    "  LEFT JOIN improvement ON (form.id = improvement.form_id)\n" +
                    "  LEFT JOIN report ON (form.id = report.form_id)\n" +
                    "  LEFT JOIN user user ON (user.id = form.user_id)\n" +
                    "  LEFT JOIN user approver ON (approver.id = form.approver_id)\n" +
                    "  WHERE form.id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            result = statement.executeQuery();
            if(result.next()) {
                return buildObject(result);
            } else {
                throw new RuntimeException("No form at form_id = " + id);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Form update(Form model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Form updateStatus(Form model, User user, Form.Status status) {
        try {
            sql = "UPDATE form SET status = ?, approver_id = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(status));
            if(user != null) statement.setInt(2, user.getId());
            else statement.setNull(2, Types.INTEGER);
            statement.setInt(3, model.getId());
            
            if(statement.executeUpdate() > 0) {
                model.setStatus(status);
                return model;
            } else {
                throw new RuntimeException("Something wrong update form at form_id = " + model.getId());
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try {
            sql = "DELETE FROM form WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    Form buildObject(ResultSet result) throws SQLException {
        Form model = new Form();
        
        model.setId(result.getInt("form.id"));
        model.setForm_date(result.getDate("form.form_date"));
        model.setCourse_name(result.getString("form.course_name"));
        model.setOrganizer_name(result.getString("form.organizer_name"));
        model.setLocation_name(result.getString("form.location_name"));
        model.setStart_date(result.getDate("form.start_date"));
        model.setEnd_date(result.getDate("form.end_date"));
        model.setStatus(Form.Status.valueOf(result.getString("form.status")));
        model.setUse_expense(result.getBoolean("form.use_expense"));

        model.setCourse_file_path(result.getString("course_detail.file_path"));

        model.setExpense(new ExpenseFactory(connection).buildObject(result));
        model.setImprovement(new ImprovementFactory(connection).buildObject(result));
        if(result.getString("report.form_id") != null && !result.getString("report.form_id").equals("")) {
            model.setReport(new ReportFactory(connection).buildObject(result));
        }

        UserFactory userFactory = new UserFactory(connection);
        model.setUser(userFactory.buildObject(result));
        if(result.getInt("approver_id") > 0) {
            model.setApprover(userFactory.buildObject(result, "approver"));
        }

        return model;
    }
    
}
