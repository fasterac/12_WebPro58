package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import Utility.DataConnector;

public class Knowledge {
    
    private String improvement, improvement_period, improvement_evident_period;
    private int knowledge_id, form_id;

    public Knowledge() {
    }
    
    public void createKnowledge(int form_id, String improvement, String imperiod, String imevi){
        this.improvement = improvement;
        this.improvement_period = imperiod;
        this.improvement_evident_period = imevi;
        this.knowledge_id = (getLastKnowledgeId()+ 1);
        this.form_id = form_id;
    }
    
    public Boolean insertKnowledge() {
        Boolean updateResult = false;
        DataConnector connector = new DataConnector();
        String sql = "INSERT INTO knowledge (form_id, improvement, improvement_period, "
                        + "improvement_evident_period) VALUES('"
                + this.form_id + "','" + this.improvement + "','" + this.improvement_period + "','" + this.improvement_evident_period + "');";
        if(connector.update(sql) == 1) {
            System.out.println("insert Knowledge sussecc");
            updateResult = true;
        }
        connector.closeConnection();
        return updateResult;
    }
    
    public void callKnowledge(int form_id) {
        DataConnector connector = new DataConnector();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM knowledge WHERE form_id = '" + form_id + "';";
            rs = connector.execute(sql);
            rs.next();
            this.form_id = form_id;
            this.knowledge_id = rs.getInt("knowledge_id");
            this.improvement = rs.getString("improvement");
            this.improvement_period = rs.getString("improvement_period");
            this.improvement_evident_period = rs.getString("improvement_evident_period");
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connector.closeConnection();
    }
    
    public int getLastKnowledgeId(){
        DataConnector connector = new DataConnector();
        int lastID = 0;
        try{
            String sql = "SELECT knowledge_id FROM knowledge";
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

    public String getImprovement() {
        return improvement;
    }

    public String getImprovementPeriod() {
        return improvement_period;
    }

    public String getImprovementEvidentPeriod() {
        return improvement_evident_period;
    }

    public int getKnowledge_id() {
        return knowledge_id;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setImprovement(String improvement) {
        this.improvement = improvement;
    }

    public void setImprovementPeriod(String improvement_period) {
        this.improvement_period = improvement_period;
    }

    public void setImprovementEvidentPeriod(String improvement_evident_period) {
        this.improvement_evident_period = improvement_evident_period;
    }

    public void setKnowledge_id(int knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }
    
}
