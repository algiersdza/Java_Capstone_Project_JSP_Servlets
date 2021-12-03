<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/11/2021
  Time: 12:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>Sign Up Page</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<script src=""></script>
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
<h3 class="text-center">Restaurant Management Operations Sign up Page</h3>
<div id="TOP-WRAP-SIGN-UP">
    <div class="container">
        <form class="SIGN-UP-FORM" action="<%= request.getContextPath()%>/UserRegister" method="post" >
            <fieldset>
                <table class="table table-bordered">
                    <tbody>
                    <tr><th><label for="FirstName-Sign-up">First Name: </label> <input type="text" id="FirstName-Sign-up" name="FirstName" placeholder="Enter your First Name"></th></tr>
                    <tr><th><label for="LastName-Sign-up"></label>Last Name: <input type="text" id="LastName-Sign-up" name="LastName" placeholder="Enter your Last Name"></th></tr>
                    <tr><th><label for ="Username-Sign-up">User Name: </label><input type="text" id = "Username-Sign-up" name="UserName" placeholder="Enter your username"></th></tr>
                    <tr><th><label for="Password-Sign-up">Password: </label><input type="password" id="Password-Sign-up" name="Password" placeholder="Enter your password"></th></tr>
                    <tr><th><label for="Email-Sign-up">Email: </label><input type="email" id="Email-Sign-up" name="EmailAddress" placeholder="example@host.domain"></th></tr>
                    <tr><th><input type="submit" value="Register" class="btn btn-success" ></th></tr>
                    </tbody>
                </table>
            </fieldset>
        </form>
    </div>
</div>
<div class="TOP-HEAD-INDEX">
    <img src="<c:url value='/Images/logo.png'/>" id="LOGO-INDEX" width = "300" height="200" class="rounded mx-auto d-block" alt="Logo_Index">
</div>
<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
