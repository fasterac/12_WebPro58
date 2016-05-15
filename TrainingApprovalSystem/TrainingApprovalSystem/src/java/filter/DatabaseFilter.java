/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import Utility.DataConnector;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "DatabaseFilter")
public class DatabaseFilter implements Filter {

    private DataConnector dataConnector;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        dataConnector = new DataConnector();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        Connection connection = dataConnector.getConnection();
        DataConnector.setDBConenction(req, connection);;

        Throwable t = null;
        try {
            chain.doFilter(request, response);
        } catch (Exception ex) {
            t = ex;
        }

        try {
            if(connection != null) connection.close();
        } catch (SQLException ex) { }

        if(t != null) {
            try {
                throw t;
            } catch (Throwable ex) {
                Logger.getLogger(DatabaseFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void destroy() {

    }

}
