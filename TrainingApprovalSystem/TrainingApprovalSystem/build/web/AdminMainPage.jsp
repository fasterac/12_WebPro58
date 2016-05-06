<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %> 
<%@page import="Utility.DataConnector" %>
<%@page import="java.sql.ResultSet" %>
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
                    <% user = (User) session.getAttribute("sesUser"); %>
                    <h1>Admin Page</h1> 
                    <h2>Welcome ${sessionScope.sesUser.getFirstname()} ${sessionScope.sesUser.getLastname()}</h2>
                    <!--<input class="button" type="submit" value="Logout" name="logout" />--> <br>
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
                    <th>confirm</th>
                </tr>
            </thead>
            <%-- error rs.next has close conn
            must get rid callexpanse and use join in query--%>
            <%! DataConnector connect = new DataConnector(); %>
            <%! ResultSet rs = connect.execute("SELECT * FROM form"); %>
            <%! Expense expense = new Expense(); %>
            <%! int triger = 0; %>
            <tbody>
                <% while(rs.next()) { %>
                <tr>                    
                    <% expense.callExpense(rs.getInt("form_id")); %>
                    <td><button type="submit" value="<%= rs.getString("form_id") %>" name="seeform"><%= rs.getString("form_id") %></button</td>
                    <td> call and getFirstname</td>
                    <td><%= rs.getString("course") %></td>
                    <td><%= rs.getString("start_date") %></td>
                    <td><%= rs.getString("organizer") %></td>
                    <td><%= rs.getString("location") %></td>
                    <td><%= expense.getSum_expense() %></td>
                    <%-- rs.getInt("status_id"); --%>
                    <td><select name="status"  >
                            <option selected="selected">pending</option>
                            <option>approved</option>
                            <option>rejected</option>
                            <option>cancled</option>                            
                        </select></td>                        
                    <td><button type="submit" value="<%= rs.getInt("form_id")%>" name="confirm" id="login-button">Confirm</button><!--<input type="submit" value="confirm change" name="confirm" />-->
                    </td>
                </tr>
                <% } %>
                
            </tbody>
        </table>
        
        
        [btn:look full form] formID username(requestBy) where duration cost status[v] [btn:confirm]
        
    </div>
      
    </div>
      
       
	</fieldset> 
	
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="js/index.js"></script>

    
    <% connect.closeConnection(); %>
    </form>
  </body>
</html>