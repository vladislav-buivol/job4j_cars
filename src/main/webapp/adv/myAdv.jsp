<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>PHPJabber | Car Dealer</title>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../css/owl.theme.default.min.css">
    <link rel="stylesheet" href="../css/adv/status.css">

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="../css/style.css">
    <script src="../js/adv/loadUserAdv.js"></script>


</head>
<body id="top" data-spy="scroll" data-target=".navbar-collapse" data-offset="50">
<jsp:include page="../nav.jsp"/>
<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12">
                <div class="section-title text-center">
                    <h2>My advertisements</h2>
                </div>
            </div>
            <div id="advs"></div>
        </div>
    </div>
</section>
</body>
</html>
