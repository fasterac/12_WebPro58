package Controller;

import Utility.HistoryUtility;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import Utility.DataConnector;
import factory.ExpenseFactory;
import factory.FormFactory;
import java.sql.Connection;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@WebServlet(name = "UserFormServlet.do", urlPatterns = {"/UserFormServlet"})
public class UserFormServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Connection connection = DataConnector.getDBConnection(request);
        
        ExpenseFactory expenseFactory = new ExpenseFactory(connection);
        FormFactory formFactory = new FormFactory(connection);
        
//        Form form = new Form();
//        Expense expense = new Expense();
        Knowledge knowledge = new Knowledge();
        Report report = new Report();
        int inter = 0, updateSuccess = 4;
        String updateFormFlag = "";
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sesUser");
        
        if(request.getParameter("course") != null || request.getParameter("organizer") != null || request.getParameter("location") != null || 
                request.getParameter("start_date") != null || request.getParameter("end_date") != null || 
                request.getParameter("reg_expense") != null || request.getParameter("inter_expense") != null || request.getParameter("acc_night") != null || 
                request.getParameter("acc_each") != null || request.getParameter("acc_sum") != null || request.getParameter("allo_day") != null || 
                request.getParameter("allo_each") != null || request.getParameter("allo_sum") != null || request.getParameter("travelling") != null || 
                request.getParameter("improvement") != null || request.getParameter("improvement_period") != null || request.getParameter("improvement_evident_period") != null  ){
            response.sendRedirect("ApprovalForm.jsp");
            return;
        }
        
        if (request.getParameter("inter") != null && !request.getParameter("inter").isEmpty()) {
            inter = 1;
        }
        
        Form form = new Form();
        
        form.setUser_id(user.getUser_id());
        form.setCourse(request.getParameter("course"));
        form.setOrganizer(request.getParameter("organizer"));
        form.setLocation(request.getParameter("location"));
        form.setStart_date(request.getParameter("start_date"));
        form.setEnd_date(request.getParameter("end_date"));
        form.setSum_date(0);
        form.setInter_id(inter);
        
        Expense expense = new Expense();
        
        expense.setForm_id(form.getForm_id());
        expense.setReg_expense(Double.parseDouble(request.getParameter("reg_expense")));
        expense.setInter_expense(Double.parseDouble(request.getParameter("inter_expense")));
        expense.setAcc_night(Integer.parseInt(request.getParameter("acc_night")));
        expense.setAcc_each(Double.parseDouble(request.getParameter("acc_each")));
        expense.setAcc_sum(Double.parseDouble(request.getParameter("acc_sum")));
        expense.setAllo_day(Integer.parseInt(request.getParameter("allo_day")));
        expense.setAllo_each(Double.parseDouble(request.getParameter("allo_each")));
        expense.setAllo_sum(Double.parseDouble(request.getParameter("allo_sum")));
        expense.setTravelling(Double.parseDouble(request.getParameter("travelling")));
        
        knowledge.createKnowledge((form.getForm_id()), request.getParameter("improvement"), request.getParameter("improvement_period"), request.getParameter("improvement_evident_period"));
        
        //check is update successful and insert all form
        if((form = formFactory.create(form)) == null){
            updateFormFlag.concat("Error to insert Form");
            updateSuccess -= 1;            
        }
        
        if((expense = expenseFactory.create(expense)) == null){
            if(updateSuccess < 4){
                updateFormFlag.concat(", Expense");
            } else {
                updateFormFlag.concat("Error to insert Expense");
            }
            updateSuccess -= 1;
        }
        
        if(!knowledge.insertKnowledge()){
            if(updateSuccess < 4){
                updateFormFlag.concat(", Knowledge");
            } else {
                updateFormFlag.concat("Error to insert Knowledge");
            }
            updateSuccess -= 1;
        }
        
        if(!report.insertBlankReport((int)(form.getForm_id()))){
            if(updateSuccess < 4){
                updateFormFlag.concat(", Report");
            } else {
                updateFormFlag.concat("Error to insert Report");
            }
            updateSuccess -= 1;
        }
        
        if(updateSuccess == 4){
            updateFormFlag = "Create Form Successful";
        }
        
        session.setAttribute("sesFormUpdate", updateFormFlag);
        
        //navigator button control
        if (request.getParameter("submit") != null && !request.getParameter("submit").isEmpty()) {
            if(request.getParameter("submit").equals("submit")){
                response.sendRedirect("UserFormSuccessful.jsp");
                return;
            }
        } else if (request.getParameter("forwarder") != null && !request.getParameter("forwarder").isEmpty()) {
            if(request.getParameter("forwarder").equals("Home")){
                response.sendRedirect("UserMainPage.jsp");
                return;
            }
            else if(request.getParameter("forwarder").equals("Logout")){
                String loginErrorMassage = "You have been logout successfully.";
                session.setAttribute("sesLoginMassage", loginErrorMassage);
                response.sendRedirect("index.jsp");
                return;
            }
            else if(request.getParameter("forwarder").equals("CreateForm")){
                HistoryUtility history = new HistoryUtility(connection);
                ArrayList<String> his = new ArrayList<>();
                his = history.getHistory(user.getUser_id(), "2016-10-01");
                for (String word : his) {
                    System.out.println(word);
                }
                System.out.println(his.size());
                
                session.setAttribute("sesHistoryUser", his);
                response.sendRedirect("ApprovalForm.jsp");
                return;
            }
            else if(request.getParameter("forwarder").equals("TrackApproval")){
                response.sendRedirect("UserMainPage.jsp");
                return;
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
