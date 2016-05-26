<%@include file="/WEB-INF/tags/importlib.tag" %>
<%@tag pageEncoding="UTF-8" body-content="empty" %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>ระบบขอไปอบรมและนำเสนอผลงาน</title>

    <c:if test="${sessionScope['auth.user'].role == 'USER'}">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    </c:if>
    <c:if test="${sessionScope['auth.user'].role == 'ADMIN'}">
        <link href="assets/css/bootstrap.min_2.css" rel="stylesheet">
    </c:if>
    <link href="assets/css/jumbotron.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" />

    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/footer.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>