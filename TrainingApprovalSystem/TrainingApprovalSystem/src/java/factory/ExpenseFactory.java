package factory;

import model.Expense;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExpenseFactory extends BaseFactory<Expense> {

    public ExpenseFactory(Connection connection) {
        super(connection);
    }
    
    @Override
    public Expense create(Expense model) {
        try {
            sql = "INSERT INTO expense VALUES(?, ?, ?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, model.getForm_id());
            statement.setDouble(2, model.getRegister_cost());
            statement.setInt(3, model.getRoom_night_amount());
            statement.setDouble(4, model.getRoom_night_each());
            statement.setInt(5, model.getDay_amount());
            statement.setDouble(6, model.getDay_cost_each());
            statement.setDouble(7, model.getTravel_cost());
            
            statement.executeUpdate();
            
            return model;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Expense> all() {
        try {
            sql = "SELECT * FROM expense";
            statement = connection.prepareStatement(sql);

            result = statement.executeQuery();

            ArrayList<Expense> expenses = new ArrayList<>();
            if(result.next()) {
                expenses.add(buildObject(result));
            }
            return expenses;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Expense find(int id) {
        try {
            sql = "SELECT * FROM expense WHERE form_id = ?";
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
    public Expense update(Expense model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    Expense buildObject(ResultSet result) throws SQLException {
        Expense model = new Expense();
        
        model.setForm_id(result.getInt("expense.form_id"));
        model.setRegister_cost(result.getDouble("expense.register_cost"));
        model.setRoom_night_amount(result.getInt("expense.room_night_amount"));
        model.setRoom_night_each(result.getDouble("expense.room_night_each"));
        model.setDay_amount(result.getInt("expense.day_amount"));
        model.setDay_cost_each(result.getDouble("expense.day_cost_each"));
        model.setTravel_cost(result.getDouble("expense.travel_cost"));
        
        return model;
    }
    
}
