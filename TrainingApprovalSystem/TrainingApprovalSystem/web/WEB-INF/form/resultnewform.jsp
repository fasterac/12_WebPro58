<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <myTagLib:headhtml />

    <body>

        <myTagLib:navbar currentUser="${sessionScope['auth.user']}" />

        <div class="page-wrap">
            <div class="container">
                <div class="page-header">
                    <h1>ยื่นฟอร์มสำเร็จ</h1>
                </div>

                รหัสฟอร์ม : ${requestScope['form.new'].id}<br />
                <br />

                <a href="index.jsp" class="btn btn-info">กลับหน้าแรก</a>
            </div>
        </div>

        <myTagLib:footer />

        <myTagLib:scriptlib />
    </body>

</html>
