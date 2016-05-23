<%@ page import="java.sql.Connection" %>
<%@ page import="utility.DataConnector" %>
<%@ page import="utility.Authorization" %>
<%@ page import="utility.FileUploadHelper" %>
<%@ page import="factory.UserFactory" %>
<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Connection connection = DataConnector.getDBConnection(request);
    pageContext.setAttribute("user",
            new UserFactory(connection).find(new Authorization(connection, session).getCurrentUser().getId()));
%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <title>Training Approval System</title>
    </head>

    <body>
        <h1>แบบฟอร์มขออนุมัติ</h1>

        <c:if test="${sessionScope['form.error'] != null}">
            <h2>${sessionScope['form.error']}</h2>
        </c:if>

        <form action="checknewform.do" method="POST" enctype="multipart/form-data">
            <h2>รายละเอียด</h2>
            ชื่อ : <input type="text" value="${user.pname_th} ${user.fname_th}" disabled><br />
            นามสกุล : <input type="text" value="${user.lname_th}" disabled><br />
            มีความประสงค์ ขออนุมัติเข้าร่วมอบรม / สัมนา หลักสูตร : <input type="text" name="course_name" value="${sessionScope['form.param']['course_name']}"><br />
            จัดโดย : <input type="text" name="organizer_name" value="${sessionScope['form.param']['organizer_name']}"><br />
            สถานที่จัด : <input type="text" name="location_name" value="${sessionScope['form.param']['location_name']}"><br />
            วันที่เริ่ม : <input type="text" name="start_date" value="${sessionScope['form.param']['start_date']}"><br />
            วันที่สิ้นสุด : <input type="text" name="end_date" value="${sessionScope['form.param']['end_date']}"><br />
            รายละเอียดการอบรม (.zip หรือ .pdf) : <input type="file" name="course_file" />

            <hr />

            <h2>ขออนุมัติสนับสนุนค่าใช้จ่าย</h2>
            1.ค่าลงทะเบียน : <input type="text" name="register_cost" value="${sessionScope['form.param']['register_cost']}"> บาท<br />
            <br />
            2.ค่าใช้จ่ายในการเข้าร่วมอบรม/สัมมนา (กรณีสถานที่จัดอยู่ต่างจังหวัด/ต่างประเทศ) : <br />
            <input type="checkbox" name="use_expense" value="true" ${sessionScope['form.param']['use_expense'] != null ? "checked" : ""} /> เสนอเบิกงบสำหรับเข้าร่วมอมรม<br />
            <br />
            2.1 ที่พัก : <input type="number" name="room_night_amount" value="${sessionScope['form.param']['room_night_amount']}"> คืน<br />
            คืนละ : <input type="text" name="room_night_each" value="${sessionScope['form.param']['room_night_each']}"> บาท<br />
            2.2 เบี้ยเลี้ยง :<input type="number"name="day_amount" value="${sessionScope['form.param']['day_amount']}"> วัน<br />
            วันละ : <input type="text" name="day_cost_each" value="${sessionScope['form.param']['day_cost_each']}"> บาท<br />
            2.3 ค่าพาหนะ เป็นเงิน : <input type="text" name="travel_cost" value="${sessionScope['form.param']['travel_cost']}"> บาท<br />

            <hr />

            <h2>การปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม </h2>
            3. การนำความรู้ที่ได้จากการเข้าร่วมอบรม/สัมมนา มาใช้ในการปรับปรุงการทำงาน<br />
            <br />
            3.1 ปรับปรุงการเรียนการสอน/ปรับปรุงการทำงาน/สร้างสรรค์งาน/สร้างนวัตกรรม :<br />
            <textarea name="detail" rows="4" cols="100">${sessionScope['form.param']['detail']}</textarea><br />
            3.2 ระยะเวลาที่ใช้ในการปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม ภายหลังเข้าร่วมอบรม/สัมมนา (ไม่เกิน 1 ปี): <input type="number" name="date_duration" value="${sessionScope['form.param']['date_duration']}" /> วัน<br />
            3.3 ระยะเวลาการส่งเอกสารหลักฐานอ้างอิงการปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม (ไม่เกิน 1 ปี): <input type="number" name="report_sent_duration" value="${sessionScope['form.param']['report_sent_duration']}" /> วัน<br />
            <p>
                <small>
                    หมายเหตุ* เอกสารหลักฐานอ้างอิง ตามข้อ 3.3<br />
                    &nbsp;&nbsp;&nbsp;&nbsp;ส่วนวิชาการ เช่น เอกสารรายงานการปรับปรุงแผนการสอน/ปรับปรุงเนื้อหาการสอน/ปรับปรุงเอกสารการสอน
                    เนื้อหารายวิชาหรือหลักสูตรอบรมใหม่ที่เปิดผลมาจากการอบรม ฯลฯ<br />
                    &nbsp;&nbsp;&nbsp;&nbsp;ส่วนสนับสนุนวิชาการ เช่น เอกสารรายงานการปรับปรุงกระบวนการทำงาน การประเมินผลสัมฤทธิ์ของการ
                    ทำงานภายหลัง เข้ารับการอบรม/สัมมนาโดยหัวหน้างาน/ผู้อำนวยการส่วนสนับสนุนวิชาการ/ผู้ช่วยคณบดี/รองคณบดี เป็นต้น
                </small>
            </p>

            <button type="submit">ยื่นฟอร์ม</button> <a href="index.jsp">ยกเลิกและกลับหน้าหลัก</a>
        </form>

    </body>

</html>

<%
    session.setAttribute("form.error", null);
    session.setAttribute("form.param", null);
    session.setAttribute("form.new", null);

    if(session.getAttribute("form.course_file_name") != null) {
        FileUploadHelper fileUploadHelper = new FileUploadHelper(request);
        fileUploadHelper.removeFile(fileUploadHelper.getTempFile((String) session.getAttribute("form.course_file_name")));
        session.setAttribute("form.course_file_name", null);
    }
%>
