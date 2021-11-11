<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/11/2021
  Time: 12:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>Sign Up Page</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="style.css"/>
<script src=""></script>
<body>

<div class="TOP-HEAD-SIGN-UP">
    <img id="LOGO-SIGN-UP" src="Images/logo.png" width = "300" height="200">
    <h3>Restaurant Management Operations Sign up Page</h3>
        <p>Welcome! To begin your adventure with us you are required to have an account.</p>
</div>

<div id="TOP-WRAP-SIGN-UP">
    <div class="SIGN-UP-SIGN-UP">
        <form class="SIGN-UP-FORM" action="<%= request.getContextPath()%>/UserRegister" method="post" >
            <fieldset>
                <table id="SIGN-UP-TABLE">
                    <tbody>
                    <tr><td><label for="FirstName-Sign-up">First Name: </label> <input type="text" id="FirstName-Sign-up" name="FirstName" placeholder="Enter your First Name"></td></tr>
                    <tr><td><label for="LastName-Sign-up"></label>Last Name: <input type="text" id="LastName-Sign-up" name="LastName" placeholder="Enter your Last Name"></td></tr>
                    <tr><td><label for = "Username-Sign-up">User Name: </label><input type="text" id = "Username-Sign-up" name="UserName" placeholder="Enter your username"></td></tr>
                    <tr><td><label for="Password-Sign-up">Password: </label><input type="password" id="Password-Sign-up" name="Password" placeholder="Enter your password"></td></tr>
                    <tr><td><label for="Email-Sign-up">Email: </label><input type="email" id="Email-Sign-up" name="EmailAddress" placeholder="Enter your email"></td></tr>
                    <tr><td><input type="submit" value="Register"></td></tr>
                    </tbody>
                </table>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
