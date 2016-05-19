//package controller;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import utility.DataConnector;
//import model.*;
//import factory.UserFactory;
//import javax.servlet.http.HttpSession;
//
//
//@WebServlet(name = "/CheckLogin.do", urlPatterns = {"/CheckLogin.do"})
//public class LoginProcessServlet extends HttpServlet {
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        HttpSession session = request.getSession();
//
//        DataConnector connector = new DataConnector();
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        String role = connector.executeString(("SELECT role FROM user WHERE username = '" + username + "' AND password = '" + password +"';") , "role");
//        connector.closeConnection();
//
//        if(role.equals("admin") || role.equals("user")) {
//            User user = new UserFactory(DataConnector.getDBConnection(request)).findByUsername(username);
//            session.setAttribute("user", user);
//
//            if (role.equals("admin")) response.sendRedirect("adminmainpage.jsp");
//            else if(role.equals("user")) response.sendRedirect("usermainpage.jsp");
//        } else {
//            String loginErrorMassage = "Wrong Username or Password";
//            session.setAttribute("sesLoginMassage", loginErrorMassage);
//            response.sendRedirect("index.jsp");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
