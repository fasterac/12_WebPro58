<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %> 
<%@page import="javax.servlet.http.HttpServletRequest" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Training Approval System - Home</title>
    </head>
    <body>
        <form action="UserProcessServlet" method="POST">
            <h1>User Page</h1>
            <%! User user = new User() ; %> 
            <% user = (User) session.getAttribute("reqUser"); %>
            <%-- request.setAttribute("reqUser", user); --%>
            <h2>Welcome <%= user.getFirstname()%> <%= user.getLastname() %></h2>
            <input type="submit" value="Logout" name="logout" /> <br>
            <input type="submit" value="CreateForm" name="forwarder" /> 
            <input type="submit" value="TrackApproval" name="forwarder" /> 
        </form>
    </body>
</html>
