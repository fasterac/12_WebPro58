<%@include file="/WEB-INF/tags/importlib.tag" %>
<%@tag description="User home page" pageEncoding="UTF-8" body-content="empty" %>

<h1>User Panel, Welcome ${sessionScope['auth.user'].pname_th}${sessionScope['auth.user'].fname_th}</h1>

<a href="newform.jsp">ยื่นคำร้อง</a><br />
<a href="showallsendform.jsp">ดูคำร้อง</a><br />
<br />

<a href="logout.do">ออกจากระบบ</a>