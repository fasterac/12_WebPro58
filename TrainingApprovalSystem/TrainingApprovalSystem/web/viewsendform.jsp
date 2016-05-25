<%@page import="java.util.Date"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="utility.DataConnector" %>
<%@ page import="factory.FormFactory" %>
<%@ page import="utility.FileUploadHelper" %>
<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Connection connection = DataConnector.getDBConnection(request);
    pageContext.setAttribute("form",
            new FormFactory(connection).find(Integer.parseInt(request.getParameter("form_id"))));
    pageContext.setAttribute("fileUploadHelper", new FileUploadHelper(request));
    
%>
<%  int userid = ((Form)pageContext.getAttribute("form")).getUser().getId(); 
    pageContext.setAttribute("history", new FormFactory(DataConnector.getDBConnection(request)).findAllByUserID(userid));%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <title>Training Approval System</title>
    </head>

    <body>
        <h1>แบบฟอร์มขออนุมัติ</h1>

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
        <%-- calculate year ngobpraman --%>
        <%! final Date date = new Date(); %>
        <% if(new Date().getMonth() < 9){
            date.setYear(new Date().getYear()-1); date.setMonth(9); date.setDate(01);
        } %>
        <% pageContext.setAttribute("thisyear", date); %> 
        ในปีงบประมาณ <%= date.getYear() + 2443 %> ได้เข้าร่วมอบรม/สัมมนา ดังนี้<br>
        <%! int formOrder = 0; %>
        <table border="1">
            <thead>
                <tr>
                    <th>ที่</th>
                    <th>หลักสูตร</th>
                    <th>ระยะเวลาที่ไปอบรม</th>
                    <th>ค่าใช้จ่าย</th>
                    <th>วันที่ส่งรายงานสรุป</th>
                    <th>วันที่จัดบรรยาย</th>
                </tr>
            </thead>     

            <tbody>
                <c:forEach var="his" items="${history}">
                    <c:if test="${his.form_date > thisyear and his.id ne form.id and his.status eq 'APPROVED'}">
                    <tr>
                        <% formOrder += 1; %>
                        <td><%= formOrder %></td>
                        <td>${his.course_name}</td>
                        <td>${his.start_date} - ${his.end_date}</td>
                        <td>${((his.expense.room_night_amount == null ? 0 : his.expense.room_night_amount) *
                        (his.expense.room_night_each == null ? 0 : his.expense.room_night_each)) +
                        ((his.expense.day_amount == null ? 0 : his.expense.day_amount) *
                        (his.expense.day_cost_each == null ? 0 : his.expense.day_cost_each)) + his.expense.register_cost +
                        (his.expense.travel_cost == null ? 0 : his.expense.travel_cost) }</td>
                        <td>-</td><!-- looking for condition -->
                        <td>-</td>
                    </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>

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
        <input type="submit" value="Print this form" onClick="window.print()"/>
        <a href="showallsendform.jsp">ย้อนกลับ</a>
        <c:if test="${form.status == 'PENDING'}">
            <c:if test="${sessionScope['auth.user'].id == form.user.id}">
                <a href="confirmcancelform.do?form_id=${form.id}">ยกเลิกการยื่นฟอร์ม</a>
            </c:if>

            <c:if test="${sessionScope['auth.user'].role == 'ADMIN'}">
                <a href="confirmapproveform.do?form_id=${form.id}">อนุมัติฟอร์ม</a>
                <a href="confirmrejectform.do?form_id=${form.id}">ปฎิเสธฟอร์ม</a>
            </c:if>
        </c:if>
    </body>

</html>

<%
    session.setAttribute("form.result", null);
    formOrder = 0;
%>