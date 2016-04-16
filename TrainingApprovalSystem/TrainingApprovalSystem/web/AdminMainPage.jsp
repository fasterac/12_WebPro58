<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %> 
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
                    
                    <h2>✎รายชื่อแบบฟอร์มสำหรับอนุมัติ<br><br></h2>
                    
                    <fieldset>
            <%! User user = new User() ; %> 
            <% user = (User) session.getAttribute("reqUser"); %>
        <h1>Admin Page</h1> 
        <h2>Welcome ${sessionScope.reqUser.getFirstname()} ${sessionScope.reqUser.getLastname()}</h2>
        <input class="button" type="submit" value="Logout" name="logout" /> <br>
        
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
            <tbody>
                <tr>
                    <td></td>
                    <td>${sessionScope.reqUser.getFirstname()} ${sessionScope.reqUser.getLastname()}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><select name="status" >
                            <option>pending</option>
                            <option>approved</option>
                            <option>reject</option>
                            <option>cancle</option>
                        </select></td>
                    <td><input type="submit" value="confirm change" name="confirm" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
        
        
        [btn:look full form] formID username(requestBy) where duration cost status[v] [btn:confirm]
        
    </div>
      
    </div>
      
       
	</fieldset> 
	
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="js/index.js"></script>

    
    
    </form>
  </body>
</html>

