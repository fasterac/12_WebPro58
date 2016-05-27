<%@include file="/WEB-INF/tags/importlib.tag" %>
<%@tag description="Admin home page" pageEncoding="UTF-8" body-content="empty" %>

<myTagLib:navbar currentUser="${sessionScope['auth.user']}" />
 
<div class="page-wrap">
    <div class="jumbotron">
        <div class="container">
            <img src="assets/images/user.png" alt="banner" />
            <h1>ยินดีต้อนรับ!</h1>
            <p>${sessionScope['auth.user'].role} : ${sessionScope['auth.user'].pname_th}${sessionScope['auth.user'].fname_th} ${sessionScope['auth.user'].lname_th}</p>

        </div>
    </div>
    <br>

    <div class="container">
        <div class="row">
            <div class="col-md-5">
                 <img src="assets/images/form.png" alt="banner" />
                <h2>ยื่นคำร้อง</h2>
                <p>กรอกรายละเอียดแบบฟอร์มเพื่อยื่นคำร้อง</p>
                <p><a class="btn btn-default" href="newform.jsp" role="button">คลิก &raquo;</a></p>
            </div>
            <div class="col-md-5">
                <img src="assets/images/check.png" alt="banner" /><br>
                <h2>ดูคำร้อง</h2>
                <p>ตรวจสอบและจัดการแบบฟอร์ม</p>
                <p><a class="btn btn-default" href="showallsendform.jsp" role="button">คลิก &raquo;</a></p>
            </div>
            <div class="col-md-2">
                <img src="assets/images/member.png" alt="banner" />
                <h2>สมาชิก</h2>
                <p>ตรวจสอบรายชื่อสมาชิก</p>
                <p><a class="btn btn-default" href="showalluser.jsp" role="button">คลิก &raquo;</a></p>
            </div>
        </div>
    </div>
</div>