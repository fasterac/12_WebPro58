/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Base factory for querying the database.
 * @param <T> 
 */
abstract class BaseFactory<T> {
    
    protected String sql;
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet result;

    public BaseFactory(Connection connection) {
        this.connection = connection;
    }
    
    public abstract T create(T model);
    public abstract ArrayList<T> all();
    public abstract T find(int id);
    public abstract T update(T model);
    public abstract void remove(int id);
    
    abstract T buildObject(ResultSet result) throws SQLException;
    
}
