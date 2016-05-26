<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

    <myTagLib:headhtml />

    <body>
        <c:if test="${sessionScope['auth.user'].role == 'USER'}">
            <myTagLib:userHomePage />
        </c:if>
        <c:if test="${sessionScope['auth.user'].role == 'ADMIN'}">
            <myTagLib:adminHomePage />
        </c:if>

        <myTagLib:footer />

        <myTagLib:scriptlib />
    </body>

</html>
