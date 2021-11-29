<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/10/2021
  Time: 11:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>Index Page</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<style>
    fieldset{
        display: inline-block;
    }
    .SIGN-UP{
        margin-top: 20px;
    }
</style>
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


<div class="TOP-HEAD-INDEX">
    <img src="<c:url value='/Images/logo.png'/>" id="LOGO-INDEX" width = "300" height="200" alt="Logo_Index">
    <h3>RestCo</h3>
        <p>Welcome! To begin your adventure with us you are required to have an account.</p>
</div>

<div id="TOP-WRAP-INDEX">
    <div class="container">
        <form class="SIGN-IN-FORM" action="<%= request.getContextPath()%>/LoginServlet" method="post">
            <fieldset>
                <table class="table table-bordered">
                    <tbody>
                    <tr><th><label for="Username-Login">UserName: </label><input type="text" id ="Username-Login" name="UserName" placeholder="Enter your username"></th></tr>
                    <tr><th><label for="Password-Login">Password: </label><input type="password" id="Password-Login" name="Password" placeholder="Enter your password"></th></tr>
                    <tr><th><input type="submit"  class="btn btn-success" value="Login"></th></tr>
                    </tbody>
                </table>
<%--                <p>--%>
<%--                    <a href="forgotrequest.jsp">Forgot password</a>--%>
<%--                </p>--%>
            </fieldset>
        </form>
    </div>
<!--    <div class="SIGN-UP-INDEX">
        <fieldset>
            <p>
                Alternatively you can sign up for an account here
            </p>
            <a href="signup.jsp">Sign up</a>
        </fieldset>
    </div>  -->
</div>
<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
