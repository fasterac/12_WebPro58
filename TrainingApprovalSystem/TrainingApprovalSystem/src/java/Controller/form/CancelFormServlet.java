package controller.form;

import factory.FormFactory;
import model.Form;
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

@WebServlet(name = "CancelFormServlet", urlPatterns = {"/cancelform.do"})
public class CancelFormServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Connection connection = DataConnector.getDBConnection(request);

        FormFactory formFactory = new FormFactory(connection);

        Form form = formFactory.find(Integer.parseInt(request.getParameter("form_id")));

        if(form.getUser().getId() != new Authorization(connection, session).getCurrentUser().getId()) {
            response.getWriter().println("NOT_ENOUGH_PERMISSION");
            return;
        }

        formFactory.updateStatus(form, Form.Status.CANCEL);

        session.setAttribute("form.result", "CANCEL_COMPLETE");
        response.sendRedirect("viewsendform.jsp?form_id=" + request.getParameter("form_id"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
