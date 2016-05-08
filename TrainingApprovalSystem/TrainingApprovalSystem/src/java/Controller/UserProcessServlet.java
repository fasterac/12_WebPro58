package Controller;

import Utility.HistoryUtility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import sun.security.pkcs11.wrapper.Functions;
import Utility.DataConnector;

@WebServlet(name = "UserProcess.do", urlPatterns = {"/UserProcessServlet"})
public class UserProcessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Connection connection = DataConnector.getDBConnection(request);
        
        String forwarder = request.getParameter("forwarder");
        String loggouter = ".";
        
        User user = new User();
        HistoryUtility history = new HistoryUtility(connection);
        ArrayList<String> his = new ArrayList<>();
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("sesUser");
        
        if(forwarder.equals("CreateForm")){
            his = history.getHistory(user.getUsername(), "2016-10-01");
            for (String word : his) {
                System.out.println(word);
            }
            System.out.println(his.size());
            
            
            session.setAttribute("sesHistoryUser", his);
            
            response.sendRedirect("ApprovalForm.jsp");
        }
        
        if(forwarder.equals("Logout")){
            String loginErrorMassage = "You have been logout successfully.";
            session.setAttribute("sesLoginMassage", loginErrorMassage);
            response.sendRedirect("index.jsp");
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
