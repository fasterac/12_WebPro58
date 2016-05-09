/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import Model.Knowledge;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author meranote
 */
public class KnowledgeFactory extends BaseFactory<Knowledge> {

    public KnowledgeFactory(Connection connection) {
        super(connection);
    }

    @Override
    public Knowledge create(Knowledge model) {
        try {
            sql = "INSERT INTO knowledge VALUES(0, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, model.getForm_id());
            statement.setString(2, model.getImprovement());
            statement.setString(3, model.getImprovementPeriod());
            statement.setString(4, model.getImprovementEvidentPeriod());
            
            model.setKnowledge_id(statement.executeUpdate());
            
            return model;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Knowledge> all() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Knowledge find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Knowledge update(Knowledge model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Knowledge remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Knowledge buildObject(ResultSet result) throws SQLException {
        Knowledge knowledge = new Knowledge();
        
        knowledge.setForm_id(result.getInt("form_id"));
        knowledge.setKnowledge_id(result.getInt("knowledge_id"));
        knowledge.setImprovement(result.getString("improvement"));
        knowledge.setImprovementPeriod(result.getString("improvement_period"));
        knowledge.setImprovementEvidentPeriod(result.getString("improvement_evident_period"));
        
        return knowledge;
    }
    
}
