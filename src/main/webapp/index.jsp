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

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/adv/status.css">

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="css/style.css">
    <script src="js/index/loadAllAdv.js"></script>
    <script src="js/search/loadSearchData.js"></script>

    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">

</head>
<body id="top" data-spy="scroll" data-target=".navbar-collapse" data-offset="50">
<jsp:include page="nav.jsp"/>
<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12">
                <div class="section-title text-center">
                    <h2>Car sales advertisements<small>Find your next car</small></h2>
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xs-6 col-sm-3">
                            <div id="accordion" class="panel panel-primary behclick-panel">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Search Filter Car</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="panel-heading ">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" href="#collapse1">
                                                <i class="indicator fa fa-caret-down" aria-hidden="true"></i> Brand
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapse1" class="panel-collapse collapse in">
                                        <ul class="list-group" id="model">
                                        </ul>
                                    </div>
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" href="#collapse3"><i
                                                    class="indicator fa fa-caret-down" aria-hidden="true"></i> Engine
                                                size</a>
                                        </h4>
                                    </div>
                                    <div id="collapse3" class="panel-collapse collapse in">
                                        <ul class="list-group" id="engine">

                                        </ul>
                                    </div>
                                    <button class="btn btn-success" onClick="filter()">Search</button>
                                </div>
                            </div>
                        </div>
                        <div id="advs"></div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
</body>
</html>
