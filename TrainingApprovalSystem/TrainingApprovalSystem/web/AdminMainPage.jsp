<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %> 
<%@page import="Utility.DataConnector" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html >
  <head>

      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Training Approval System - Administrator Panel</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <script type="text/javascript" src="java/javascript.js"></script>
    <script type="text/javascript" src="java/fileupload.js"></script>
    <link rel="stylesheet" href="css/style.css">
    
    
  </head>

  <body onload="load()">
      <form action="AdminProcessServlet" method="POST">
     
   <div class="wrapper">
	
		 
                <div class="box">
                	
		
<!--................................. CONTENT  FORM...................................................................-->  
                    <%! User user = new User() ; %>
                    <%! FormList formlister = new FormList(); %>
                    <% ArrayList<String> formlist = new ArrayList<>(); %>
                    <% formlist = formlister.getAllFormList(); %>
                    <%-- connect.execute("SELECT * FROM form JOIN expense ON(form.form_id = expense.form_id);"); --%>
                    <% user = (User) session.getAttribute("sesUser"); %>
                    <% request.setAttribute("pageFormList", formlist); %>
                    <h1>Admin Page</h1> 
                    <h2>Welcome ${sessionScope.sesUser.getFirstname()} ${sessionScope.sesUser.getLastname()}</h2> <br>
                    <button type="submit" value="Logout" name="logout" id="login-button">Log Out</button><br><br>
        
                    
                    
                    <fieldset>
                        <h2>✎รายชื่อแบบฟอร์มสำหรับอนุมัติ<br><br></h2>
        
        
        
        <table border="1" width="850">
            <thead>
                <tr>
                    <th>at</th>
                    <th>By Participer</th>
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
            <%--
            <% System.out.println("Connecting " + rs.isClosed()); %>            
            <%! ResultSet rs = connect.execute("SELECT * FROM form JOIN expense ON(form.form_id = expense.form_id);"); %>
            <% System.out.println("Connected " + rs.isClosed()); %>
            <%! int triger = 0; %>
            <tbody>
                <% while(rs.next()) { %>
                <% System.out.println("print firstrow start with " + rs.getString(1)); %>
                <tr>
                    <td><button type="submit" value="<%= rs.getString("form_id") %>" name="seeform"><%= rs.getString("form_id") %></button></td>
                    <td> call and getFirstname</td>
                    <td><%= rs.getString("course") %></td>
                    <td><%= rs.getString("start_date") %> - <%= rs.getString("end_date") %></td>
                    <td><%= rs.getString("organizer") %> ,<br> <%= rs.getString("location") %></td>
                    <td><%= rs.getString("sum_expense") %></td>
                    <td>end_date - start_date</td>
                    <td><%= rs.getString("status_id") %></td>                        
                </tr>
                <% } %>
            </tbody>
            <% rs.close(); %>         
            <% System.out.println("Close Connection " + rs.isClosed()); %>
        </table>
        --%>
        
        
        
    </div>
      
    </div>
      
       
	</fieldset> 
	
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="js/index.js"></script>

    
    
    </form>
  </body>
</html>