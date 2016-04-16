package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import Utility.DataConnector;

public class Knowledge {
    private String improvement, imperiod, imevi;
    private int knowledge_id, form_id;

    public Knowledge() {
    }
    
    public void createKnowledge(int form_id, String improvement, String imperiod, String imevi){
        this.improvement = improvement;
        this.imperiod = imperiod;
        this.imevi = imevi;
        this.knowledge_id = (getLastKnowledgeId()+ 1);
        this.form_id = form_id;
    }
    
    public void insertKnowledge() {
        DataConnector connector = new DataConnector();
        String sql = "INSERT INTO knowledge (form_id, improvement, improvement_period, "
                        + "improvement_evident_period) VALUES('"
                + this.form_id + "','" + this.improvement + "','" + this.imperiod + "','" + this.imevi + "');";
        if(connector.update(sql) == 0) {
            System.out.println("Knowledge insert sussecc");
        }
        connector.closeConnection();
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
            this.imperiod = rs.getString("imperiod");
            this.imevi = rs.getString("imevi");
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
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        connector.closeConnection();
        return lastID;
    }
    
}