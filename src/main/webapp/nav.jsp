<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<!-- Image and text -->
<section class="navbar custom-navbar navbar-fixed-top" role="navigation">
    <div class="container">

        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon icon-bar"></span>
                <span class="icon icon-bar"></span>
                <span class="icon icon-bar"></span>
            </button>
            <!-- lOGO TEXT HERE -->
            <a href="<%=request.getContextPath()%>" class="navbar-brand">Car Dealer Website</a>
        </div>

        <!-- MENU LINKS -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-nav-first">
                <li><a href="<%=request.getContextPath()%>">Home</a></li>
                <li><a href="cars.html">Cars</a></li>
                <li>
                    <c:if test="${not empty account}">
                        <a class="nav-link" href="<%=request.getContextPath()%>/add.jsp">
                            Sale car
                        </a>
                    </c:if>
                </li>
                <c:choose>
                    <c:when test="${empty account}">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/login.do">
                                Log in
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/registration.do">Registration</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/logout">
                                <c:out value="${account.name}"/>
                                | Log out
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</section>