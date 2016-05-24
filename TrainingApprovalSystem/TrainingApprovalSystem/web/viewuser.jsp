<%@ page import="utility.DataConnector" %>
<%@ page import="factory.UserFactory" %>
<%@ page import="factory.FormFactory" %>
<%@ page import="factory.TeacherFactory" %>
<%@ page import="factory.StaffFactory" %>
<%@ include file="/WEB-INF/importlib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    pageContext.setAttribute("user", new UserFactory(DataConnector.getDBConnection(request))
            .find(Integer.parseInt(request.getParameter("user_id"))));
    pageContext.setAttribute("forms", new FormFactory(DataConnector.getDBConnection(request))
            .findAllByUserID(((User) pageContext.getAttribute("user")).getId()));

    switch (((User) pageContext.getAttribute("user")).getType()) {
        case TEACHER:
            pageContext.setAttribute("teacher", new TeacherFactory(DataConnector.getDBConnection(request))
                    .findByUserID(Integer.parseInt(request.getParameter("user_id"))));
            break;
        case STAFF:
            pageContext.setAttribute("staff", new StaffFactory(DataConnector.getDBConnection(request))
                    .findByUserID(Integer.parseInt(request.getParameter("user_id"))));
            break;
    }
%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <title>Training Approval System</title>
    </head>

    <body>
        <h1>ข้อมูลสมาชิก</h1>

        <c:if test="${param['from'] != null}">
            <a href="viewsendform.jsp?form_id=${param['from']}">ย้อนกลับ</a>
        </c:if>
        <c:if test="${param['from'] == null}">
            <a href="showalluser.jsp">ย้อนกลับ</a>
        </c:if>

        <hr />

        ชื่อ - นามสกุล : ${user.pname_th}${user.fname_th} ${user.lname_th}<br />
        ชื่อ - นามสกุล (ภาษาอังกฤษ)​ : ${user.pname_en}${user.fname_en} ${user.lname_en}<br />
        อีเมลล์ : ${user.email}<br />
        เบอร์โทรศัพท์ : ${user.mobile}<br />
        ประเภท : ${user.type}<br />
        <c:if test="${user.type == 'TEACHER'}">
            ตำแหน่ง : ${teacher.position}<br />
            สถานะ : ${teacher.status}<br />
        </c:if>
        <c:if test="${user.type == 'STAFF'}">
            ฝ่าย : ${staff.work_section}<br />
        </c:if>
        ชื่อบัญชีผู้ใช้ : ${user.username}<br />
        ประเภทบัญชีผู้ใช้ : ${user.type}<br />

        <hr />

        <h2>ประวัติการยื่นคำร้อง</h2>

        <table border="1">
            <thead>
                <th>รหัสฟอร์ม</th>
                <th>ชื่อคอสอบรบ</th>
                <th>วันที่อบรบ</th>
                <th>วันที่ยื่นฟอร์ม</th>
                <th>สถานะฟอร์ม</th>
                <c:if test="${sessionScope['auth.user'].role == 'ADMIN'}">
                    <th>ผู้ยื่นฟอร์ม</th>
                </c:if>
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
                        <c:if test="${sessionScope['auth.user'].role == 'ADMIN'}">
                            <td>${form.user.pname_th}${form.user.fname_th} ${form.user.lname_th}</td>
                        </c:if>
                        <td><a href="viewsendform.jsp?form_id=${form.id}">ดูฟอร์ม</a></td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
    </body>

</html>
