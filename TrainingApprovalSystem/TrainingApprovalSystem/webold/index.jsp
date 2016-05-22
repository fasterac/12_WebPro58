<%@ page import="utility.Authorization" %>
<%@ page import="utility.DataConnector" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%
    Authorization authorization = new Authorization(DataConnector.getDBConnection(request), session);

    switch (authorization.getCurrentUser().getRole()) {
        case USER: response.sendRedirect("adminmainpage.jsp"); return;
        case ADMIN: response.sendRedirect("usermainpage.jsp"); return;
    }
%>

