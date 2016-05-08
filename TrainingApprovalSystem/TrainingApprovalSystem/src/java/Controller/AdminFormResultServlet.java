/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.*;

@WebServlet(name = "AdminFormResultServlet", urlPatterns = {"/AdminFormResultServlet"})
public class AdminFormResultServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        if(request.getParameter("forwarder") != null){
            switch(request.getParameter("forwarder")) {
                case "Logout":
                    session.setAttribute("sesLoginMassage", "You have been logout successfully.");
                    response.sendRedirect("index.jsp");
                    return;
                case "Back":
                    response.sendRedirect("AdminMainPage.jsp");
                    return;
            }
        }
        
        if(request.getParameter("changeStatus") != null) {
            Form form = (Form) session.getAttribute("sesForm");
            
            switch(request.getParameter("changeStatus")) {
                case "Approve":
                    form.setStatus_id(1);
                    return;
                case "Reject":
                    form.setStatus_id(2);
                    return;
                case "Cancle":
                    form.setStatus_id(3);
                    return;
            }
            
            response.sendRedirect("AdminMainPage.jsp");
            return;
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
