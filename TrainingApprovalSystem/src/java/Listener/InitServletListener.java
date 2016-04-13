package Listener;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.*;

public class InitServletListener implements ServletContextListener {
    
    Connection conn;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            conn = getDB12().getConnection();
            sce.getServletContext().setAttribute("connection", conn);
        } catch (NamingException ex) {
            Logger.getLogger(InitServletListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InitServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       try{
            conn.close();
       }catch(Exception ex){
            ex.getStackTrace();
       }
    }

    private DataSource getDB12() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/DB12");
    }
    
    
}
