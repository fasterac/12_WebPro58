package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import Utility.DataConnector;

public class Expense {

    private double reg_expense, inter_expense, acc_night, acc_each, acc_sum, allo_day, allo_each, allo_sum, traveling, sum_expense;
    private int expense_id, form_id;
    private DataConnector connector;

    public Expense() {
    }

    public void createExpense(int expense_id, int form_id, double reg_expense, double inter_expense,
            double acc_night, double acc_each, double acc_sun,
            double allo_day, double allo_each, double allo_sum, double traveling, double sum_expense) {
        this.reg_expense = reg_expense;
        this.inter_expense = inter_expense;
        this.acc_night = acc_night;
        this.acc_each = acc_each;
        this.acc_sum = acc_sun;
        this.allo_day = allo_day;
        this.allo_each = allo_each;
        this.allo_sum = allo_sum;
        this.traveling = traveling;
        this.sum_expense = calculateSumExpense();
        this.expense_id = getLastExpenseId()+ 1;
        this.form_id = form_id;
    }

    public void insertExpense() {
        String sql = "INSERT INTO form VALUES("
                + (getLastExpenseId()+ 1) +"," + this.form_id + ",'" + this.reg_expense + "','" + this.inter_expense
                + "','" + this.acc_night + "','" + this.acc_each + "','" + this.acc_sum
                + "','" + this.allo_day + "','" + this.allo_each + "'," + this.allo_sum
                + "','" + this.traveling + "','" + this.sum_expense + "';";
        if(connector.update(sql) == 0) {
            System.out.println("insert sussecc");
        }

    }

    public void callExpense(int form_id) {
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM form WHERE form_id = '" + form_id + "';";
            rs = connector.execute(sql);
            rs.next();
            this.form_id = form_id;
            this.expense_id = rs.getInt("expense_id");
            this.reg_expense = rs.getInt("reg_expense");
            this.inter_expense = rs.getInt("inter_expense");
            this.acc_night = rs.getDouble("acc_night");
            this.acc_each = rs.getDouble("acc_each");
            this.acc_sum = rs.getDouble("acc_sun");
            this.allo_day = rs.getDouble("allo_day");
            this.allo_each = rs.getDouble("allo_each");
            this.allo_sum = rs.getDouble("allo_sum");
            this.traveling = rs.getDouble("traveling");            
            this.sum_expense = rs.getDouble("inter_id");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private double calculateSumExpense() {
        return this.reg_expense + this.acc_sum + this.allo_sum + this.traveling + this.inter_expense;
    }
    
    public int getLastExpenseId(){
        int lastID = 0;
        try{
            String sql = "SELECT form_id FROM form";
            ResultSet rs = connector.execute(sql);
            while(rs.next()){
                lastID = rs.getInt(1);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lastID;
    }

}
