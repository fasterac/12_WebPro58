package Controller;

import Utility.DataConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GodServlet", urlPatterns = {"/GodServlet"})
public class GodServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        DataConnector connector = new DataConnector();
        int updateResult = 999;
        ResultSet rs = null;
        
        if(request.getParameter("exec").equals("executeUpdate(Stringsql)")){
            updateResult = connector.update(request.getParameter("sqlexec"));
        }
        else if(request.getParameter("exec").equals("executeQuery(Stringsql)")){
            rs = connector.execute(request.getParameter("sqlexec"));
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(request.getParameter("sqlexec") + "<br>");
            if(updateResult == 1){
                out.println("Update Seccessful <br> Click back for anothrer sql command");
            }
            else if(updateResult == 0){
                out.print("Error : see error at output log <br> Click back for type new sql command");
            }
            
            if(rs != null){
                try {

                    while (rs.next()) {
                        out.print("...");
                        out.print(rs.getRow());
                    
                    }   
                } catch (SQLException ex) {
                    Logger.getLogger(GodServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
