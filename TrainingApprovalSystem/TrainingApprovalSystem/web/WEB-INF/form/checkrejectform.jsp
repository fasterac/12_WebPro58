<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <myTagLib:headhtml />

    <body>

        <myTagLib:navbar currentUser="${sessionScope['auth.user']}" />

        <div class="page-wrap">
            <div class="container">
                <h2>ยืนยันการปฎิเสธฟอร์มรหัส ${param['form_id']} หรือไม่</h2>
                <a href="rejectform.do?form_id=${param['form_id']}" class="btn btn-reject">ยีนยัน</a> <a href="viewsendform.jsp?form_id=${param['form_id']}" class="btn-info">ย้อนกลับ</a>
            </div>
        </div>

        <myTagLib:footer />

        <myTagLib:scriptlib />
    </body>

</html>
