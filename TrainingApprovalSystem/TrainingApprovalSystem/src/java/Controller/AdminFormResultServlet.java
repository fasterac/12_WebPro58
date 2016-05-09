/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        Form form = new Form();
        Expense expense = new Expense();
        Knowledge knowledge = new Knowledge();
        Report report = new Report();
        
        form = (Form) session.getAttribute("sesForm");
        expense = (Expense) session.getAttribute("sesExpense");
        knowledge = (Knowledge) session.getAttribute("sesKnowledge");
        report = (Report) session.getAttribute("sesReport");
        
        
        if(request.getParameter("forwarder") != null){
            if(request.getParameter("forwarder").equals("Logout")){
                String loginErrorMassage = "You have been logout successfully.";
                session.setAttribute("sesLoginMassage", loginErrorMassage);
                response.sendRedirect("index.jsp");
            }
            else if(request.getParameter("forwarder").equals("Back")){
                response.sendRedirect("AdminMainPage.jsp");
            }
        }
        if(request.getParameter("changeStatus") != null){
            if(request.getParameter("changeStatus").equals("Approve")){
                form.setStatus_id(1);
                response.sendRedirect("AdminMainPage.jsp");
            }
            else if(request.getParameter("changeStatus").equals("Reject")){
                form.setStatus_id(2);
                response.sendRedirect("AdminMainPage.jsp");
            }
            else if(request.getParameter("changeStatus").equals("Cancle")){
                form.setStatus_id(3);
                response.sendRedirect("AdminMainPage.jsp");
            }
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminFormResultServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminFormResultServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
