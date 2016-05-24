<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
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

    <head>
        <title>Training Approval System</title>
    </head>

    <body>
        <h1>รายการฟอร์มที่ยื่น</h1>

        <a href="index.jsp">กลับหน้าแรก</a>
        <c:if test="${currentUser.role == 'USER'}">
            <c:if test="${forms == null}">
                no form to show here... you didnt sent form
            </c:if>
        </c:if>
        
        <c:if test="${forms != null}">
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
                            <% Form form = (Form)pageContext.getAttribute("form"); //${form.form_date} %>                            
                            <td><%= showDate(form.getStart_date() , form.getEnd_date()) %></td>
                            <td><%= showOneDate(form.getForm_date()) %></td>
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
        
    </body>

</html>

<%! public String showDate(Date date1, Date date2) {
String returnser = "";
if(date1.after(date2)){ //swap
    Date temp = date1;
    date1 = date2;
    date2 = temp;
}

//month and year is equal
if((date1.getMonth()+"").equals((date2.getMonth())+"") && (date1.getYear()+"").equals((date2.getYear())+"")){
    returnser = returnser + (date1.getDate() + "-" + date2.getDate());
    switch (date1.getMonth()){
        case 0: returnser = returnser + (" ม.ค. "); break;
        case 1: returnser = returnser + (" ก.พ. "); break;
        case 2: returnser = returnser + (" มี.ค. "); break;
        case 3: returnser = returnser + (" เม.ษ. "); break;
        case 4: returnser = returnser + (" พ.ค. "); break;
        case 5: returnser = returnser + (" มิ.ย. "); break;
        case 6: returnser = returnser + (" ก.ค. "); break;
        case 7: returnser = returnser + (" ส.ค. "); break;
        case 8: returnser = returnser + (" ก.ย. "); break;
        case 9: returnser = returnser + (" ต.ค. "); break;
        case 10: returnser = returnser + (" พ.ย. "); break;
        case 11: returnser = returnser + (" ธ.ค. "); break;
    }
    returnser = returnser + (date1.getYear() - 57);
}

//month not equal but year equal
else if((!((date1.getMonth()+"").equals((date2.getMonth())+""))) && (date1.getYear()+"").equals((date2.getYear())+"")){
    returnser = returnser + date1.getDate();
    switch (date1.getMonth()){
        case 0: returnser = returnser + (" ม.ค. - "); break;
        case 1: returnser = returnser + (" ก.พ. - "); break;
        case 2: returnser = returnser + (" มี.ค. - "); break;
        case 3: returnser = returnser + (" เม.ษ. - "); break;
        case 4: returnser = returnser + (" พ.ค. - "); break;
        case 5: returnser = returnser + (" มิ.ย. - "); break;
        case 6: returnser = returnser + (" ก.ค. - "); break;
        case 7: returnser = returnser + (" ส.ค. - "); break;
        case 8: returnser = returnser + (" ก.ย. - "); break;
        case 9: returnser = returnser + (" ต.ค. - "); break;
        case 10: returnser = returnser + (" พ.ย. - "); break;
        case 11: returnser = returnser + (" ธ.ค. - "); break;
    }
    returnser = returnser + date2.getDate();
    switch (date2.getMonth()){
        case 0: returnser = returnser + (" ม.ค. "); break;
        case 1: returnser = returnser + (" ก.พ. "); break;
        case 2: returnser = returnser + (" มี.ค. "); break;
        case 3: returnser = returnser + (" เม.ษ. "); break;
        case 4: returnser = returnser + (" พ.ค. "); break;
        case 5: returnser = returnser + (" มิ.ย. "); break;
        case 6: returnser = returnser + (" ก.ค. "); break;
        case 7: returnser = returnser + (" ส.ค. "); break;
        case 8: returnser = returnser + (" ก.ย. "); break;
        case 9: returnser = returnser + (" ต.ค. "); break;
        case 10: returnser = returnser + (" พ.ย. "); break;
        case 11: returnser = returnser + (" ธ.ค. "); break;
    }
    returnser = returnser + (date1.getYear() - 57);
}
else{
    returnser = returnser + date1.getDate();
    switch (date1.getMonth()){
        case 0: returnser = returnser + (" ม.ค. "); break;
        case 1: returnser = returnser + (" ก.พ. "); break;
        case 2: returnser = returnser + (" มี.ค. "); break;
        case 3: returnser = returnser + (" เม.ษ. "); break;
        case 4: returnser = returnser + (" พ.ค. "); break;
        case 5: returnser = returnser + (" มิ.ย. "); break;
        case 6: returnser = returnser + (" ก.ค. "); break;
        case 7: returnser = returnser + (" ส.ค. "); break;
        case 8: returnser = returnser + (" ก.ย. "); break;
        case 9: returnser = returnser + (" ต.ค. "); break;
        case 10: returnser = returnser + (" พ.ย. "); break;
        case 11: returnser = returnser + (" ธ.ค. "); break;
    }
    returnser = returnser + (date1.getYear() - 57) + " - ";

    returnser = returnser + date2.getDate();
    switch (date2.getMonth()){
        case 0: returnser = returnser + (" ม.ค. "); break;
        case 1: returnser = returnser + (" ก.พ. "); break;
        case 2: returnser = returnser + (" มี.ค. "); break;
        case 3: returnser = returnser + (" เม.ษ. "); break;
        case 4: returnser = returnser + (" พ.ค. "); break;
        case 5: returnser = returnser + (" มิ.ย. "); break;
        case 6: returnser = returnser + (" ก.ค. "); break;
        case 7: returnser = returnser + (" ส.ค. "); break;
        case 8: returnser = returnser + (" ก.ย. "); break;
        case 9: returnser = returnser + (" ต.ค. "); break;
        case 10: returnser = returnser + (" พ.ย. "); break;
        case 11: returnser = returnser + (" ธ.ค. "); break;
    }
    returnser = returnser + (date2.getYear() - 57);
}
return returnser;
} %>

<%! public String showOneDate(Date date1){
    String returner =  "";
    returner = returner + date1.getDate();
    switch (date1.getMonth()){
        case 0: returner = returner + (" ม.ค. "); break;
        case 1: returner = returner + (" ก.พ. "); break;
        case 2: returner = returner + (" มี.ค. "); break;
        case 3: returner = returner + (" เม.ษ. "); break;
        case 4: returner = returner + (" พ.ค. "); break;
        case 5: returner = returner + (" มิ.ย. "); break;
        case 6: returner = returner + (" ก.ค. "); break;
        case 7: returner = returner + (" ส.ค. "); break;
        case 8: returner = returner + (" ก.ย. "); break;
        case 9: returner = returner + (" ต.ค. "); break;
        case 10: returner = returner + (" พ.ย. "); break;
        case 11: returner = returner + (" ธ.ค. "); break;
    }
    returner = returner + (date1.getYear() - 57);
    return returner;
} %>