<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Training Approval System</title>
    </head>

    <body>
        <h1>ยื่นฟอร์มสำเร็จ</h1>

        รหัสฟอร์ม : ${requestScope['form.new'].id}<br />
        <br />

        <a href="index.jsp">กลับหน้าแรก</a>
    </body>

</html>
