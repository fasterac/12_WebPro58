<%-- 
    Document   : GodModeJSP
    Created on : Apr 9, 2016, 11:32:06 PM
    Author     : Fasterac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>God Mode!</h1>
        <form action="GodServlet" >
            Input SQL update: <input type="text" name="sqlexec"  size="100" /> 
            <input type="submit" value="executeUpdate(Stringsql)" name="exec" /> <br>
            
            Input SQL query: <input type="text" name="sqlexec"  size="100" /> 
            <input type="submit" value="executeQuery(Stringsql)" name="exec" />
        </form>
    </body>
</html>
