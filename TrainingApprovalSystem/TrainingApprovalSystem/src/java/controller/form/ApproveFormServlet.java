package controller.form;

import factory.FormFactory;
import model.Form;
import model.User;
import utility.Authorization;
import utility.DataConnector;
import utility.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ApproveFormServlet", urlPatterns = {"/approveform.do"})
public class ApproveFormServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Connection connection = DataConnector.getDBConnection(request);

        FormFactory formFactory = new FormFactory(connection);
        User user = new Authorization(connection, session).getCurrentUser();

        Form form = formFactory.find(Integer.parseInt(request.getParameter("form_id")));

        if(user.getRole() != User.Role.ADMIN) {
            response.getWriter().println("NOT_ENOUGH_PERMISSION");
            return;
        }

        formFactory.updateStatus(form, user, Form.Status.APPROVED);

        String text = "ฟอร์มของคุณ รหัสฟอร์ม : <a href=\"viewsendform.jsp?form_id=" + form.getId() + "\">" + form.getId() + "</a> ได้รับการอนุมัติแล้ว";

        EmailSender emailSender = new EmailSender();
        emailSender.sendEmail("chaniwat.meranote@gmail.com", "รายงานความคืบหน้าของการยื่นขอเข้าร่วมอบรม, รหัสฟอร์ม : " + form.getId(), text);

        session.setAttribute("form.result", "APPROVE_COMPLETE");
        response.sendRedirect("viewsendform.jsp?form_id=" + request.getParameter("form_id"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
