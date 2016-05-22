package controller.form;

import factory.FormFactory;
import model.Expense;
import model.Form;
import model.Improvement;
import utility.Authorization;
import utility.DataConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "ConfirmNewFormServlet", urlPatterns = {"/confirmnewform.do"})
public class ConfirmNewFormServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Connection connection = DataConnector.getDBConnection(request);

        Form form = (Form) session.getAttribute("form.new");

        if(form == null) {
            request.getSession().setAttribute("form.error", "NO_FORM_SENT");
            response.sendRedirect("newform.jsp");
            return;
        }

        if((form = new FormFactory(connection).create(form)) != null) {
            request.setAttribute("form.new", form);
            request.getRequestDispatcher("/WEB-INF/form/resultnewform.jsp").forward(request, response);

            session.setAttribute("form.new", null);
            session.setAttribute("form.param", null);
        } else {
            request.getSession().setAttribute("form.error", "SOMETHING_WRONG");
            response.sendRedirect("newform.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
