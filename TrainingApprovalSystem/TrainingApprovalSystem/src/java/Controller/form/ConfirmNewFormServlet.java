package controller.form;

import factory.FormFactory;
import model.Expense;
import model.Form;
import model.Improvement;
import utility.Authorization;
import utility.DataConnector;
import utility.FileUploadHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//@MultipartConfig(maxFileSize = 999999999  , maxRequestSize= 999999999 ) 
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

        FileUploadHelper fileUploadHelper = new FileUploadHelper(request);
        fileUploadHelper.moveFileToCourseFile(fileUploadHelper.getTempFile((String) session.getAttribute("form.course_file_name")));

        form.setCourse_file_path((String) session.getAttribute("form.course_file_name"));

        session.setAttribute("form.course_file_name", null);

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
