<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="CheckLogin.do" method="POST">
            <h1> TrainingApprovalSystem </h1>
            <input type="text" name="username" value="" /> <br> <br>
            <input type="text" name="password" value="" /> <br> <br>
            <input type="submit" value="Submit" name="submit" />
            <h3 style="color: darkred"> Wrong username or password! </h3>
        </form>
    </body>
</html>

