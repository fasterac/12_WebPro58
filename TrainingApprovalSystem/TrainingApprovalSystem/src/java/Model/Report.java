
package Model;

import Utility.DataConnector;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Report {
    private String report_date= "-", lecture_date= "-", file;
    private int report_id, form_id;

    public Report() {        
    }
    
    public void createReport(int form_id, String report_date, String lecture_date, String file){
        this.report_date = report_date;
        this.lecture_date = lecture_date;
        this.file = file;
        this.report_id = getLastReportId() + 1;
        this.form_id = form_id;
    }
    
    public void insertBlankReport(int form_id) {
        DataConnector connector = new DataConnector();
        String sql = "INSERT INTO report (form_id) VALUES('" + this.form_id + "');";
        if(connector.update(sql) == 0) {
            System.out.println("insert sussecc");
        }
        connector.closeConnection();
    }
    
    public void insertReport() {
        DataConnector connector = new DataConnector();
        String sql = "INSERT INTO report (form_id, report_date, file_path)  VALUES("
                + this.form_id + ",'" 
                + "','" + this.report_date + "','" + this.lecture_date + "','" + this.file + "');";
        if(connector.update(sql) == 0) {
            System.out.println("insert sussecc");
        }
        connector.closeConnection();
    }
    
    public void callReport(int form_id) {
        DataConnector connector = new DataConnector();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM report WHERE form_id = '" + form_id + "';";
            rs = connector.execute(sql);
            rs.next();
            this.form_id = form_id;
            this.report_id = rs.getInt("report_id");
            this.report_date = rs.getString("report_date");
            this.lecture_date = rs.getString("lecture_date");
            this.file = rs.getString("file_path");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connector.closeConnection();
    }
    
    public int getLastReportId(){
        DataConnector connector = new DataConnector();
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
        connector.closeConnection();
        return lastID;
    }

    public String getReport_date() {
        return report_date;
    }

    public String getLecture_date() {
        return lecture_date;
    }
    
    
}

