<%@ include file="/WEB-INF/importlib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Training Approval System</title>

        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />

        <link rel="stylesheet" href="assets/css/login.css">
        

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <!-- -->
    </head>

    <body>
        <div class="wrapper">
            <section class="container">
                <center><img src="assets/images/lock.png" alt="banner" /></center><br /><br />

                <div class="login">
                    <h1>ระบบขอไปอบรมและนำเสนอผลงาน</h1>

                    <c:if test="${sessionScope['login.error'] != null}">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                ${sessionScope['login.error']}
                        </div>
                    </c:if>
                    <c:if test="${sessionScope['login.info'] != null}">
                        <div class="alert alert-info alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                ${sessionScope['login.info']}
                        </div>
                    </c:if>

                    <form action="login.do" method="post" class="form-signin">
                        <div class="form-group" id="username-group">
                            <label for="username" class="sr-only">Username</label>
                            <input type="text" name="username" id="username" class="form-control" placeholder="ชื่อผู้ใช้" />
                        </div>

                        <div class="form-group" id="password-group">
                            <label for="password" class="sr-only">Password</label>
                            <input type="password" name="password" id="password" class="form-control" placeholder="รหัสผ่าน" />
                        </div>

                        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                    </form>
                </div>
            </section>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script>
            $(".form-signin").submit(function(e) {
                var emptyUser = $("#username").val() == "", emptyPass = $("#password").val() == "";
                if(emptyUser || emptyPass) {
                    var alertElem = $(".alert");
                    if(alertElem.length > 0) alertElem.remove();
                    $("<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">" +
                            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>" +
                            "โปรดกรอกข้อมูลให้ครบ" +
                    "</div>").insertAfter(".login > h1");
                    e.preventDefault();

                    if(emptyUser) {
                        $("#username-group").addClass("has-error");
                    } else {
                        $("#username-group").removeClass("has-error");
                    }

                    if(emptyPass) {
                        $("#password-group").addClass("has-error");
                    } else {
                        $("#password-group").removeClass("has-error");
                    }
                }
            });
        </script>
    </body>

</html>

<%
    session.setAttribute("login.error", null);
    session.setAttribute("login.info", null);
%>