package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.*;
import utility.DataConnector;
import factory.ExpenseFactory;
import factory.FormFactory;
import java.sql.Connection;

@WebServlet(name = "AdminProcessServlet.do", urlPatterns = {"/AdminProcessServlet"})
public class AdminProcessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        Connection connection = DataConnector.getDBConnection(request);
        ExpenseFactory expenseFactory = new ExpenseFactory(connection);
        FormFactory formFactory = new FormFactory(connection);
        
        if(request.getParameter("logout") != null){
            if(request.getParameter("logout").equals("Logout")){
                String loginErrorMassage = "You have been logout successfully.";
                session.setAttribute("sesLoginMassage", loginErrorMassage);
                response.sendRedirect("index.jsp");
                return;
            }
        } else if(request.getParameter("seeform") != null) {
            String formID = request.getParameter("seeform");
            session.setAttribute("sesFormNumber", formID);
            
            Form form = formFactory.find(Integer.parseInt(formID));
            Expense expense = expenseFactory.findByFormID(Integer.parseInt(formID));
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
            response.sendRedirect("FormResult.jsp");
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
