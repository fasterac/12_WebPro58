package Model;

import java.util.*;
import java.sql.*;
import Utility.DataConnector;

public class Form {
    private int form_id, status_id, inter_id, sum_date;
    private String form_date, course, organizer, location, start_date, end_date;
    private Connection conn;
    private DataConnector connector;

    public Form() {
        
    }
    
    public void createForm(int form_id, int status_id, String form_date, String course, String organizer, String location,
            String start_date, String end_date, int sum_date, int inter_id ){
        this.form_id = getLastFormId() + 1;
        this.status_id = 1;
        this.form_date = form_date;
        this.course = course;
        this.organizer = organizer;
        this.location = location;
        this.start_date = start_date;
        this.end_date = end_date;
        this.sum_date = sum_date;
        this.inter_id = inter_id;
    }
    
    public void insertForm(){
        try{
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO form VALUES("
                    + (getLastFormId() + 1) +"," + 1 + ",'" + getTimeStamp() + "','" + this.course 
                    + "','" + this.organizer + "','" + this.location + "','" + this.start_date
                    + "','" + this.end_date + "','" + this.sum_date + "'," + this.inter_id + ";";
            if(connector.update(sql) == 0) {
                System.out.println("insert sussecc");
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void callForm(int form_id){
        ResultSet rs = null;
        try{
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM form WHERE form_id = '"+form_id + "';";
            rs = stmt.executeQuery(sql);
            rs.next();
            this.form_id = form_id;
            this.status_id = rs.getInt("status_id");
            this.form_date = rs.getString("form_date");
            this.course = rs.getString("course");
            this.organizer = rs.getString("organizer");
            this.location = rs.getString("location");
            this.start_date = rs.getString("start_date");
            this.end_date = rs.getString("end_date");
            this.sum_date = rs.getInt("sum_date");
            this.inter_id = rs.getInt("inter_id");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    private String calculateSumDate(){
        String sumdate = "";
        //this.start_date + this.end_date
        return sumdate;
    }
    
    public int getLastFormId(){
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
    
    private String getTimeStamp(){
        String timestamp = "";
        return timestamp;
    }
    
    public int getForm_id() {
        return form_id;
    }

    public String getCourse() {
        return course;
    }

    public String getStart_date() {
        return start_date;
    }

    
    
    
}
