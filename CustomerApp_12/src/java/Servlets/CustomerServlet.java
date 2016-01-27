package Servlets;

import Model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CustomerUtility;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/CustomerServlet"})
public class CustomerServlet extends HttpServlet {
    
    public static PreparedStatement pstmt;
    public static Connection conn;
    public static Statement stmt;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Customer Information</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            
            String firstname = request.getParameter("firstName");
            String lastname = request.getParameter("lastname");
            String company = request.getParameter("company");
            String mobile = request.getParameter("mobile");
            String email = request.getParameter("email");
            String addr = request.getParameter("addr");
            
            Customer cus = new Customer(firstname, lastname, company, mobile, email, addr);
            CustomerUtility cusutil = new CustomerUtility();
            cusutil.init();
            
            int numOfEffectedRec = cusutil.insertData(cus.getFirstName(), cus.getLastName(),
            cus.getCompany(), cus.getMobile(), cus.getEmail(), cus.getAddr());
            if(numOfEffectedRec == 1){
            System.out.println("Insert Successfully");
            }
            else{
                System.out.println("Cannot insert");
            }
            
            ResultSet rs;        
            try {
                pstmt = conn.prepareStatement("SELECT * FROM CustomerMVC");
                rs =  pstmt.executeQuery();
                
                while(rs.next()){
                    out.print("<h1>Customer Information</h1>");
                    out.print("<b>First Name:</b> "+ rs.getString("firstName") + "<br>");
                    out.print("<b>Last Name: </b>"+ rs.getString("lastName") + "<br>");
                    out.print("<b>Mobile: </b>"+ rs.getString("mobile") + "<br>");
                    out.print("<b>E-mail: </b>"+ rs.getString("email") + "<br>");
                    out.print("<b>Address: </b>"+ rs.getString("addr") + "<br>");
                }

            } catch (SQLException ex) {
                Logger.getLogger(CustomerUtility.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
