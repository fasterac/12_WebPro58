<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>approval form</title>
    
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Training Approval System - Administrator Panel</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <script type="text/javascript" src="java/javascript.js"></script>
    <script type="text/javascript" src="java/fileupload.js"></script>
    <link rel="stylesheet" href="css/style.css">
    
    
  </head>

  <body onload="load()">
     
   <div class="wrapper">
	
		 
                <div class="box">
                	
		
<!--................................. CONTENT  FORM...................................................................-->  
                    
                    <h2>✎รายชื่อแบบฟอร์มสำหรับอนุมัติ<br><br></h2>
                    
                    <fieldset> 
        <h1>Admin Page</h1> 
        <input type="submit" value="Logout" name="logout" /> <br>
        
        <table border="1" width="800">
            <thead>
                <tr>
                    <th>full form</th>
                    <th>form_ID</th>
                    <th>request by</th>
                    <th>destination</th>
                    <th>duration</th>
                    <th>cost</th>
                    <th>status</th>
                    <th>confirm button</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><input type="submit" value="0001" name="0001" /></td>
                    <td>0001</td>
                    <td>prof.aaa srrr</td>
                    <td>aland</td>
                    <td>10-apr-2558/11-apr-2558</td>
                    <td>65536</td>
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
                </tr>
            </tbody>
        </table>

        [btn:look full form] formID username(requestBy) where duration cost status[v] [btn:confirm]
        
    </div>
      
    </div>
      
       
	
	
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="js/index.js"></script>

    
    
    
  </body>
</html>

