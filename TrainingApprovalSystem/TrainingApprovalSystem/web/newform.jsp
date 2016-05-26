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

    <myTagLib:headhtml />

    <body>

        <myTagLib:navbar currentUser="${sessionScope['auth.user']}" />

        <div class="page-wrap">

            <div class="container">

                <h1>แบบฟอร์มขออนุมัติ</h1>

                <c:if test="${sessionScope['form.error'] != null}">
                    <h2>${sessionScope['form.error']}</h2>
                </c:if>

                <hr />

                <form action="checknewform.do" method="POST" enctype="multipart/form-data">
                    <h2>รายละเอียด</h2>
                    <br />

                    <div class="form-group">
                        <label for="form-user-name">ชื่อ</label>
                        <input type="text" class="form-control" id="form-user-name" value="${user.pname_th}${user.fname_th}" disabled>
                    </div>
                    <div class="form-group">
                        <label for="form-user-surname">นามสกุล</label>
                        <input type="text" class="form-control" id="form-user-surname" value="${user.lname_th}" disabled>
                    </div>
                    <div class="form-group">
                        <label for="form-course_name">มีความประสงค์ ขออนุมัติเข้าร่วมอบรม/สัมนา หลักสูตร</label>
                        <input type="text" class="form-control" name="course_name" id="form-course_name" value="${sessionScope['form.param']['course_name']}">
                    </div>
                    <div class="form-group">
                        <label for="form-organizer_name">จัดโดย</label>
                        <input type="text" class="form-control" name="organizer_name" id="form-organizer_name" value="${sessionScope['form.param']['organizer_name']}">
                    </div>
                    <div class="form-group">
                        <label for="form-location_name">สถานที่จัด</label>
                        <input type="text" class="form-control" name="location_name" id="form-location_name" value="${sessionScope['form.param']['location_name']}">
                    </div>
                    <div class="form-group">
                        <label for="form-start_date">วันที่เริ่ม</label>
                        <div class="row">
                            <div class="col-md-6">
                                <input type="date" class="form-control" name="start_date" id="form-start_date" value="${sessionScope['form.param']['start_date']}">
                            </div>
                            <div class="col-md-6">
                                <input type="time" class="form-control" name="start_time" id="form-start_time" value="${sessionScope['form.param']['start_time']}">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="form-end_date">วันที่สิ้นสุด</label>
                        <div class="row">
                            <div class="col-md-6">
                                <input type="date" class="form-control" name="end_date" id="form-end_date" value="${sessionScope['form.param']['end_date']}">
                            </div>
                            <div class="col-md-6">
                                <input type="time" class="form-control" name="end_time" id="form-end_time" value="${sessionScope['form.param']['end_time']}">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="form-course_file">รายละเอียดการอบรม</label>
                        <input type="file" name="course_file" id="form-course_file" />
                        <p class="help-block">.zip หรือ .pdf เท่านั้น</p>
                    </div>

                    <hr />

                    <h2>ขออนุมัติสนับสนุนค่าใช้จ่าย</h2>
                    <div class="form-group">
                        <label for="form-register_cost">1. ค่าลงทะเบียน</label>
                        <input type="text" class="form-control" name="register_cost" id="form-register_cost" value="${sessionScope['form.param']['register_cost']}">
                    </div>
                    <div class="form-group">
                        <label for="form-use_expense">2.ค่าใช้จ่ายในการเข้าร่วมอบรม/สัมมนา (กรณีสถานที่จัดอยู่ต่างจังหวัด/ต่างประเทศ)</label>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="use_expense" id="form-use_expense" ${sessionScope['form.param']['use_expense'] != null ? "checked" : ""}> เสนอเบิกงบสำหรับเข้าร่วมอมรม
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="form-room_night_amount">2.1 ที่พัก (คืน)</label>
                        <input type="number" class="form-control" name="room_night_amount" id="form-room_night_amount" value="${sessionScope['form.param']['room_night_amount']}" disabled>
                    </div>
                    <div class="form-group">
                        <label for="form-room_night_each">คืนละ (บาท)</label>
                        <input type="text" class="form-control" name="room_night_each" id="form-room_night_each" value="${sessionScope['form.param']['room_night_each']}" disabled>
                    </div>
                    <div class="form-group">
                        <label for="form-day_amount">2.2 เบี้ยเลี้ยง (วัน)</label>
                        <input type="number" class="form-control" name="day_amount" id="form-day_amount" value="${sessionScope['form.param']['day_amount']}" disabled>
                    </div>
                    <div class="form-group">
                        <label for="form-day_cost_each">วันละ (บาท)</label>
                        <input type="text" class="form-control" name="day_cost_each" id="form-day_cost_each" value="${sessionScope['form.param']['day_cost_each']}" disabled>
                    </div>
                    <div class="form-group">
                        <label for="form-travel_cost">2.3 ค่าพาหนะ (บาท)</label>
                        <input type="text" class="form-control" name="travel_cost" id="form-travel_cost" value="${sessionScope['form.param']['travel_cost']}" disabled>
                    </div>

                    <hr />

                    <h2>การปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม</h2>
                    <div class="form-group">
                        <label>3. การนำความรู้ที่ได้จากการเข้าร่วมอบรม/สัมมนา มาใช้ในการปรับปรุงการทำงาน</label>
                    </div>
                    <div class="form-group">
                        <label for="form-detail">3.1 ปรับปรุงการเรียนการสอน/ปรับปรุงการทำงาน/สร้างสรรค์งาน/สร้างนวัตกรรม</label>
                        <textarea class="form-control" name="detail" id="form-detail" rows="4">${sessionScope['form.param']['detail']}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="form-room_night_amount">3.2 ระยะเวลาที่ใช้ในการปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม ภายหลังเข้าร่วมอบรม/สัมมนา (ไม่เกิน 1 ปี) (หน่วยเป็นวัน)</label>
                        <input type="number" class="form-control" name="date_duration" id="form-date_duration" value="${sessionScope['form.param']['date_duration']}">
                    </div>
                    <div class="form-group">
                        <label for="form-room_night_amount">3.3 ระยะเวลาการส่งเอกสารหลักฐานอ้างอิงการปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม (ไม่เกิน 1 ปี) (หน่วยเป็นวัน)</label>
                        <input type="number" class="form-control" name="report_sent_duration" id="form-report_sent_duration" value="${sessionScope['form.param']['report_sent_duration']}">
                    </div>

                    <p>
                        หมายเหตุ* เอกสารหลักฐานอ้างอิง ตามข้อ 3.3<br />
                        &nbsp;&nbsp;&nbsp;&nbsp;ส่วนวิชาการ เช่น เอกสารรายงานการปรับปรุงแผนการสอน/ปรับปรุงเนื้อหาการสอน/ปรับปรุงเอกสารการสอน
                        เนื้อหารายวิชาหรือหลักสูตรอบรมใหม่ที่เปิดผลมาจากการอบรม ฯลฯ<br />
                        &nbsp;&nbsp;&nbsp;&nbsp;ส่วนสนับสนุนวิชาการ เช่น เอกสารรายงานการปรับปรุงกระบวนการทำงาน การประเมินผลสัมฤทธิ์ของการ
                        ทำงานภายหลัง เข้ารับการอบรม/สัมมนาโดยหัวหน้างาน/ผู้อำนวยการส่วนสนับสนุนวิชาการ/ผู้ช่วยคณบดี/รองคณบดี เป็นต้น
                    </p>

                    <button type="submit" class="btn btn-success">ยื่นฟอร์ม</button> <a href="index.jsp" class="btn btn-danger">ยกเลิกและกลับหน้าหลัก</a>
                </form>
            </div>

        </div>

        <myTagLib:footer />

        <myTagLib:scriptlib />
        <script>
            $('#form-use_expense').change(function() {
                if($(this).is(":checked")) {
                    $("#form-room_night_amount").removeAttr("disabled");
                    $("#form-room_night_each").removeAttr("disabled");
                    $("#form-day_amount").removeAttr("disabled");
                    $("#form-day_cost_each").removeAttr("disabled");
                    $("#form-travel_cost").removeAttr("disabled");
                } else {
                    $("#form-room_night_amount").attr("disabled", true);
                    $("#form-room_night_each").attr("disabled", true);
                    $("#form-day_amount").attr("disabled", true);
                    $("#form-day_cost_each").attr("disabled", true);
                    $("#form-travel_cost").attr("disabled", true);
                }
            });
        </script>
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
