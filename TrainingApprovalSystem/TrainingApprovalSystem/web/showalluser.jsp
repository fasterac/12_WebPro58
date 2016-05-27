<%@ page import="utility.DataConnector" %>
<%@ page import="factory.UserFactory" %>
<%@ include file="/WEB-INF/importlib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="assets/css/bg01.css">

<%
    pageContext.setAttribute("users", new UserFactory(DataConnector.getDBConnection(request)).all());
%>

<!DOCTYPE html>
<html>

    <myTagLib:headhtml />

    <body>

        <myTagLib:navbar currentUser="${sessionScope['auth.user']}" />

        <div class="page-wrap">

            <div class="container">

                <div class="page-header">
                    <h1>รายชื่อผู้ใช้งาน</h1>
                </div>

                <table border="1" class="table table-bordered table-hover" id="showtable">
                    <thead>
                        <th>รหัสผู้ใช้</th>
                        <th>ชื่อ - นามสกุล</th>
                        <th>อีเมล</th>
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

            </div>

        </div>

        <myTagLib:footer />

        <myTagLib:scriptlib />
        <script>
            $(document).ready(function() {
                $('#showtable').DataTable();
            });
        </script>
    </body>

</html>
