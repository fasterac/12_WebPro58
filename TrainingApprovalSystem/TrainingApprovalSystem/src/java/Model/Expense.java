package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import Utility.DataConnector;

public class Expense {

    private double reg_expense = 0, inter_expense = 0,  acc_each = 0, acc_sum = 0, 
             allo_each = 0, allo_sum = 0, travelling = 0, sum_expense = 0;
    private int allo_day = 0, acc_night = 0;
    private int expense_id, form_id;

    public Expense() {
    }

    public void createExpense(int form_id, double reg_expense, double inter_expense,
            int acc_night, double acc_each, double acc_sun,
            int allo_day, double allo_each, double allo_sum, double travelling) {
        this.reg_expense = reg_expense;
        this.inter_expense = inter_expense;
        this.acc_night = acc_night;
        this.acc_each = acc_each;
        this.acc_sum = acc_sun;
        this.allo_day = allo_day;
        this.allo_each = allo_each;
        this.allo_sum = allo_sum;
        this.travelling = travelling;
        this.sum_expense = calculateSumExpense();
        this.expense_id = getLastExpenseId()+ 1;
        this.form_id = form_id;
    }

    public Boolean insertExpense() {
        Boolean updateResult = false;
        DataConnector connector = new DataConnector();
        String sql =  "INSERT INTO expense (form_id, reg_expense, inter_expense, "
                        + "acc_night, acc_each, acc_sum, allo_day, allo_each, allo_sum, travelling, sum_expense) VALUES('"
                + this.form_id + "','" + this.reg_expense + "','" + this.inter_expense
                + "','" + this.acc_night + "','" + this.acc_each + "','" + this.acc_sum
                + "','" + this.allo_day + "','" + this.allo_each + "','" + this.allo_sum
                + "','" + this.travelling + "','" + this.sum_expense + "');";
        if(connector.update(sql) == 1) {
            System.out.println("insert Expense sussecc");
            updateResult = true;
        }
        connector.closeConnection();
        return updateResult;
    }

    public void callExpense(int form_id) {
        DataConnector connector = new DataConnector();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM expense WHERE form_id = '" + form_id + "';";
            rs = connector.execute(sql);
            rs.next();
            this.form_id = form_id;
            this.expense_id = rs.getInt("expense_id");
            this.reg_expense = rs.getInt("reg_expense");
            this.inter_expense = rs.getInt("inter_expense");
            this.acc_night = rs.getInt("acc_night");
            this.acc_each = rs.getDouble("acc_each");
            this.acc_sum = rs.getDouble("acc_sum");
            this.allo_day = rs.getInt("allo_day");
            this.allo_each = rs.getDouble("allo_each");
            this.allo_sum = rs.getDouble("allo_sum");
            this.travelling = rs.getDouble("travelling");            
            this.sum_expense = rs.getDouble("sum_expense");
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connector.closeConnection();
    }

    private double calculateSumExpense() {
        return this.reg_expense + this.acc_sum + this.allo_sum + this.travelling + this.inter_expense;
    }
    
    public int getLastExpenseId(){
        DataConnector connector = new DataConnector();
        int lastID = 0;
        try{
            String sql = "SELECT expense_id FROM expense";
            ResultSet rs = connector.execute(sql);
            while(rs.next()){
                lastID = rs.getInt(1);
            }
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        connector.closeConnection();
        return lastID;
    }

    public double getSum_expense() {
        return sum_expense;
    }

    public double getReg_expense() {
        return reg_expense;
    }

    public double getInter_expense() {
        return inter_expense;
    }

    public double getAcc_each() {
        return acc_each;
    }

    public double getAcc_sum() {
        return acc_sum;
    }

    public double getAllo_each() {
        return allo_each;
    }

    public double getAllo_sum() {
        return allo_sum;
    }

    public double gettravelling() {
        return travelling;
    }

    public int getAllo_day() {
        return allo_day;
    }

    public int getAcc_night() {
        return acc_night;
    }

    public int getExpense_id() {
        return expense_id;
    }

    public int getForm_id() {
        return form_id;
    }
    
    

}
