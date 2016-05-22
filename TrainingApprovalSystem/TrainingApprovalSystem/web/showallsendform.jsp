<%@ page import="factory.FormFactory" %>
<%@ page import="utility.DataConnector" %>
<%@ page import="utility.Authorization" %>
<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User currentUser = new Authorization(DataConnector.getDBConnection(request), session).getCurrentUser();
    pageContext.setAttribute("forms", new FormFactory(DataConnector.getDBConnection(request)).findAllByUserID(currentUser.getId()));
%>

<!DOCTYPE html>
<html>

    <head>
        <title>Training Approval System</title>
    </head>

    <body>
        <h1>รายการฟอร์มที่ยื่น</h1>

        <a href="index.jsp">กลับหน้าแรก</a>
        <table>
            <thead>
                <th>รหัสฟอร์ม</th>
                <th>ชื่อคอสอบรบ</th>
                <th>วันที่อบรบ</th>
                <th>วันที่ยื่นฟอร์ม</th>
                <th>สถานะฟอร์ม</th>
                <th>ดูข้อมูล</th>
            </thead>

            <tbody>
                <c:forEach var="form" items="${forms}">
                    <tr>
                        <td>${form.id}</td>
                        <td>${form.course_name}</td>
                        <td>${form.start_date}</td>
                        <td>${form.form_date}</td>
                        <td>${form.status}</td>
                        <td><a href="viewsendform.jsp?form_id=${form.id}">ดูฟอร์ม</a></td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
    </body>

</html>
