<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="factory.FormFactory"%>
<%@page import="utility.DataConnector" %>
<%@page import="java.util.ArrayList" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    pageContext.setAttribute("forms" , new FormFactory(DataConnector.getDBConnection(request)).all());
%>

<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Training Approval System - Administrator Panel</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <div class="wrapper">
            <div class="box">
                <h1>Admin Page</h1> 
                <h2>Welcome ${sessionScope.user.firstname} ${sessionScope.user.lastname}</h2> <br>
                <a href="logout"><button type="button" id="login-button">Log Out</button></a><br><br>

                <fieldset>
                    <h2>✎ รายชื่อแบบฟอร์มสำหรับอนุมัติ<br><br></h2>

                    <table border="1" width="850">
                        <thead>
                            <tr>
                                <th>at</th>
                                <th>By Participant</th>
                                <th>Course</th>
                                <th>Date duration</th>
                                <th>Organizer, Location</th>
                                <th>Sum Expense</th>
                                <th>Sum Hour</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <c:forEach var="form" items="${pageScope.forms}">
                            <tr>
                                <td><a href="viewresultform?id=${form.form_id}"><button type="button">${form.form_id}</button></a></td>
                                <td>${form.user.firstname} ${form.user.lastname}</td>
                                <td>${form.course}</td>
                                <td>${form.start_date} - ${form.end_date}</td>
                                <td>${form.organizer}, ${form.location}</td>
                                <td>${form.expense.sum_expense}</td>
                                <td>${form.sum_date}</td>
                                <td>${form.status}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </fieldset>
            </div>
        </div>

        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script type="text/javascript" src="js/script.js"></script>
    </body>
</html>