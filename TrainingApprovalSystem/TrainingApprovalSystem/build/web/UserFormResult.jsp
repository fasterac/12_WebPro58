<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Training Approval System - View Form</title>
    </head>
    <form action="UserFormResultServlet" method="POST">
        
        <!-- temp header -->
        <button type="submit" value="Back" name="forwarder">Back</button>
        <button type="submit" name="forwarder" value="Home">Home</button>
        <button type="submit" name="forwarder" value="CreateForm">CreateForm</button>
        <button type="submit" name="forwarder" value="TrackApproval">TrackApproval</button>
        <button type="submit" name="forwarder" value="Logout">Logout</button><br>
        
        <!--show status of this form-->
        <c:if test="${sessionScope.sesForm.getStatus_id() == 0}" >
            <h3><%= "This form is pending" %></h3>
            <button type="submit" value="Cancle" name="changeStatus">Cancle Ths Form</button>
        </c:if>
        <c:if test="${sessionScope.sesForm.getStatus_id() == 1}" >
            <h3><%= "This form has been Approved" %></h3>
            <button type="submit" value="Cancle" name="changeStatus">Cancle Ths Form</button>
        </c:if>
        <c:if test="${sessionScope.sesForm.getStatus_id() == 2}" >
            <h3><%= "This form has been Rejected" %></h3>
            <button type="submit" value="Cancle" name="changeStatus">Cancle Ths Form</button>
        </c:if>
        <c:if test="${sessionScope.sesForm.getStatus_id() == 3}" >
            <h3><%= "This form has been Cancelled" %></h3>
        </c:if>
        <br>
        
        
        <body>
            Form number: ${sessionScope.sesFormNumber}<br>
            Status ${sessionScope.sesForm.getStatus_id()}<br>
            ข้าพเจ้า getname surname ตำแหน่าง getrole <br>
            มีความประสงค์ ขออนุมัติเข้าาร่วมอบรม/สัมนา หลักสูตร ${sessionScope.sesForm.getCourse()} <br>
            จัดโดย ${sessionScope.sesForm.getOrganizer()}
        </body>
    </form>
</html>
