
package Model;

import Utility.DataConnector;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Report {
    private String report_date, lecture_date, file;
    private int report_id, form_id;
    private DataConnector connector;

    public Report() {        
    }
    
    public void createReport(int report_id, int form_id, String report_date, String lecture_date, String file){
        this.report_date = report_date;
        this.lecture_date = lecture_date;
        this.file = file;
        this.report_id = report_id;
        this.form_id = getLastReportId();
    }
    
    public void insertReport() {
        String sql = "INSERT INTO form VALUES("
                + (getLastReportId()+ 1) + "," + this.form_id + ",'" 
                + "','" + this.report_date + "','" + this.lecture_date + "','" + this.file + "';";
        if(connector.update(sql) == 0) {
            System.out.println("insert sussecc");
        }

    }
    
    public void callReport(int form_id) {
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM form WHERE form_id = '" + form_id + "';";
            rs = connector.execute(sql);
            rs.next();
            this.form_id = form_id;
            this.report_id = rs.getInt("report_id");
            this.report_date = rs.getString("improvement");
            this.lecture_date = rs.getString("imperiod");
            this.file = rs.getString("file");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getLastReportId(){
        int lastID = 0;
        try{
            String sql = "SELECT report_id FROM report";
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

