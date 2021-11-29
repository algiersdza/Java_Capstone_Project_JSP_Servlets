<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/12/2021
  Time: 2:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>No account</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: rgb(1, 1, 24)">
        <div>
            <a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand"> Restco
                Admin Management</a>
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
    </nav>
</header>
<div class="TOP-HEAD-no-account">
    <h1>We did not find your information in our database</h1>
    <h2>Please consider making an account.</h2>
    <h3>If you wish to recover your password<a href="forgotrequest.jsp">Click here</a></h3>
    <a href="signup.jsp">Go to the sign up page</a>
    <br>
    <a href="index.jsp">Go back to index page</a>
</div>
</body>
</html>
