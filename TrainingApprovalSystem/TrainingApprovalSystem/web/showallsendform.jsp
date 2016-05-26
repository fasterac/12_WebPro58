<%@ page import="factory.FormFactory" %>
<%@ page import="utility.DataConnector" %>
<%@ page import="utility.Authorization" %>
<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User currentUser = new Authorization(DataConnector.getDBConnection(request), session).getCurrentUser();
    pageContext.setAttribute("currentUser", currentUser);
    if(currentUser.getRole() == User.Role.USER) {
        pageContext.setAttribute("forms", new FormFactory(DataConnector.getDBConnection(request)).findAllByUserID(currentUser.getId()));
    } else {
        pageContext.setAttribute("forms", new FormFactory(DataConnector.getDBConnection(request)).all());
    }
%>

<!DOCTYPE html>
<html>

    <myTagLib:headhtml />

    <body>

        <myTagLib:navbar currentUser="${sessionScope['auth.user']}" />

        <div class="page-wrap">

            <div class="container">

                <div class="page-header">
                    <h1>รายการฟอร์มที่ยื่น</h1>
                </div>

                <c:if test="${currentUser.role == 'USER'}">
                    <c:if test="${forms == null}">
                        no form show here... you didnt sent form
                    </c:if>
                </c:if>

                <c:if test="${forms != null}">
                    <table border="1" class="table table-bordered" id="showtable">
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
                </c:if>

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
