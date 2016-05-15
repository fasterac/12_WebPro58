package Utility;

import Model.Report;
import factory.ExpenseFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryUtility {

    private Connection connection;
    
    public HistoryUtility(Connection connection) {
        this.connection = connection;
    }
    
    public ArrayList<String> getHistory(int user_id, String beforeDate){
        try {
            int formCounter = 1;
            
            ExpenseFactory expenseFactory = new ExpenseFactory(connection);
            
            Report report = new Report();
            
            ResultSet result = connection.createStatement().executeQuery("SELECT form_id, course, start_date, end_date FROM form WHERE user_id ='" + user_id +
                    "' AND form_date <'" + beforeDate + "' AND form_date > '2015-10-01';");
            
            ArrayList<String> history = new ArrayList<>();
            
            
            while(result.next()){
                report.callReport(result.getInt("form_id"));
                history.add("" + formCounter++);
                history.add(result.getString("course"));
                history.add(result.getString("start_date") + " - " + result.getString("end_date"));
                history.add(expenseFactory.findByFormID(result.getInt("form_id")).getSum_expense()+"");
                history.add(checknull(report.getReport_date()));
                history.add(checknull(report.getLecture_date()));
            }
            
            return history;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    private String checknull(String strInput){
        try{
            if(!(strInput == null) || !strInput.equals("null")){
            return strInput;
        }
        }
        catch(NullPointerException nuex){
            return "-";
        }
        return "-";
    }
    
}
