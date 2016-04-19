<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %> 
<%@page import="javax.servlet.http.HttpServletRequest" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>user_main_page</title>
<link href='http://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'/>
<link href="css/welcome.css" rel="stylesheet" type="text/css" />

</head>
<body>
    <form action="UserProcessServlet" method="POST">
      <%! User user = new User() ; %> 
      <% user = (User) session.getAttribute("reqUser"); %>
<div class="wrapper">
  <div class="logo-menu-container">
       
    <div class="logo">ระบบขอไปอบรมและนำเสนอผลงาน</div>
    <div class="menu">
        <ul>
            <li><input type="submit" value="Home" name="forwarder" /></li>
            <li><input type="submit" value="Logout" name="forwarder" /> </li>
            <li><input type="submit" value="CreateForm" name="forwarder" /></li>
            <li><input type="submit" value="TrackApproval" name="forwarder" /></li>
      </ul>
    </div>
  </div>
  
  <div class="clear"></div>
  <div class="page">
    
    <div class="clear"></div>
    <div class="left-column">
      <div class="dark-panel">
        <div class="dark-panel-top"></div>
        <div class="dark-panel-center">
          <ul>
            <li>
              <h1>ยินดีต้อนรับ</h1>
            </li>
            <li>
              <p>คุณได้เข้าสู่ระบบเรียบร้อยแล้ว</p>
            </li>
        </div>
        <div class="dark-panel-center"></div>
        <div class="dark-panel-bottom"></div>
      </div>
    </div>  
    <div class="right-column">
      <div class="right-column-content">
        <div class="right-column-content-heading">
<!--................................................Edit Content..................................................................................-->            
          <h1>คุณ <%= user.getFirstname()%> <%= user.getLastname() %></h1><br>
          <h2>ผู้ใช้: <%= user.getRole()%></h2>
<!--................................................Edit Content..................................................................................--> 
        </div>
        <div class="right-column-content-img-left"> <img src="images/user.jpg" alt="banner" /> </div>
        <div class="right-column-content-content">
          <p><p><p><p><p><p>
        </div>
         <div class="right-column-content-content">
<!--................................................Edit Content..................................................................................-->  
          <p>รายละเอียดของบุคคลนั้นๆ
            lectus. Cras eu risus eu enim semper vulputate. Integer a lorem lorem, nec convallis elit. </p>
          <p>Nam metus justo, consequat at lacinia sit amet, adipiscing at dui. Quisque eu velit at velit accumsan suscipit sit amet ac velit. Pellentesque tempus, dolor ac tincidunt ma.</p>
          <br><br><br><br><br><br><br><br>
<!--................................................Edit Content..................................................................................-->  
         </div>
        </div>
      </div>
    
    </div>
    
  </div>
  


  
<div class="clear"></div>

<div class="copyrights">ระบบขอไปอบรมและนำเสนอผลงาน
  <div class="copyrights-bottom"></div>
</div>
</form>
</body>
</html>

