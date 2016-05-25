<%@page import="java.util.Date"%>
<%@page import="factory.FormFactory"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="utility.DataConnector" %>
<%@ page import="utility.Authorization" %>
<%@ page import="factory.UserFactory" %>
<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Connection connection = DataConnector.getDBConnection(request);
    User user = new UserFactory(connection).find(new Authorization(connection, session).getCurrentUser().getId());
    pageContext.setAttribute("user", user);
    pageContext.setAttribute("history", new FormFactory(DataConnector.getDBConnection(request)).findAllByUserID(user.getId()));
%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <title>Training Approval System</title>
    </head>

    <body>
        <h1>แบบฟอร์มขออนุมัติ</h1>

        <h2>รายละเอียด</h2>
        ชื่อ : ${user.pname_th} ${user.fname_th}<br />
        นามสกุล : ${user.lname_th}<br />
        มีความประสงค์ ขออนุมัติเข้าร่วมอบรม / สัมนา หลักสูตร : ${sessionScope['form.new'].course_name}<br />
        จัดโดย : ${sessionScope['form.new'].organizer_name}<br />
        สถานที่จัด : ${sessionScope['form.new'].location_name}<br />
        วันที่เริ่ม : ${String.format("%tF", sessionScope['form.new'].start_date)} <br />
        วันที่สิ้นสุด : ${String.format("%tF", sessionScope['form.new'].end_date)} <br />

        <hr />

        <h2>ขออนุมัติสนับสนุนค่าใช้จ่าย</h2>
        1.ค่าลงทะเบียน : ${sessionScope['form.new'].expense.register_cost} บาท<br />
        <br />
        2.ค่าใช้จ่ายในการเข้าร่วมอบรม/สัมมนา (กรณีสถานที่จัดอยู่ต่างจังหวัด/ต่างประเทศ) :<br />
        <br />
        2.1 ที่พัก : ${sessionScope['form.new'].expense.room_night_amount == null ? 0 : sessionScope['form.new'].expense.room_night_amount} คืน<br />
        คืนละ : ${sessionScope['form.new'].expense.room_night_each == null ? 0 : sessionScope['form.new'].expense.room_night_each} บาท<br />
        รวมค่าที่พัก : ${
            (sessionScope['form.new'].expense.room_night_amount == null ? 0 : sessionScope['form.new'].expense.room_night_amount) *
            (sessionScope['form.new'].expense.room_night_each == null ? 0 : sessionScope['form.new'].expense.room_night_each)
        }<br />
        2.2 เบี้ยเลี้ยง : ${sessionScope['form.new'].expense.day_amount == null ? 0 : sessionScope['form.new'].expense.day_amount} วัน<br />
        วันละ : ${sessionScope['form.new'].expense.day_cost_each == null ? 0 : sessionScope['form.new'].expense.day_cost_each} บาท<br />
        รวมค่าเบี้ยเลี้ยง : ${
            (sessionScope['form.new'].expense.day_amount == null ? 0 : sessionScope['form.new'].expense.day_amount) *
            (sessionScope['form.new'].expense.day_cost_each == null ? 0 : sessionScope['form.new'].expense.day_cost_each)
        }<br />
        2.3 ค่าพาหนะ เป็นเงิน : ${sessionScope['form.new'].expense.travel_cost == null ? 0 : sessionScope['form.new'].expense.travel_cost} บาท<br />
        <br />
        รวมค่าใช้จ่ายทั้งหมด : ${
            ((sessionScope['form.new'].expense.room_night_amount == null ? 0 : sessionScope['form.new'].expense.room_night_amount) *
             (sessionScope['form.new'].expense.room_night_each == null ? 0 : sessionScope['form.new'].expense.room_night_each)) +
            ((sessionScope['form.new'].expense.day_amount == null ? 0 : sessionScope['form.new'].expense.day_amount) *
             (sessionScope['form.new'].expense.day_cost_each == null ? 0 : sessionScope['form.new'].expense.day_cost_each)) +
            sessionScope['form.new'].expense.register_cost +
            (sessionScope['form.new'].expense.travel_cost == null ? 0 : sessionScope['form.new'].expense.travel_cost)
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
        <textarea name="detail" rows="4" cols="100" disabled>${sessionScope['form.new'].improvement.detail}</textarea><br />
        3.2 ระยะเวลาที่ใช้ในการปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม ภายหลังเข้าร่วมอบรม/สัมมนา (ไม่เกิน 1 ปี): ${sessionScope['form.new'].improvement.date_duration} วัน<br />
        3.3 ระยะเวลาการส่งเอกสารหลักฐานอ้างอิงการปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม (ไม่เกิน 1 ปี): ${sessionScope['form.new'].improvement.report_sent_duration} วัน<br />
        <p>
            <small>
                หมายเหตุ* เอกสารหลักฐานอ้างอิง ตามข้อ 3.3<br />
                &nbsp;&nbsp;&nbsp;&nbsp;ส่วนวิชาการ เช่น เอกสารรายงานการปรับปรุงแผนการสอน/ปรับปรุงเนื้อหาการสอน/ปรับปรุงเอกสารการสอน
                เนื้อหารายวิชาหรือหลักสูตรอบรมใหม่ที่เปิดผลมาจากการอบรม ฯลฯ<br />
                &nbsp;&nbsp;&nbsp;&nbsp;ส่วนสนับสนุนวิชาการ เช่น เอกสารรายงานการปรับปรุงกระบวนการทำงาน การประเมินผลสัมฤทธิ์ของการ
                ทำงานภายหลัง เข้ารับการอบรม/สัมมนาโดยหัวหน้างาน/ผู้อำนวยการส่วนสนับสนุนวิชาการ/ผู้ช่วยคณบดี/รองคณบดี เป็นต้น
            </small>
        </p>

        <a href="confirmnewform.do">ยืนยันข้อมูล (ยื่นฟอร์ม)</a> <a href="newform.jsp">ย้อนกลับ</a>
    </body>

</html>
<% formOrder = 0; %>