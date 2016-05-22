<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Training Approval System - Track Approval</title>
    </head>
    <body>
        <form action="UserTrackApproval" method="POST">
            <h1>Track Approval</h1>
            
            <%! User user = new User() ; %>
            <% user = (User) session.getAttribute("sesUser"); %>
            <%! FormList formlister = new FormList(); %>
            <% ArrayList<String> formlist = new ArrayList<>(); %>
            <% formlist = formlister.getUserFormList(user.getId()); %>
            
            <!-- temp header -->
            <button type="submit" name="forwarder" value="Home">Home</button>
            <button type="submit" name="forwarder" value="CreateForm">CreateForm</button>
            <button type="submit" name="forwarder" value="TrackApproval">TrackApproval</button>
            <button type="submit" name="forwarder" value="Logout">Logout</button><br><br>
            
            <!-- Table List Zone
            form_id may change to number of form -->
            <table border="1" width="850">
            <thead>
                <tr>
                    <th>Form_id</th>
                    <th>Date created</th>
                    <th>Course</th>
                    <th>Date duration</th>
                    <th>Organizer, Location</th>
                    <th>Sum Expense</th>
                    <th>Sum Hour</th>
                    <th>Status</th>
                </tr>
            </thead>
            <%! int rowcounter = 0; %>
            <% for (String word : formlist) { %>
                <% if (rowcounter % 8 == 0) { %>
                    <td><button type="submit" value="<%=word %>" name="seeform"><%=word %></button></td>
                <% } else {%>
                 <td><%=word %></td>
                <% } rowcounter += 1; if(rowcounter % 8 == 0) { %>
                    </tr> <tr>
                <% } else if (rowcounter == formlist.size()) { %>
                    </tr>
                <% } %>
                
            <%  } %>
        </form>
        
    </body>
</html>
