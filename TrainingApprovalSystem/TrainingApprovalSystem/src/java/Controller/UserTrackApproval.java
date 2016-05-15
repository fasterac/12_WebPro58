/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import Utility.DataConnector;
import Utility.HistoryUtility;
import factory.ExpenseFactory;
import factory.FormFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserTrackApproval", urlPatterns = {"/UserTrackApproval"})
public class UserTrackApproval extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        Connection connection = DataConnector.getDBConnection(request);
        
        FormFactory formFactory = new FormFactory(connection);
        ExpenseFactory expenseFactory = new ExpenseFactory(connection);
        
        User user = new User();
        user = (User) session.getAttribute("sesUser");
        
        if(request.getParameter("seeform") != null) {
            String formID = request.getParameter("seeform");
            session.setAttribute("sesFormNumber", formID);
            Form form = formFactory.find(Integer.parseInt(formID));
            Expense expense = expenseFactory.find(Integer.parseInt(formID));
            Knowledge knowledge = new Knowledge();
            Report report = new Report();
            
//            form.callForm(Integer.parseInt(formID));
//            expense.callExpense(Integer.parseInt(formID));
            knowledge.callKnowledge(Integer.parseInt(formID));
            report.callReport(Integer.parseInt(formID));
            
            session.setAttribute("sesForm", form);
            session.setAttribute("sesExpense", expense);
            session.setAttribute("sesKnowledge", knowledge);
            session.setAttribute("sesReport", report);
            response.sendRedirect("UserFormResult.jsp");
        }
        
        //navigator button control
        if (request.getParameter("forwarder") != null && !request.getParameter("forwarder").isEmpty()) {
            if(request.getParameter("forwarder").equals("Home")){
                response.sendRedirect("UserMainPage.jsp");
            }
            else if(request.getParameter("forwarder").equals("Logout")){
                String loginErrorMassage = "You have been logout successfully.";
                session.setAttribute("sesLoginMassage", loginErrorMassage);
                response.sendRedirect("index.jsp");
            }
            else if(request.getParameter("forwarder").equals("CreateForm")){
                HistoryUtility history = new HistoryUtility(connection);
                ArrayList<String> his = new ArrayList<>();
                his = history.getHistory(user.getUser_id(), "2016-10-01");
                System.out.println(his.size());
                session.setAttribute("sesHistoryUser", his);
                response.sendRedirect("approvalform.jsp");
            }
            else if(request.getParameter("forwarder").equals("TrackApproval")){
                response.sendRedirect("TrackApproval.jsp");
            }
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserTrackApproval</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserTrackApproval at " + request.getContextPath() + "</h1>");
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
