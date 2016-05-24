<%@ page import="utility.DataConnector" %>
<%@ page import="factory.UserFactory" %>
<%@ include file="/WEB-INF/importlib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    pageContext.setAttribute("users", new UserFactory(DataConnector.getDBConnection(request)).all());
%>

<!DOCTYPE html>
<html>

    <head>
        <title>Training Approval System</title>
    </head>

    <body>
    <h1>รายชื่อผู้ใช้งาน</h1>

    <a href="index.jsp">กลับหน้าแรก</a>
    <table border="1">
        <thead>
            <th>รหัสผู้ใช้</th>
            <th>ชื่อ - นามสกุล</th>
            <th>อีเมลล์</th>
            <th>เบอร์โทรศัพท์</th>
            <th>ดูข้อมูล</th>
        </thead>

        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.pname_th}${user.fname_th} ${user.lname_th}</td>
                    <td>${user.email}</td>
                    <td>${user.mobile}</td>
                    <td><a href="viewuser.jsp?user_id=${user.id}">ดูข้อมูลและประวัติการจอง</a></td>
                </tr>
            </c:forEach>
        </tbody>

    </table>
    </body>

</html>
