package Model;

import Utility.DataConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class History {

    public History() {
        
    }
    
    
    
    public ArrayList<String> getHistory(int user_id, String beforeDate){
        DataConnector connector = new DataConnector();
        ArrayList<String> history = new ArrayList<>();
        Expense expense = new Expense();
        Report report = new Report();
        int formCounter = 0;
        ResultSet rs = connector.execute("SELECT form_id, course, start_date, end_date FROM form WHERE user_id ='" + user_id 
                + "' AND form_date <'"+ beforeDate +"' AND form_date > '2015-10-01';");

        try {
            while(rs.next()){
                formCounter += 1;
                expense.callExpense(rs.getInt("form_id"));
                report.callReport(rs.getInt("form_id"));
                history.add(formCounter + "");
                history.add(rs.getString("course"));
                history.add(rs.getString("start_date") + " - " + rs.getString("end_date"));
                history.add(expense.getSum_expense()+"");
                history.add(checknull(report.getReport_date()));
                history.add(checknull(report.getLecture_date()));
//                System.out.println(formCounter + " " +rs.getString("course") + rs.getString("start_date") + expense.getSum_expense()+" "
//                +report.getReport_date() + report.getLecture_date() );
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.closeConnection();
        return history;
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
