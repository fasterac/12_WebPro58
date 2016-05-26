<%@ page import="java.sql.Connection" %>
<%@ page import="utility.DataConnector" %>
<%@ page import="factory.FormFactory" %>
<%@ page import="utility.FileUploadHelper" %>
<%@ page import="java.util.Date" %>
<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Connection connection = DataConnector.getDBConnection(request);

    Form form = new FormFactory(connection).find(Integer.parseInt(request.getParameter("form_id")));

    pageContext.setAttribute("form", form);
    pageContext.setAttribute("fileUploadHelper", new FileUploadHelper(request));
    pageContext.setAttribute("historyForms", new FormFactory(DataConnector.getDBConnection(request)).findAllByUserID(form.getUser().getId()));
%>

<!DOCTYPE html>
<html>

    <myTagLib:headhtml />

    <body>

        <myTagLib:navbar currentUser="${sessionScope['auth.user']}" />

        <div class="page-wrap">

            <div class="container">

                <div class="page-header">
                    <h1>แบบฟอร์มขออนุมัติ</h1>
                </div>

                <c:if test="${sessionScope['form.result'] != null}">
                    <h2>${sessionScope['form.result']}</h2>
                </c:if>

                <h2>รายละเอียด</h2>
                สถานะฟอร์ม : ${form.status}<br />
                <c:if test="${sessionScope['auth.user'].role == 'ADMIN'}">
                    ผู้ยื่นฟอร์ม : <a href="viewuser.jsp?user_id=${form.user.id}&from=${form.id}">${form.user.pname_th}${form.user.fname_th} ${form.user.lname_th}</a><br />
                </c:if>
                อบรม / สัมนา หลักสูตร : ${form.course_name}<br />
                จัดโดย : ${form.organizer_name}<br />
                สถานที่จัด : ${form.location_name}<br />
                วันที่เริ่ม : ${String.format("%tF", form.start_date)}<br />
                วันที่สิ้นสุด : ${String.format("%tF", form.end_date)}<br />
                รายละเอียดการอบรม (.zip หรือ .pdf) : <a href="${fileUploadHelper.getCourseFileURL(form.course_file_path)}" target="_blank">${form.course_file_path}</a>

                <hr />

                <%-- calculate finance year --%>
                <%
                    Date date = new Date();
                    if(new Date().getMonth() < 9){
                        date.setYear(new Date().getYear()-1); date.setMonth(9); date.setDate(01);
                    }
                    pageContext.setAttribute("thisyear", date);
                %>
                <h3>ในปีงบประมาณ <%= date.getYear() + 2443 %> ได้เข้าร่วมอบรม/สัมมนา ดังนี้</h3>

                <table border="1" class="table table-bordered" id="showtable">
                    <thead>
                        <tr>
                            <th>รหัสฟอร์ม</th>
                            <th>ชื่อหลักสูตร</th>
                            <th>ระยะเวลาที่ไปอบรม</th>
                            <th>ค่าใช้จ่ายทั้งหมด</th>
                            <th>วันที่ส่งรายงานสรุป</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="hisform" items="${historyForms}">
                            <c:if test="${hisform.form_date > thisyear and hisform.status eq 'APPROVED' and hisform.id != form.id}">
                                <tr>
                                    <td><a href="viewsendform.jsp?form_id=${hisform.id}">${hisform.id}</a></td>
                                    <td>${hisform.course_name}</td>
                                    <td>${hisform.start_date} - ${hisform.end_date}</td>
                                    <td>
                                    ${
                                    ((hisform.expense.room_night_amount == null ? 0 : hisform.expense.room_night_amount) *
                                    (hisform.expense.room_night_each == null ? 0 : hisform.expense.room_night_each)) +
                                    ((hisform.expense.day_amount == null ? 0 : hisform.expense.day_amount) *
                                    (hisform.expense.day_cost_each == null ? 0 : hisform.expense.day_cost_each)) + form.expense.register_cost +
                                    (hisform.expense.travel_cost == null ? 0 : hisform.expense.travel_cost)
                                    }
                                    </td>
                                    <td>${form.report != null ? form.report.report_date : 'ไม่มีรายงานสรุป'}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>

                </table>

                <hr />

                <h2>ค่าใช้จ่าย</h2>
                1.ค่าลงทะเบียน : ${form.expense.register_cost} บาท<br />
                <br />
                2.ค่าใช้จ่ายในการเข้าร่วมอบรม/สัมมนา (กรณีสถานที่จัดอยู่ต่างจังหวัด/ต่างประเทศ) :<br />
                <br />
                2.1 ที่พัก : ${form.expense.room_night_amount == null ? 0 : form.expense.room_night_amount} คืน<br />
                คืนละ : ${form.expense.room_night_each == null ? 0 : form.expense.room_night_each} บาท<br />
                รวมค่าที่พัก : ${
                        (form.expense.room_night_amount == null ? 0 : form.expense.room_night_amount) *
                                (form.expense.room_night_each == null ? 0 : form.expense.room_night_each)
                        }<br />
                2.2 เบี้ยเลี้ยง : ${form.expense.day_amount == null ? 0 : form.expense.day_amount} วัน<br />
                วันละ : ${form.expense.day_cost_each == null ? 0 : form.expense.day_cost_each} บาท<br />
                รวมค่าเบี้ยเลี้ยง : ${
                        (form.expense.day_amount == null ? 0 : form.expense.day_amount) *
                                (form.expense.day_cost_each == null ? 0 : form.expense.day_cost_each)
                        }<br />
                2.3 ค่าพาหนะ เป็นเงิน : ${form.expense.travel_cost == null ? 0 : form.expense.travel_cost} บาท<br />
                <br />
                รวมค่าใช้จ่ายทั้งหมด : ${
                        ((form.expense.room_night_amount == null ? 0 : form.expense.room_night_amount) *
                                (form.expense.room_night_each == null ? 0 : form.expense.room_night_each)) +
                                ((form.expense.day_amount == null ? 0 : form.expense.day_amount) *
                                        (form.expense.day_cost_each == null ? 0 : form.expense.day_cost_each)) +
                                form.expense.register_cost +
                                (form.expense.travel_cost == null ? 0 : form.expense.travel_cost)
                        } บาท <br />

                <hr />

                <h2>การปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม </h2>
                3. การนำความรู้ที่ได้จากการเข้าร่วมอบรม/สัมมนา มาใช้ในการปรับปรุงการทำงาน<br />
                <br />
                3.1 ปรับปรุงการเรียนการสอน/ปรับปรุงการทำงาน/สร้างสรรค์งาน/สร้างนวัตกรรม :<br />
                <textarea name="detail" rows="4" cols="100" disabled>${form.improvement.detail}</textarea><br />
                3.2 ระยะเวลาที่ใช้ในการปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม ภายหลังเข้าร่วมอบรม/สัมมนา (ไม่เกิน 1 ปี): ${form.improvement.date_duration} วัน<br />
                3.3 ระยะเวลาการส่งเอกสารหลักฐานอ้างอิงการปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม (ไม่เกิน 1 ปี): ${form.improvement.report_sent_duration} วัน<br />
                <p>
                    <small>
                        หมายเหตุ* เอกสารหลักฐานอ้างอิง ตามข้อ 3.3<br />
                        &nbsp;&nbsp;&nbsp;&nbsp;ส่วนวิชาการ เช่น เอกสารรายงานการปรับปรุงแผนการสอน/ปรับปรุงเนื้อหาการสอน/ปรับปรุงเอกสารการสอน
                        เนื้อหารายวิชาหรือหลักสูตรอบรมใหม่ที่เปิดผลมาจากการอบรม ฯลฯ<br />
                        &nbsp;&nbsp;&nbsp;&nbsp;ส่วนสนับสนุนวิชาการ เช่น เอกสารรายงานการปรับปรุงกระบวนการทำงาน การประเมินผลสัมฤทธิ์ของการ
                        ทำงานภายหลัง เข้ารับการอบรม/สัมมนาโดยหัวหน้างาน/ผู้อำนวยการส่วนสนับสนุนวิชาการ/ผู้ช่วยคณบดี/รองคณบดี เป็นต้น
                    </small>
                </p>
                <button type="button" class="btn btn-default" onClick="window.print()">print this form</button>
                <a href="showallsendform.jsp" class="btn btn-info">ย้อนกลับ</a>
                <c:if test="${form.status == 'PENDING'}">
                    <c:if test="${sessionScope['auth.user'].id == form.user.id}">
                        <a href="confirmcancelform.do?form_id=${form.id}" class="btn btn-warning">ยกเลิกการยื่นฟอร์ม</a>
                    </c:if>

                    <c:if test="${sessionScope['auth.user'].role == 'ADMIN'}">
                        <a href="confirmapproveform.do?form_id=${form.id}" class="btn btn-success">อนุมัติฟอร์ม</a>
                        <a href="confirmrejectform.do?form_id=${form.id}" class="btn btn-danger">ปฎิเสธฟอร์ม</a>
                    </c:if>
                </c:if>

            </div>

        </div>

        <myTagLib:footer />

        <myTagLib:scriptlib />
        <script>
            $(document).ready(function() {
                $('#showtable').DataTable();
            });
        </script>
    </body>

</html>

<%
    session.setAttribute("form.result", null);
%>