<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <title>Training Approval System</title>
    </head>

    <body>
        <form action="login.do" method="POST">
            Username: <input type="text" name="username" id="username" placeholder="ชื่อผู้ใช้" /><br />
            Password: <input type="password" name="password" id="password" placeholder="รหัสผ่าน" /><br />
            <button type="submit">Login</button>
        </form>
    </body>

</html>
