<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <title>Training Approval System</title>
    </head>

    <body>
        <h2>ยืนยันการอนุมัติฟอร์มรหัส ${param['form_id']} หรือไม่</h2>
        <a href="approveform.do?form_id=${param['form_id']}">ยีนยัน</a> <a href="viewsendform.jsp?form_id=${param['form_id']}">ย้อนกลับ</a>
    </body>

</html>
