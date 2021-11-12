<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/12/2021
  Time: 1:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>User Page</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="style.css"/>
<body>
<div class="TOP-HEAD-USER">
    <form action="<%= request.getContextPath()%>/LogoutServlet" method="post">
        <input type="submit" value="Sign Out">
    </form>

    <%
        if (session.getAttribute("UserName")== null){
            response.sendRedirect("index.jsp");
        }
    %>
    <h1>Login Successful!</h1>
    Welcome ${UserName}
    <!-- <h2>Welcome</h2> -->
    <p>You are a user role, not much to do here</p>
</div>
</body>
</html>
