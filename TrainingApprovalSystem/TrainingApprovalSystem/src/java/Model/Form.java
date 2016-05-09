package Model;

import java.util.*;
import java.sql.*;
import Utility.DataConnector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Form {
    private int user_id, form_id, status_id, inter_id, sum_date = 0;
    private String form_date, course, organizer = "", location = "", start_date = "CURRENT_TIMESTAMP", end_date = "";
    private Connection conn;

    public Form() {
        
    }
    
    public void createForm(int user_id, String course, String organizer, String location,
            String start_date, String end_date, int sum_date, int inter_id ){
        this.form_id = getLastFormId() + 1;
        this.status_id = 0;
        this.user_id = user_id;
        this.course = course;
        this.organizer = organizer;
        this.location = location;
        this.start_date = start_date;
        this.end_date = end_date;
        this.sum_date = sum_date;
        this.inter_id = inter_id;
    }
    
    public Boolean insertForm(){
        Boolean updateResult = false;
        DataConnector connector = new DataConnector();
        String sql = "INSERT INTO form (form_id, user_id, status_id, course, organizer, location, "
                + "start_date, end_date, sum_date, inter_id) VALUES ('"
                + this.form_id + "','" + this.user_id + "','0','"
                + this.course + "','" + this.organizer + "','" + this.location + "','" 
                + this.start_date + "','" + this.end_date + "','" + this.sum_date + "','" + this.inter_id + "');";
        if(connector.update(sql) == 1) {
            System.out.println("Form insert sussecc");
            updateResult = true;
        }
        connector.closeConnection();
        return updateResult;
    }
    
    public void callForm(int form_id){
        DataConnector connector = new DataConnector();
        String sql = "SELECT * FROM form WHERE form_id = '"+form_id + "';";
        ResultSet rs = connector.execute(sql);
        try {
            rs.next();
            this.form_id = form_id;
            this.status_id = rs.getInt("status_id");
            this.user_id = rs.getInt("user_id");
            this.form_date = rs.getString("form_date");
            this.course = rs.getString("course");
            this.organizer = rs.getString("organizer");
            this.location = rs.getString("location");
            this.start_date = rs.getString("start_date");
            this.end_date = rs.getString("end_date");
            this.sum_date = rs.getInt("sum_date");
            this.inter_id = rs.getInt("inter_id");
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.closeConnection();

    }
    
    private String calculateSumDate(){
        String sumdate = "";
        //this.start_date + this.end_date
        return sumdate;
    }
    
    public int getLastFormId(){
        DataConnector connector = new DataConnector();
        int lastID = 0;
        try{
            String sql = "SELECT form_id FROM form;";
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
    
    //use when form didn't insert successful in user form servlet
    public Boolean deleteForm(int form_id){
        Boolean updateResult = false;
        DataConnector connector = new DataConnector();
        String sql = "DELETE FROM form WHERE form_id ='" + form_id + "';";
        if(connector.update(sql) == 1) {
            System.out.println("delete form " + form_id + "successful");
            updateResult = true;
        }
        connector.closeConnection();
        return updateResult;
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

    public int getStatus_id() {
        return status_id;
    }

    public int getInter_id() {
        return inter_id;
    }

    public int getSum_date() {
        return sum_date;
    }

    public String getForm_date() {
        return form_date;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getLocation() {
        return location;
    }

    public String getEnd_date() {
        return end_date;
    }

    //for admin use in "admin form result servlet"
    public void setStatus_id(int status_id) {
        DataConnector connector = new DataConnector();
        String sql = "UPDATE form SET status_id='"+ status_id +"' WHERE form_id='" + this.form_id + "';";
        if(connector.update(sql) == 0) {
            System.out.println("update Status successful");
        }
        connector.closeConnection();
    }

    
    
    
}
