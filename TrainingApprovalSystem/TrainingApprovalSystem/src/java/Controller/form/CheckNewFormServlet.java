package controller.form;

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
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CheckNewFormServlet", urlPatterns = {"/checknewform.do"})
@MultipartConfig
public class CheckNewFormServlet extends HttpServlet {

    private static final String[] notEmptyParams =
            {"course_name", "organizer_name", "location_name", "start_date", "end_date", "register_cost", "detail", "date_duration", "report_sent_duration"};

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Connection connection = DataConnector.getDBConnection(request);

        HashMap<String, String> params = new HashMap<>();

        for(Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            params.put(param.getKey(), param.getValue()[0]);
        }
        session.setAttribute("form.param", params);

        for(String str : notEmptyParams) {
            if(params.get(str) == null || params.get(str).trim().equals("")) {
                session.setAttribute("form.error", "FORM_INPUT_EMPTY");
                response.sendRedirect("newform.jsp");
                return;
            }
        }

        boolean useExpense = false;

        if(params.get("use_expense") != null) {
            for(String str : new String[]{"room_night_amount", "room_night_each", "day_amount", "day_cost_each", "travel_cost"}) {
                if(params.get(str) == null || params.get(str).trim().equals("")) {
                    session.setAttribute("form.error", "FORM_INPUT_EMPTY");
                    response.sendRedirect("newform.jsp");
                    return;
                }
            }
            useExpense = true;
        }

        if(request.getPart("course_file") == null) {
            session.setAttribute("form.error", "FORM_COURSE_DETAIL_EMPTY");
            response.sendRedirect("newform.jsp");
            return;
        }

        FileUploadHelper fileUploadHelper = new FileUploadHelper(request);
        File file = fileUploadHelper.uploadToTemp(request.getPart("course_file"));

        session.setAttribute("form.course_file_name", file.getName());

        Form form = new Form();
        form.setUser(new Authorization(connection, session).getCurrentUser());
        form.setCourse_name(params.get("course_name"));
        form.setOrganizer_name(params.get("organizer_name"));
        form.setLocation_name(params.get("location_name"));
        try {
            form.setStart_date(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("start_date")));
            form.setEnd_date(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("end_date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Expense expense = new Expense();
        expense.setRegister_cost(Double.parseDouble(params.get("register_cost")));
        if(useExpense) {
            form.setUse_expense(true);
            expense.setRoom_night_amount(Integer.parseInt(params.get("room_night_amount")));
            expense.setRoom_night_each(Double.parseDouble(params.get("room_night_each")));
            expense.setDay_amount(Integer.parseInt(params.get("day_amount")));
            expense.setDay_cost_each(Double.parseDouble(params.get("day_cost_each")));
            expense.setTravel_cost(Double.parseDouble(params.get("travel_cost")));
        }
        form.setExpense(expense);

        Improvement improvement = new Improvement();
        improvement.setDetail(params.get("detail"));
        improvement.setDate_duration(Integer.parseInt(params.get("date_duration")));
        improvement.setReport_sent_duration(Integer.parseInt(params.get("report_sent_duration")));
        form.setImprovement(improvement);

        session.setAttribute("form.new", form);
        request.getRequestDispatcher("/WEB-INF/form/checknewform.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
