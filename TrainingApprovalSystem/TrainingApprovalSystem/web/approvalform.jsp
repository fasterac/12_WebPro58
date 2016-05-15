<%@page import="Utility.DataConnector"%>
<%@page import="Utility.HistoryUtility"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %> 
<%@page import="javax.servlet.http.HttpServletRequest" %> 
<%@page import="java.util.ArrayList" %> 
<% 
    User user = (User) session.getAttribute("user");
    ArrayList<String> history = new HistoryUtility(DataConnector.getDBConnection(request)).getHistory(user.getUser_id(), "2016-10-01");
    pageContext.setAttribute("history", history);
%>
<!DOCTYPE html>
<html >
    <head>
        <meta charset="UTF-8">
        <title>form</title>
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/script.js"></script>
        <link rel="stylesheet" href="css/style.css">


    </head>

    <body onload="load()">
        <form action="UserFormServlet" method="POST">
            
            <div class="wrapper">
                <div class="logo-menu-container">
       
                    <div class="logo">ระบบขอไปอบรมและนำเสนอผลงาน</div>
                    <div class="menu">
                        <ul>
                            <li><input type="submit" value="Home" name="forwarder" /></li>
                            <li><input type="submit" value="Logout" name="forwarder" /> </li>
                            <li><input type="submit" value="CreateForm" name="forwarder" /></li>
                            <li><input type="submit" value="TrackApproval" name="forwarder" /></li>
                      </ul>
                    </div>
                </div>
                
                <div class="box">
                    


                    <!--................................. CONTENT  FORM...................................................................-->  

                    <h2>
                        ✎แบบฟอร์มขออนุมัติเข้าร่วมอบรม/สัมมนาของบุคลากร<br>&nbsp;&nbsp;&nbsp;คณะเทคโนโลยีสารสนเทศ<br><br>
                        <br><br></h2>

                    <fieldset>
                        <legend>รายละเอียด</legend>
                        ชื่อ :<input class="textsmall" type="text" value="${sessionScope.user.firstname}" > <br>
                        นามสกุล :<input class="textsmall" type="text" value="${sessionScope.user.lastname}"> <br>
                        ตำแหน่ง :<input class="textsmall" type="text" value=""> <br><br>
                        มีความประสงค์ ขออนุมัติเข้าร่วมอบรม / สัมนา หลักสูตร :<input class="textbox" type="text" size="70" name="course"><br><br>
                        จัดโดย :<input class="textsmall" type="text" name="organizer"> <br>
                        สถานที่จัด :<input class="textsmall" type="text" name="location"><br><br>
                        วันที่เริ่ม :<input class="textsmall" type="date" name="start_date"> <br>
                        วันที่สิ้นสุด :<input class="textsmall" type="date" name="end_date"> <br>
                        <input type="submit" value="รวมวัน" name="cal" /> : <input class="textmini" type="text"  value="0"> วัน<br><br>
                    </fieldset><br><br>

                    <fieldset>
                        <legend>ขออนุมัติสนับสนุนค่าใช้จ่าย</legend>
                        1.ค่าลงทะเบียน :<input class="textsmall" type="text" value="0" name="reg_expense"> บาท<br><br>
                        2.ค่าใช้จ่ายในการเข้าร่วมอบรม/สัมมนา <input class="check" type="checkbox" name="inter" value="ON" />
                        (กรณีสถานที่จัดอยู่ต่างจังหวัด/ต่างประเทศ) รวมเป็นเงิน:<input class="textsmall" type="text" size="4" value="0" name="inter_expense"> บาท<br><br>
                        
                        2.1 ที่พัก :<input class="textmini" id="room_night" type="text" size="4" value="0" name="acc_night"> 
                        คืน คืนละ : <input class="textmini" id="room_cost" type="text" size="4" value="0" name="acc_each"> 
                        บาท <input type="button" id="cal_room_cost" value="คิดค่าที่พัก" value="0" name="cal" /><br>
                        เป็นเงิน : <input class="textmini" id="room_total" type="text" size="4" value="0" name="acc_sum"> บาท <br><br>
                        2.2 เบี้ยเลี้ยง :<input class="textmini" id="eat_day" type="text" size="4" value="0" name="allo_day"> 
                        วัน วันละ : <input class="textmini" id="eat_cost" type="text" size="4" value="0" name="allo_each"> 
                        บาท <input type="button" id="cal_eat_cost" value="คิดค่าเบี้ยเลี้ยง" value="0" name="cal" /><br>
                        เป็นเงิน : <input class="textmini" id="eat_total" type="text" size="4" value="0" name="allo_sum"> บาท <br><br>
                        
                        2.3 ค่าพาหนะ เป็นเงิน : <input class="textmini" type="text" size="4" value="0" name="travelling"> บาท<br><br>
                    </fieldset><br><br>

                    <fieldset>
                        <legend>การเข้าร่วมอบรม/สัมมนา</legend>
                        <!-- get datetime at value below -->
                        ในปีงบประมาณ : <input class="textmini" type="text" size="10" value="2559" name="" disabled="disabled"><br><br>

                        <!--..............................................Table...............................................-->
                        <%!int count = 0;%>


                        <table id="myTableData"  border="1" width="800">
                            <thead>
                                <tr>
                                    <th><b>&nbsp;num&nbsp;</b></th>
                                    <th><b>&nbsp;course&nbsp;</b></th>
                                    <th><b>&nbsp;start_date&nbsp;</b></th>
                                    <th><b>&nbsp;sum_expense&nbsp;</b></th>
                                    <th><b>&nbsp;report_date&nbsp;</b></th>
                                    <th><b>&nbsp;lecture_date&nbsp;</b></th>
                                </tr>
                            </thead>

                            <tbody>
                                <tr>
                                    <c:forEach var="word" items="${pageScope.history}">
                                        <td>${word}</td>
                                        <% count = count + 1; %>
                                        <% if (count == history.size()) { %> 
                                    </tr>
                                    <% } else if (count % 6 == 0) {%>
                                </tr> <tr>
                                <% }%>
                            </c:forEach>
                    </tbody>
                </table>



                <!--..............................................Table...............................................-->
            </fieldset><br><br>


            <fieldset>
                <legend>การปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม </legend>
                3. การนำความรู้ที่ได้จากการเข้าร่วมอบรม/สัมมนา มาใช้ในการปรับปรุงการทำงาน <br><br>
                &nbsp;&nbsp;&nbsp;3.1 ปรับปรุงการเรียนการสอน/ปรับปรุงการทำงาน/สร้างสรรค์งาน/สร้างนวัตกรรม : <br>
                <textarea name="improvement" rows="4" cols="100"></textarea></textarea><br><br>
                &nbsp;&nbsp;&nbsp;3.2 ระยะเวลาที่ใช้ในการปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม ภายหลังเข้าร่วมอบรม/สัมมนา (ไม่เกิน 1 ปี): <br>
                <textarea name="improvement_period" rows="4" cols="100"></textarea><br><br>
                &nbsp;&nbsp;&nbsp;3.3 ระยะเวลาการส่งเอกสารหลักฐานอ้างอิงการปรับปรุงงาน/สร้างสรรค์งาน/สร้างนวัตกรรม (ไม่เกิน 1 ปี): <br>
                <textarea name="improvement_evident_period" rows="4" cols="100"></textarea><br><br><br>
                <small>
                    หมายเหตุ* เอกสารหลักฐานอ้างอิง ตามข้อ 3.3 <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;ส่วนวิชาการ เช่น เอกสารรายงานการปรับปรุงแผนการสอน/ปรับปรุงเนื้อหาการสอน/ปรับปรุงเอกสารการสอน <br>
                    เนื้อหารายวิชาหรือหลักสูตรอบรมใหม่ที่เปิดผลมาจากการอบรม ฯลฯ<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;ส่วนสนับสนุนวิชาการ เช่น เอกสารรายงานการปรับปรุงกระบวนการทำงาน การประเมินผลสัมฤทธิ์ของการ<br>
                    ทำงานภายหลัง เข้ารับการอบรม/สัมมนาโดยหัวหน้างาน/ผู้อำนวยการส่วนสนับสนุนวิชาการ/
                    ผู้ช่วยคณบดี/รองคณบดี เป็นต้น

                </small>

            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <br><br><button type="submit" name="submit" value="submit">Submit</button><br><br>.

            </fieldset><br><br>
            <!--................................. CONTENT  FORM...................................................................-->   
<br><br>.

        </div>

    </div>




    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    
    
    <script>
        $(function() {
            $("#cal_room_cost").click(function() {
                $("#room_total").val($("#room_night").val() * $("#room_cost").val());
            });
            
            $("#cal_eat_cost").click(function() {
                $("#eat_total").val($("#eat_day").val() * $("#eat_cost").val());
            });
            
            $("form").submit(function(e) {
                var event = e;
                var flag = false;
                $("form input").each(function() {
                    if($(this).val().trim() == "") {
                        flag = true;
                        return;
                    }
                });
                $("form textarea").each(function() {
                    if($(this).val().trim() == "") {
                        flag = true;
                        return;
                    }
                });
                if(flag) {
                    event.preventDefault();
                    alert("กรุณากรอกฟอร์มให้ครบ");
                }
            });
        });
    </script>



</form>

</body>
</html>

