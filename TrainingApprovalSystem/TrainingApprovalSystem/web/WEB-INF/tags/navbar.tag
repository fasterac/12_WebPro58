<%@include file="/WEB-INF/tags/importlib.tag" %>
<%@tag pageEncoding="UTF-8" body-content="empty" %>

<%@ attribute name="currentUser" type="model.User" required="true" rtexprvalue="true" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">ระบบขอไปอบรมและนำเสนอผลงาน</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="index.jsp">หน้าหลัก</a></li>

            <c:if test="${currentUser.role == 'USER'}">
                <li><a href="newform.jsp">ยื่นคำร้อง</a></li>
                <li><a href="showallsendform.jsp">ดูคำร้อง</a></li>
            </c:if>
            <c:if test="${currentUser.role == 'ADMIN'}">
                <li><a href="newform.jsp">ยื่นคำร้อง</a></li>
                <li><a href="showallsendform.jsp">ดูคำร้อง</a></li>
                <li><a href="showalluser.jsp">ดูสมาชิก</a></li>
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="logout.do"><span class="glyphicon glyphicon-user"></span> <span class="glyphicon glyphicon-log-in"></span> ออกจากระบบ</a></li>
        </ul>
    </div>
</nav>