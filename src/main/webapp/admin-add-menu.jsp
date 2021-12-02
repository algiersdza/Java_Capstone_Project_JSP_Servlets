<%@ page import="com.group18.capstone.dao.FoodDao" %>
<%@ page import="com.group18.capstone.controller.Food" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 12/2/2021
  Time: 5:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    FoodDao foodData = new FoodDao();
    List<Food> foods = foodData.selectAllItems();
    request.setAttribute("FOOD_LIST", foods);
%>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Add to Menu</title>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style=" background-color: #343a40">
        <div>
            <a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand">
                <img src="<c:url value='/Images/r-header.png'/>"
                     width="30" height="30" class="d-inline-block align-top" alt=""> Restco
                Admin Management
            </a>
        </div>
        <div>
            <ul class="navbar-nav">
                <li>
                    <a href="<%=request.getContextPath()%>/admin.jsp" class="nav-link"> Admin Page</a>
                </li>
            </ul>
        </div>
        <div>
            <form class="form-inline my-2 my-lg-0" action="<%= request.getContextPath()%>/LogoutServlet" method="post">
                <button class="btn btn-secondary my-2 my-sm-0" type="submit">Sign Out</button>
            </form>
        </div>
    </nav>
</header>
<!-- check if logged session is active -->
<%
    if (session.getAttribute("UserName")== null){
        response.sendRedirect("index.jsp");
    }
%>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <h3>Create New Food Item</h3>
            <form action="AddToMenuServlet" method="post">
                <div class="form-group">
                    <label>Item Name</label>
                    <input class="form-control" name="ItemName" placeholder="Food Item" required>
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input class="form-control" name="ItemDesc" placeholder="Description (200 char max)" required>
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input class="form-control" type="number" min="1.00" step="0.01" name="ItemPrice"
                           placeholder="Price 00.00" required>
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
                <button type="reset" class="btn btn-primary">Reset</button>
            </form>
        </div>
        <div class="col-md-9">
            <h3 class="text-center">List of Food</h3>
            <hr>
            <br>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Item</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="food" items="${FOOD_LIST}">
                    <tr>
                        <td>${food.itemName}</td>
                        <td>${food.itemDesc}</td>
                        <td>$${food.itemPrice}</td>
                        <td><a href="DeleteFoodServlet?id=${food.foodID}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
