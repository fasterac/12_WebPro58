<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html >
  <head>
    <meta charset="UTF-8">
    <title>Login</title>
        <link rel="stylesheet" href="css/login_input.css">
        
  </head>

  <body>
    <div class="wrapper">
	<div class="container">
		<h1>✎Login</h1>
        <form class="form" action="CheckLogin.do" method="POST">
            <h1> TrainingApprovalSystem </h1><br>
            Username: <input type="text" name="username" /> <br> <br>
            Password: <input type="password" name="password" /> <br> <br>
            <!--<input type="submit" value="Submit" name="submit" />-->
            <button type="submit" value="Submit" name="submit" id="login-button">Submit</button>
            <h3 style="color: darkred"> Wrong username or password! </h3>
        </form>
                </div>
<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="js/index.js"></script>

    
    
    
  </body>
</html>

