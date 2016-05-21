<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="javax.servlet.http.HttpServletRequest" %> 
<%@page import="java.util.ArrayList" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Training Approval System - Create Form Successful</title>
    </head>
    <body>
        <form action="UserFormSuccessful" method="POST">
            <%!String updateMassage; %>
            <% updateMassage = (String) session.getAttribute("sesFormUpdate"); %>
            
            <!-- header area (temp header below)-->
            <button type="submit" name="forwarder" value="Home">Home</button>
            <button type="submit" name="forwarder" value="CreateForm">CreateForm</button>
            <button type="submit" name="forwarder" value="TrackApproval">TrackApproval</button>
            <button type="submit" name="forwarder" value="Logout">Logout</button><br>
            
            
            <h3> <%= updateMassage %></h3><br>
            You can see your form at Track Approval button
            <button type="submit" name="forwarder" value="TrackApproval">TrackApproval</button><br><br>
            <button type="submit" name="forwarder" value="Home">Back to Home</button>
        </form>
    </body>
</html>
