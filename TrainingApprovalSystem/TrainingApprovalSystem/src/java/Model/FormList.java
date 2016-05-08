package Model;

import Utility.HistoryUtility;
import Utility.DataConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//this class use at admin main page
//at(form_id)    byParticiper    course    dateDuration    organizer,location    sumExpense    sumHour    status
public class FormList {
    
    public FormList() {
        
    }

    public ArrayList<String> getAllFormList(){
        DataConnector connector = new DataConnector();
        ArrayList<String> formList = new ArrayList<>();
        int formCounter = 0;
        ResultSet rs = connector.execute("SELECT form.form_id, form.course, form.organizer, form.location, form.sum_date,"
                + "form.start_date, form.end_date, expense.sum_expense, user.firstname, user.lastname, status.status_name "
                + "FROM form JOIN expense ON(form.form_id = expense.form_id) "
                + "JOIN user ON(user.user_id = form.user_id) JOIN status ON(form.status_id = status.status_id) ORDER BY form.form_id ASC;");

        try {
            while(rs.next()){
                formList.add(rs.getString("form_id"));
                formList.add(rs.getString("firstname") + " " + rs.getString("lastname"));
                formList.add(rs.getString("course"));
                formList.add(rs.getString("start_date") + " - " + rs.getString("end_date"));
                formList.add(rs.getString("organizer") + ", " + rs.getString("location"));
                formList.add(rs.getString("sum_expense"));
                formList.add(rs.getString("sum_date"));
                formList.add(rs.getString("status_name"));

//                System.out.println(formCounter + " " +rs.getString("course") + rs.getString("start_date") + expense.getSum_expense()+" "
//                +report.getReport_date() + report.getLecture_date() );
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HistoryUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.closeConnection();
        System.out.println(formList);
        return formList;
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
