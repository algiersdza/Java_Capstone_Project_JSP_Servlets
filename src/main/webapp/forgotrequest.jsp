<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/11/2021
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>Forgot Password Page</title>
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
<div class="TOP-HEAD-FORGOT">
    <h3 class="text-center">To retrieve your password please answer the following questions:</h3>
</div>
<div class="container">
    <form class="FORM-FORGOT" action="<%= request.getContextPath()%>/RecoverServlet" method="post">
        <fieldset>
            <table class="table table-bordered">
                <tr><th><label for="FirstName-Forgot">First Name: </label><input type="text" id="FirstName-Forgot" name="FirstName" placeholder="First Name?"></th></tr>
                <tr><th><label for="LastName-Forgot">Last Name: </label><input type="text" id="LastName-Forgot" name="LastName" placeholder="Last Name?"></th></tr>
                <tr><th><label for="Email-Forgot">Email Address: </label><input type="email" id="Email-Forgot" name="EmailAddress" placeholder="Email Address?"></th></tr>
                <tr><th><input type="submit" value="Recover password" class="btn btn-success"></th></tr>
            </table>
        </fieldset>
    </form>
</div>
<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
