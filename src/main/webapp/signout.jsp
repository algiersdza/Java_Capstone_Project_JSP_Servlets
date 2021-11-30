<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/12/2021
  Time: 3:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>Session ended</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div>
            <a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand">
                <img src="<c:url value='/Images/r-header.png'/>"
                     width="30" height="30" class="d-inline-block align-top" alt=""> Restco
                Admin Management
            </a>
        </div>
        <div >
            <ul class="navbar-nav">
                <li>
                    <a href="<%=request.getContextPath()%>/index.jsp" class="nav-link">Sign In
                    </a>
                </li>
            </ul>
        </div>
        <div>
            <ul class="navbar-nav" >
                <li>
                    <a href="<%=request.getContextPath()%>/signup.jsp" class="nav-link">Register
                    </a>
                </li>
            </ul>
        </div>
        <div>
            <ul class="navbar-nav">
                <li>
                    <a href="<%=request.getContextPath()%>/forgotrequest.jsp" class="nav-link">Forgot Password
                    </a>
                </li>
            </ul>
        </div>
        <div>
            <ul class="navbar-nav">
                <li>
                    <a href="<%=request.getContextPath()%>/features.jsp" class="nav-link">Features
                    </a>
                </li>
            </ul>
        </div>
        <div>
            <ul class="navbar-nav">
                <li>
                    <a href="<%=request.getContextPath()%>/about.jsp" class="nav-link">About
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="TOP-HEAD-no-account">
    <h3>You are signed out</h3>
    <h3>If you wish to test it<a href="admin.jsp"> Click here </a> to try to go to http://localhost:8080/CapStone_war_exploded/admin.jsp</h3>
</div>
<div class="TOP-HEAD-INDEX">
    <img src="<c:url value='/Images/logo.png'/>" id="LOGO-INDEX" width = "300" height="300" class="rounded mx-auto d-block" alt="Logo_Index">
</div>
<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
