<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html >
<head>
    <meta charset="UTF-8">
    <title>Training Approval System - Login</title>
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

            <c:if test="${sessionScope.loginErrorMassage} == null">
                printtest
            </c:if>

            <% String errorMassage = (String) session.getAttribute("sesLoginMassage"); %>
            <% if(errorMassage == null) {errorMassage = "";} %>

            <br><h3 style="color: #340e21"> <%= errorMassage %> </h3>
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

