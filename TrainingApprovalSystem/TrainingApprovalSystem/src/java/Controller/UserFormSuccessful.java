/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import Utility.DataConnector;
import Utility.HistoryUtility;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserFormSuccessful", urlPatterns = {"/UserFormSuccessful"})
public class UserFormSuccessful extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        //navigator button control
        if (request.getParameter("forwarder") != null && !request.getParameter("forwarder").isEmpty()) {
            if(request.getParameter("forwarder").equals("Home")){
                response.sendRedirect("usermainpage.jsp");
            }
            else if(request.getParameter("forwarder").equals("Logout")){
                String loginErrorMassage = "You have been logout successfully.";
                session.setAttribute("sesLoginMassage", loginErrorMassage);
                response.sendRedirect("index.jsp");
            }
            else if(request.getParameter("forwarder").equals("CreateForm")){
                ArrayList<String> his = new ArrayList<>();
                his = new HistoryUtility(DataConnector.getDBConnection(request)).getHistory(user.getUser_id(), "2016-10-01");
                for (String word : his) {
                    System.out.println(word);
                }
                System.out.println(his.size());
                session.setAttribute("sesHistoryUser", his);
                response.sendRedirect("approvalform.jsp");
            }
            else if(request.getParameter("forwarder").equals("TrackApproval")){
                response.sendRedirect("trackapproval.jsp");
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
