<%@page session="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %> 
<%@page import="javax.servlet.http.HttpServletRequest" %> 
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>form</title>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <-script type="text/javascript" src="java/javascript.js"></script>
    <-link rel="stylesheet" href="css/style.css">
    
    
  </head>

  <body onload="load()">
      <form class="form" action="UserFormServlet" method="POST">
   <div class="wrapper">
	
		 
                <div class="box">
                    <%! User user = new User() ; %> 
                    <% user = (User) session.getAttribute("reqUser"); %>
                	
		
<!--................................. CONTENT  FORM...................................................................-->  
                    
                    <h2>
                        ✎แบบฟอร์มขออนุมัติเข้าร่วมอบรม/สัมมนาของบุคลากร<br>&nbsp;&nbsp;&nbsp;คณะเทคโนโลยีสารสนเทศ<br><br>
                        <br><br></h2>
                    
                    <fieldset>
                        <legend>รายละเอียด</legend>
                        ชื่อ :<input class="textsmall" type="text" value="<%= user.getFirstname() %>" > 
                        นามสกุล :<input class="textsmall" type="text" value="<%= user.getLastname()%>"> 
                        ตำแหน่ง :<input class="textsmall" type="text" value="position"> <br><br>
                        มีความประสงค์ ขออนุมัติเข้าร่วมอบรม / สัมนา หลักสูตร :<input class="textbox" type="text" size="70" name="course"><br><br>
                        จัดโดย :<input class="textsmall" type="text" name="organizer"> 
                        สถานที่จัด :<input class="textsmall" type="text" name="location"><br><br>
                        วันที่เริ่ม :<input class="textsmall" type="date" name="start_date"> 
                        วันที่สิ้นสุด :<input class="textsmall" type="date" name="end_date"> 
                        <input type="submit" value="รวมวัน" name="cal" /> : <input class="textmini" type="text"  value="0"> วัน<br><br>
                    </fieldset><br><br>
                     
                    <fieldset>
                        <legend>ขออนุมัติสนับสนุนค่าใช้จ่าย</legend>
                        1.ค่าลงทะเบียน :<input class="textsmall" type="text" name="reg_expense"> บาท<br><br>
                        2.ค่าใช้จ่ายในการเข้าร่วมอบรม/สัมมนา <input class="button" type="checkbox" name="inter" value="ON" />(กรณีสถานที่จัดอยู่ต่างจังหวัด/ต่างประเทศ) รวมเป็นเงิน:<input class="textsmall" type="text" size="4" name="inter_expense"> บาท<br><br>
                        &nbsp;&nbsp;&nbsp;2.1 ค่าที่พัก :<input class="textmini" type="text" size="4" name="acc_night"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;คืน คืนละ : <input class="textmini" type="text" size="4" name="acc_each"> 
                        บาท เป็นเงิน : <input class="textmini" type="text" size="4" name="acc_sum"> บาท <input type="submit" value="คิดค่าที่พัก" name="cal" /><br><br>
                        &nbsp;&nbsp;&nbsp;2.2 ค่าเบี้ยเลี้ยง :<input class="textmini" type="text" size="4" name="allo_day"> วัน วันละ : <input class="textmini" type="text" size="4" name="allo_each"> 
                        บาท เป็นเงิน : <input class="textmini" type="text" size="4" name="allo_sum"> บาท <input type="submit" value="คิดค่าเบี้ยเลี้ยง" name="cal" /><br><br>
                        &nbsp;&nbsp;&nbsp;2.3 ค่าพาหนะ เป็นเงิน : <input class="textmini" type="text" size="4" name="traveling"> บาท<br><br>
                    </fieldset><br><br>
                    
                    <fieldset>
                        <legend>การเข้าร่วมอบรม/สัมมนา</legend>
                        ในปีงบประมาณ : <input class="textmini" type="text" size="10" value="YEAR 2559" name=""><br><br>
                        
<!--..............................................Table...............................................-->
                            
<table id="myTableData"  border="1" cellpadding="2">
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
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
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
                        <textarea name="improvement_evident_period" rows="4" cols="100"></textarea><br><br><br><br>
                       <small>
                       หมายเหตุ* เอกสารหลักฐานอ้างอิง ตามข้อ 3.3 <br>
                       &nbsp;&nbsp;&nbsp;&nbsp;ส่วนวิชาการ เช่น เอกสารรายงานการปรับปรุงแผนการสอน/ปรับปรุงเนื้อหาการสอน/ปรับปรุงเอกสารการสอน <br>เนื้อหารายวิชาหรือหลักสูตรอบรมใหม่ที่เปิดผลมาจากการอบรม ฯลฯ<br>
                       &nbsp;&nbsp;&nbsp;&nbsp;ส่วนสนับสนุนวิชาการ เช่น เอกสารรายงานการปรับปรุงกระบวนการทำงาน การประเมินผลสัมฤทธิ์ของการ<br>ทำงานภายหลัง เข้ารับการอบรม/สัมมนาโดยหัวหน้างาน/ผู้อำนวยการส่วนสนับสนุนวิชาการ/
                       ผู้ช่วยคณบดี/รองคณบดี เป็นต้น
                       
                       </small>
                    </fieldset><br><br>
                    
                    
                    
                    
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="submit">Submit</button><br><br>
                    

<!--................................. CONTENT  FORM...................................................................-->   
                  
                
                    </div>
      
    </div>
      
       
	
	
    <-script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'><-/script>

        <script src="js/index.js"></script>

    
    
</form>

  </body>
</html>

