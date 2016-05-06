package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;


@WebServlet(name = "UserFormServlet.do", urlPatterns = {"/UserFormServlet"})
public class UserFormServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        User user = new User();
        Form form = new Form();
        Expense expense = new Expense();
        Knowledge knowledge = new Knowledge();
        Report report = new Report();
        int inter = 0;
        
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("sesUser");
        
        if(request.getParameter("course") != null || request.getParameter("organizer") != null || request.getParameter("location") != null || 
                request.getParameter("start_date") != null || request.getParameter("end_date") != null || 
                request.getParameter("reg_expense") != null || request.getParameter("inter_expense") != null || request.getParameter("acc_night") != null || 
                request.getParameter("acc_each") != null || request.getParameter("acc_sum") != null || request.getParameter("allo_day") != null || 
                request.getParameter("allo_each") != null || request.getParameter("allo_sum") != null || request.getParameter("traveling") != null || 
                request.getParameter("improvement") != null || request.getParameter("improvement_period") != null || request.getParameter("improvement_evident_period") != null  ){
            response.sendRedirect("ApprovalForm.jsp");
        }
        
        else if (request.getParameter("inter") != null && !request.getParameter("inter").isEmpty()) {inter = 1;}
        form.createForm(user.getUsername(), request.getParameter("course"), request.getParameter("organizer"), 
                request.getParameter("location"), request.getParameter("start_date"), request.getParameter("end_date"), 0, inter);
        
        expense.createExpense((form.getForm_id()), (double)(Double.parseDouble(request.getParameter("reg_expense"))), (double)(Double.parseDouble(request.getParameter("inter_expense"))),
                (int)(Double.parseDouble(request.getParameter("acc_night"))), (double)(Double.parseDouble(request.getParameter("acc_each"))), (double)(Double.parseDouble(request.getParameter("acc_sum"))), 
                (int)(Double.parseDouble(request.getParameter("allo_day"))), (double)(Double.parseDouble(request.getParameter("allo_each"))),
                (double)(Double.parseDouble(request.getParameter("allo_sum"))), (double)(Double.parseDouble(request.getParameter("traveling"))));
        
        knowledge.createKnowledge((form.getForm_id()), request.getParameter("improvement"), request.getParameter("improvement_period"), request.getParameter("improvement_evident_period"));
        
        form.insertForm();
        expense.insertExpense();
        knowledge.insertKnowledge();
        report.insertBlankReport((form.getForm_id()));
        
        if (request.getParameter("knowsub") != null && !request.getParameter("knowsub").isEmpty()) {
            if(request.getParameter("knowsub").equals("knowsub")){
                response.sendRedirect("UserMainPage.jsp");
            }
        }

        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserFormServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserFormServlet at " + request.getContextPath() + "</h1>");
            out.println("<h2>Create Form Successful</h2>");
            out.println("<form action=\"UserFormServlet\" method=\"POST\">"
                    + "<button name=\"knowsub\" values=\"knowsub\" type=\"submit\"></button>"
                    + "</form>");
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
