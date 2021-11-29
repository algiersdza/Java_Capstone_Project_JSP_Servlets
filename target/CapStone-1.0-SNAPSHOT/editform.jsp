<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/12/2021
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>
<%@ page import="com.group18.capstone.controller.User" %>
<%@ page import="com.group18.capstone.dao.UserDao" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int UserID = Integer.parseInt(request.getParameter("id"));
    UserDao usID = new UserDao();
    User record = usID.getSingleUser(UserID);
    request.setAttribute("edit_user", record);
%>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

    <title>Edit User Data</title>
    <style>
        .inner{
            position: relative;
            margin: 0 auto;
            width: 650px;
            display: block;
            padding: 50px 0;
        }
        h3{
            text-align: center;
            border-bottom: 2px solid midnightblue;
            margin-bottom: 20px;
        }
        nav li a{
            font-size: 25px;
            font-weight: 500;
        }
        a{
            color: #fff;
            text-decoration: none;
        }
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: rgb(1, 1, 24)">
        <div>
            <a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand"> Restco
                Admin Management</a>
        </div>
        <div>
            <a href="<%=request.getContextPath()%>/admin.jsp" class="nav-link"> Admin Page</a>
        </div>
        <div>
            <form action="<%= request.getContextPath()%>/LogoutServlet" method="post">
                <input type="submit" value="Sign Out">
            </form>
        </div>
    </nav>
</header>
<div class="inner">
    <div class=" container">
        <div class="row">
            <div class="col-12">
                <h3>
                    Edit User Details</h3>
                <form action="EditUserServlet" method="post">
                    <div class="form-group">
                        <label>User ID</label>
                        <input class="form-control" name="UserID" value="${edit_user.userID }" required>
                    </div>
                    <div class="form-group">
                        <label>First Name</label>
                        <input class="form-control" name="FirstName" value="${edit_user.firstName }" required>
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <input class="form-control" name="LastName" value="${edit_user.lastName }" required>
                    </div>
                    <div class="form-group">
                        <label>Username</label>
                        <input class="form-control" name="UserName" value="${edit_user.userName }" required>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input class="form-control" type="password" name="Password" value="${edit_user.password }" required>
                    </div>
                    <div class="form-group">
                        <label>Email Address</label>
                        <input class="form-control" type="email" name="EmailAddress" value="${edit_user.emailAddress }" required>
                    </div>
                    <div class="form-group" >
                        <label>Role</label>
                        <select id="inputState" class="form-control" name="Role" required>
                            <option selected disabled>Choose Role</option>
                            <option value="admin">Admin</option>
                            <option value="user">Regular User</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <button  class="btn btn-primary"><a href="admin.jsp">Cancel</a></button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<%--<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>--%>

</body>
</html>

