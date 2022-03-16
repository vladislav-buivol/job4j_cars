<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sale adv</title>

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

    <!-- MAIN CSS -->
    <link rel="stylesheet" href="css/style.css">

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/adv/script.js"></script>

</head>
<body>
<jsp:include page="../nav.jsp"/>
<section class="section-background">
    <div class="container">

        <div class="row">
            <div class="col-lg-12 col-xs-9">
                <div class="form">
                    <form action="#" method="post" id="createAdv" enctype="multipart/form-data">
                        <div class="form-group">
                            <label>Used/New:</label>
                            <select class="form-control" id="isUsed" name="isUsed">
                                <option disabled selected value="-1">-- Used/New: --</option>
                                <option value="false">New vehicle</option>
                                <option value="true">Used vehicle</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Model:</label>

                            <select class="form-control" id="model" name="model">
                                <option disabled selected value="">-- Select model --</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Price:</label>
                            <input id="price" name="price" type="text" class="form-control" autocomplete="off"
                                   required>
                        </div>

                        <div class="form-group">
                            <label>Mileage:</label>
                            <input id="mileage" name="mileage" type="text" class="form-control" autocomplete="off"
                                   required>
                        </div>

                        <div class="form-group">
                            <label>Number of seats:</label>
                            <input id="numberOfSeats" type="text" class="form-control" name="numberOfSeats"
                                   autocomplete="off"
                                   required>
                        </div>

                        <div class="form-group">
                            <label>Engine size:</label>

                            <select id="engine" name="engine" class="form-control">
                                <option disabled selected value="">-- Select engine --</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Fuel:</label>

                            <select id="fuel" name="fuel" class="form-control">
                                <option disabled selected value="">-- Select fuel type --</option>
                                <option value="Petrol">Petrol</option>
                                <option value="Gas">Gas</option>
                                <option value="Petrol">Petrol/Gas</option>
                                <option value="Electric">Electric</option>
                                <option value="Hybrid">Hybrid</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Gearbox:</label>

                            <select id="gearbox" name="gearbox" class="form-control">
                                <option disabled selected value="">-- Select gearbox --</option>
                                <option value="Manual">Manual</option>
                                <option value="Automatic">Automatic</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="img" class="form-label">Add car image</label>
                            <input class="form-control-file" type="file" id="img" name="img" multiple>
                        </div>
                        <button type="submit" class="section-btn btn btn-primary btn-block">Create an advertisement
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>

