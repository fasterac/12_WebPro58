package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/CustomerServlet"})
public class CustomerServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            
            /*System.out.println("......");
            
            Customer cus = new Customer(firstname, lastname, email, mobile, email, addr);
            CustomerUtility cusutil = new CustomerUtility();
            cusutil.init();
            
            int numOfEffectedRec = cusutil.insertData(firstname, lastname, mobile, email, addr);
            if(numOfEffectedRec == 1){
            System.out.println("Insert Successfully");
            }
            else{
                System.out.println("Cannot insert");
            }
            */       
            
            
            String firstname = request.getParameter("firstName");
            String lastname = request.getParameter("lastname");
            String mobile = request.getParameter("mobile");
            String email = request.getParameter("email");
            String addr = request.getParameter("addr");
            
            out.print("<h1>Customer Information</h1>");
            out.print("<b>First Name:</b> "+ firstname + "<br>");
            out.print("<b>Last Name: </b>"+ lastname + "<br>");
            out.print("<b>Mobile: </b>"+ mobile + "<br>");
            out.print("<b>E-mail: </b>"+ email + "<br>");
            out.print("<b>Address: </b>"+ addr + "<br>");
            
            out.println("</html>");
        }
        //System.out.println("proreq");
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
