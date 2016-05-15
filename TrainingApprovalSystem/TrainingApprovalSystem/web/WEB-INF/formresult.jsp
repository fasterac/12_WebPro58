<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="AdminMainPage.jsp"><button type="submit">Back to Home</button></a>
        <a href="logout"><button type="submit">Logout</button></a><br>
        
        <form action="updateformresult" method="POST">
            <input type="hidden" name="form_id" value="${requestScope.form.form_id}">
            <c:if test="${requestScope.form.status_id == 0}" >
                <button type="submit" value="APPROVED" name="changeStatus">Approve</button>
                <button type="submit" value="REJECTED" name="changeStatus">Reject</button>
                <button type="submit" value="CANCEL" name="changeStatus">Cancel This Form</button>
            </c:if>
            <c:if test="${requestScope.form.status_id == 1}" >
                <%= "This form has been Approved" %>
                <button type="submit" value="CANCEL" name="changeStatus">Cancel This Form</button>
            </c:if>
            <c:if test="${requestScope.form.status_id == 2}" >
                <%= "This form has been Rejected" %>
                <button type="submit" value="CANCEL" name="changeStatus">Cancel This Form</button>
            </c:if>
            <c:if test="${requestScope.form.status_id == 3}" >
                <%= "This form has been Cancelled" %>
            </c:if>
        </form>

        Form number: ${requestScope.form.form_id}<br>
        Status ${requestScope.form.status_id}<br>
        ข้าพเจ้า ${sessionScope.user.firstname} ${sessionScope.user.lastname} ตำแหน่ง ${sessionScope.user.role} <br>
        มีความประสงค์ ขออนุมัติเข้าร่วมอบรม/สัมนา หลักสูตร ${requestScope.form.course} <br>
        จัดโดย ${requestScope.form.organizer}
    </body>
</html>
