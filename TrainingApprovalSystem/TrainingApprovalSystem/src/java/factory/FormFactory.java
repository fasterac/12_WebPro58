package factory;

import Model.Form;
import Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FormFactory extends BaseFactory<Form> {

    public FormFactory(Connection connection) {
        super(connection);
    }

    @Override
    public Form create(Form model) {
        try {
            sql = "INSERT INTO form VALUES (0, ?, 0, '', ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, model.getUser_id());
            statement.setString(2, model.getCourse());
            statement.setString(3, model.getOrganizer());
            statement.setString(4, model.getLocation());
            statement.setDate(5, (Date) new SimpleDateFormat("yyyy-MM-dd").parse(model.getStart_date()));
            statement.setDate(6, (Date) new SimpleDateFormat("yyyy-MM-dd").parse(model.getEnd_date()));
            statement.setInt(7, model.getSum_date());
            statement.setInt(8, model.getInter_id());
            
            model.setForm_id(statement.executeUpdate());
            
            return find(model.getForm_id());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Form> all() {
        try {
            sql = "SELECT * FROM form "
                    + "JOIN expense ON (form.form_id = expense.form_id) "
                    + "LEFT JOIN user ON (user.user_id = form.user_id) "
                    + "LEFT JOIN teacher ON (user.user_id = teacher.teacher_id) "
                    + "LEFT JOIN status ON (form.status_id = status.status_id) "
                    + "ORDER BY form.form_id ASC;";
            
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

    @Override
    public Form find(int id) {
        try {
            sql = "SELECT * FROM form "
                    + "LEFT JOIN expense ON (form.form_id = expense.form_id) "
                    + "LEFT JOIN user ON (user.user_id = form.user_id) "
                    + "LEFT JOIN teacher ON (user.user_id = teacher.teacher_id) "
                    + "LEFT JOIN status ON (form.status_id = status.status_id) "
                    + "WHERE form.form_id = ?;";
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Form updateStatus(Form model, int status_id) {
        try {
            sql = "UPDATE form SET status_id = ? WHERE form_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, status_id);
            statement.setInt(2, model.getForm_id());
            
            if(statement.executeUpdate() > 0) {
                model.setStatus_id(status_id);
                return model;
            } else {
                throw new RuntimeException("Something worng update form at form_id = " + model.getForm_id());
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Form remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Form buildObject(ResultSet result) throws SQLException {
        Form model = new Form();
        
        model.setForm_id(result.getInt("form.form_id"));
        model.setStatus_id(result.getInt("form.status_id"));
        model.setUser_id(result.getInt("form.user_id"));
        model.setForm_date(result.getString("form.form_date"));
        model.setCourse(result.getString("form.course"));
        model.setOrganizer(result.getString("form.organizer"));
        model.setLocation(result.getString("form.location"));
        model.setStart_date(result.getString("form.start_date"));
        model.setEnd_date(result.getString("form.end_date"));
        model.setSum_date(result.getInt("form.sum_date"));
        model.setInter_id(result.getInt("form.inter_id"));
        
        model.setExpense(new ExpenseFactory(connection).buildObject(result));
        model.setUser(new UserFactory(connection).buildObject(result));
        
        model.setStatus(Form.Status.valueOf(result.getInt("form.status_id")));

        return model;
    }
    
}
