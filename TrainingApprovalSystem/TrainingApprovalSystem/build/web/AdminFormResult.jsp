<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Training Approval System - View all form</title>
    </head>
    <form action="AdminFormResultServlet" method="POST">
        
        <c:if test="${sessionScope.sesForm.getStatus_id() == 0}" >
            <button type="submit" value="Approve" name="changeStatus">Approve</button>
            <button type="submit" value="Reject" name="changeStatus">Reject</button>
            <button type="submit" value="Cancle" name="changeStatus">Cancle Ths Form</button>
        </c:if>
        <c:if test="${sessionScope.sesForm.getStatus_id() == 1}" >
            <%= "This form has been Approved" %>
            <button type="submit" value="Cancle" name="changeStatus">Cancle Ths Form</button>
        </c:if>
        <c:if test="${sessionScope.sesForm.getStatus_id() == 2}" >
            <%= "This form has been Rejected" %>
            <button type="submit" value="Cancle" name="changeStatus">Cancle Ths Form</button>
        </c:if>
        <c:if test="${sessionScope.sesForm.getStatus_id() == 3}" >
            <%= "This form has been Cancelled" %>
        </c:if>
        
        <button type="submit" value="Back" name="forwarder">Back to Home</button>
        <button type="submit" value="Logout" name="forwarder">Logout</button><br>
        <body>
            Form number: ${sessionScope.sesFormNumber}<br>
            Status ${sessionScope.sesForm.getStatus_id()}<br>
            ข้าพเจ้า getname surname ตำแหน่าง getrole <br>
            มีความประสงค์ ขออนุมัติเข้าาร่วมอบรม/สัมนา หลักสูตร ${sessionScope.sesForm.getCourse()} <br>
            จัดโดย ${sessionScope.sesForm.getOrganizer()}
        </body>
    </form>
</html>
