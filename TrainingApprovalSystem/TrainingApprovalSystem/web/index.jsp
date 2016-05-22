<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <title>Training Approval System</title>
    </head>

    <body>
        <c:if test="${sessionScope['auth.user'].role == 'USER'}">
            <myTagLib:userHomePage />
        </c:if>
        <c:if test="${sessionScope['auth.user'].role == 'ADMIN'}">
            <myTagLib:adminHomePage />
        </c:if>
    </body>

</html>
