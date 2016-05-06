<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
        ${sessionScope.sesFormNumber}<br>
        ข้าพเจ้า getname surname ตำแหน่าง getrole <br>
        มีความประสงค์ ขออนุมัติเข้าาร่วมอบรม/สัมนา หลักสูตร ${sessionScope.sesForm.getCourse()} <br>
        จัดโดย ${sessionScope.sesForm.getOrganizer()}
    </body>
</html>
