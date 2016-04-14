package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import Utility.DataConnector;

public class Knowledge {
    private String improvement, imperiod, imevi;
    private int knowledge_id, form_id;
    private DataConnector connector;

    public Knowledge() {
        this.improvement = improvement;
        this.imperiod = imperiod;
        this.imevi = imevi;
        this.knowledge_id = knowledge_id;
        this.form_id = form_id;
    }
    
    public void createKnowledge(int knowledge_id, int form_id, String improvement, String imperiod, String imevi){
        this.improvement = improvement;
        this.imperiod = imperiod;
        this.imevi = imevi;
        this.knowledge_id = knowledge_id;
        this.form_id = getLastKnowledgeId();
    }
    
    public void insertKnowledge() {
        String sql = "INSERT INTO form VALUES("
                + (getLastKnowledgeId()+ 1) + "," + this.form_id + ",'" 
                + "','" + this.improvement + "','" + this.imperiod + "','" + this.imevi + "';";
        if(connector.update(sql) == 0) {
            System.out.println("insert sussecc");
        }

    }
    
    public void callKnowledge(int form_id) {
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM form WHERE form_id = '" + form_id + "';";
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
    }
    
    public int getLastKnowledgeId(){
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
        return lastID;
    }
    
}
