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
<body>
<div class="TOP-HEAD-INDEX">
    <img id="LOGO-INDEX" src="Images/logo.png" width = "300" height="200">
    <h3>Restaurant Management Operations Index Page</h3>
        <p>Welcome! To begin your adventure with us you are required to have an account.</p>
</div>

<div id="TOP-WRAP-INDEX">
    <div class="SIGN-IN-INDEX">
        <form class="SIGN-IN-FORM" action="<%= request.getContextPath()%>/LoginServlet" method="post">
            <fieldset>
                <table id="LOGIN-TABLE">
                    <tbody>
                    <tr><td><label for="Username-Login">UserName: </label><input type="text" id ="Username-Login" name="UserName" placeholder="Enter your username"></td></tr>
                    <tr><td><label for="Password-Login">Password: </label><input type="password" id="Password-Login" name="Password" placeholder="Enter your password"></td></tr>
                    <tr><td><input type="submit" value="Login"></td></tr>
                    </tbody>
                </table>
                <p>
                    <a href="forgotrequest.jsp">Forgot password</a>
                </p>
            </fieldset>
        </form>
    </div>
    <div class="SIGN-UP-INDEX">
        <fieldset>
            <p>
                Alternatively you can sign up for an account here
            </p>
            <a href="signup.jsp">Sign up</a>
        </fieldset>
    </div>
</div>
</body>
</html>
