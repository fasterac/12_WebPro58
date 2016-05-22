<%@include file="/WEB-INF/tags/importlib.tag" %>
<%@tag description="Admin home page" pageEncoding="UTF-8" body-content="empty" %>

<h1>Admin Panel, Welcome ${sessionScope['auth.user'].pname_th}${sessionScope['auth.user'].fname_th}</h1>

<a href="javascript:;">ดูคำร้อง</a><br />
<a href="javascript:;">ดูสมาชิก</a><br />
<br />

<a href="logout.do">ออกจากระบบ</a>