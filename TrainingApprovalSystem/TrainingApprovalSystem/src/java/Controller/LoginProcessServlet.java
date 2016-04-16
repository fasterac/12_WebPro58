package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utility.DataConnector;
import javax.servlet.RequestDispatcher;
import Model.*;
import javax.servlet.http.HttpSession;


@WebServlet(name = "/CheckLogin.do", urlPatterns = {"/CheckLogin.do"})
public class LoginProcessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();


        
        DataConnector connector = new DataConnector();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String role = "";
        role = connector.execute(("SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password +"';") , "role");
        
        
        
        if (role.equals("admin")) {
            User user = new User();
            user.callUser(username);
            session.setAttribute("reqUser", user);
            RequestDispatcher dispatch = request.getRequestDispatcher("AdminMainPage.jsp");
            dispatch.forward(request, response);
        }
        else if(role.equals("user")){
            User user = new User();
            user.callUser(username);
            session.setAttribute("reqUser", user);
            RequestDispatcher dispatch = request.getRequestDispatcher("UserMainPage.jsp");
            dispatch.forward(request, response);
        }
//        else if((request.getParameter("god mode")).equals("GOD MODE!")){
//            RequestDispatcher dispatch = request.getRequestDispatcher("GodModeJSP.jsp");
//            dispatch.forward(request, response);
//        }
        else{
            RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
            dispatch.forward(request, response);
        }
        
        
        try (PrintWriter out = response.getWriter()) {
            
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