package factory;

import Model.Expense;
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
        model.setSum_expense(model.getReg_expense() + model.getAcc_sum() + model.getAllo_sum() + model.gettravelling() + model.getInter_expense());
        
        try {
            sql = "INSERT INTO expense VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, model.getForm_id());
            statement.setDouble(2, model.getReg_expense());
            statement.setDouble(3, model.getInter_expense());
            statement.setInt(4, model.getAcc_night());
            statement.setDouble(5, model.getAcc_each());
            statement.setDouble(6, model.getAcc_sum());
            statement.setInt(7, model.getAllo_day());
            statement.setDouble(8, model.getAllo_each());
            statement.setDouble(9, model.getAllo_sum());
            statement.setDouble(10, model.gettravelling());
            statement.setDouble(11, model.getSum_expense());
            
            model.setExpense_id(statement.executeUpdate());
            
            return model;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Expense> all() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Expense find(int id) {
        try {
            sql = "SELECT * FROM expense WHERE expense_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            result = statement.executeQuery();
            if(result.next()) {
                return buildObject(result);
            } else {
                throw new RuntimeException("No expense at expense_id = " + id);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } 
        return null;
    }
    
    public Expense findByFormID(int form_id) {
        try {
            sql = "SELECT * FROM expense WHERE form_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, form_id);
            
            result = statement.executeQuery();
            if(result.next()) {
                return buildObject(result);
            } else {
                throw new RuntimeException("No expense at form_id = " + form_id);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } 
        return null;
    }

    @Override
    public Expense update(Expense model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Expense remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Expense buildObject(ResultSet result) throws SQLException {
        Expense model = new Expense();
        
        model.setForm_id(result.getInt("expense.form_id"));
        model.setExpense_id(result.getInt("expense.expense_id"));
        model.setReg_expense(result.getDouble("expense.reg_expense"));
        model.setInter_expense(result.getDouble("expense.inter_expense"));
        model.setAcc_night(result.getInt("expense.acc_night"));
        model.setAcc_each(result.getDouble("expense.acc_each"));
        model.setAcc_sum(result.getDouble("expense.acc_sum"));
        model.setAllo_day(result.getInt("expense.allo_day"));
        model.setAllo_each(result.getDouble("expense.allo_each"));
        model.setAllo_sum(result.getDouble("expense.allo_sum"));
        model.setTravelling(result.getDouble("expense.travelling"));
        model.setSum_expense(result.getDouble("expense.sum_expense"));
        
        return model;
    }
    
}
