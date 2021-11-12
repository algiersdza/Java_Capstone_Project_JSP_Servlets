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
<title>Admin Page</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="style.css"/>
<body>
<div class="TOP-HEAD-ADMIN">
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
    <p>Below you can display, add, modify and edit users</p>
    <a href="index.jsp">Go back to index page</a>
</div>
</body>
</html>
