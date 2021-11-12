<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/11/2021
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>Forgot Password Page</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="style.css"/>
<body>
<div class="TOP-HEAD-FORGOT">
    <h1>To retrieve your password please answer the following questions:</h1>
</div>
<div class="FORGOT-TABLE">
    <form class="FORM-FORGOT" action="<%= request.getContextPath()%>/RecoverServlet" method="post">
        <fieldset>
            <table id="FORGOT-PASSWORD-TABLE">
                <tr><td><label for="FirstName-Forgot">First Name: </label><input type="text" id="FirstName-Forgot" name="FirstName" placeholder="First Name?"></td></tr>
                <tr><td><label for="LastName-Forgot">Last Name: </label><input type="text" id="LastName-Forgot" name="LastName" placeholder="Last Name?"></td></tr>
                <tr><td><label for="Email-Forgot">Email Address: </label><input type="email" id="Email-Forgot" name="EmailAddress" placeholder="Email Address?"></td></tr>
                <tr><td><input type="submit" value="Recover password"></td></tr>
            </table>
        </fieldset>
    </form>
</div>
</body>
</html>
