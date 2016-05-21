///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller.nencon;
//
//import model.Expense;
//import model.Form;
//import model.Knowledge;
//import model.Report;
//import utility.DataConnector;
//import factory.ExpenseFactory;
//import factory.FormFactory;
//import java.io.IOException;
//import java.sql.Connection;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet(name = "ViewResultFormServlet", urlPatterns = {"/viewresultform"})
//public class ViewResultFormServlet extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        Connection connection = DataConnector.getDBConnection(request);
//
//        int form_id = Integer.parseInt(request.getParameter("id"));
//
//        Form form = new FormFactory(connection).find(form_id);
//        Expense expense = new ExpenseFactory(connection).findByFormID(form_id);
//        Knowledge knowledge = new Knowledge();
//        Report report = new Report();
//
//        knowledge.callKnowledge(form_id);
//        report.callReport(form_id);
//
//        request.setAttribute("form", form);
//        request.setAttribute("expense", expense);
//        request.setAttribute("knowledge", knowledge);
//        request.setAttribute("report", report);
//
//        request.getRequestDispatcher("/WEB-INF/formresult.jsp").forward(request, response);
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
