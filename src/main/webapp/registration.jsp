<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>

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

</head>
<body>
<jsp:include page="nav.jsp"/>
<section>
    <div class="container">
        <div class="card" style="width: 100%">
            <div class="col-md-12 col-sm-12">
                <div class="section-title text-center">
                    <c:if test="${not empty notific}">
                        <div style="color:#0000FF; font-weight: bold; margin: 30px 0;">
                            <c:out value="${notific}"/>
                        </div>
                    </c:if>
                    <h2>Registration</h2>
                </div>
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/registration.do" autocomplete="off" method="post" class="courses-detail">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" name="name" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" name="email" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" name="password" autocomplete="off">
                    </div>
                    <c:if test="${not empty errMsg}">
                        <div style="color:#ff0000; font-weight: bold; margin: 30px 0;">
                            <c:out value="${errMsg}"/>
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-primary">Register</button>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
