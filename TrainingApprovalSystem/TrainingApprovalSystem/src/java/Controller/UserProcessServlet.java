package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import sun.security.pkcs11.wrapper.Functions;

@WebServlet(name = "UserProcess.do", urlPatterns = {"/UserProcessServlet"})
public class UserProcessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        User user = new User();
        History history = new History();
        ArrayList<String> his = new ArrayList<>();
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("sesUser");
        
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
                his = history.getHistory(user.getUser_id(), "2016-10-01");
                System.out.println(his.size());
                session.setAttribute("sesHistoryUser", his);
                response.sendRedirect("ApprovalForm.jsp");
            }
            else if(request.getParameter("forwarder").equals("TrackApproval")){
                response.sendRedirect("TrackApproval.jsp");
            }
        }
        
//        if(forwarder.equals("CreateForm")){
//            his = history.getHistory(user.getUser_id(), "2016-10-01");
//            for (String word : his) {
//                System.out.println(word);
//            }
//            System.out.println(his.size());
//            
//            
//            session.setAttribute("sesHistoryUser", his);
//            
//            response.sendRedirect("ApprovalForm.jsp");
//        }
//        
//        if(forwarder.equals("Logout")){
//            String loginErrorMassage = "You have been logout successfully.";
//            session.setAttribute("sesLoginMassage", loginErrorMassage);
//            response.sendRedirect("index.jsp");
//        }
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserProcessServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProcessServlet at </h1>");
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
