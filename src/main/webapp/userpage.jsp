<%@ page import="com.group18.capstone.controller.Food" %>
<%@ page import="com.group18.capstone.dao.FoodDao" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/12/2021
  Time: 1:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    FoodDao foodData = new FoodDao();
    List<Food> foods = foodData.selectAllItems();
    request.setAttribute("FOOD_LIST", foods);
%>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>User Page</title>
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
        <div class="TOP-HEAD-ADMIN">
            <form class="form-inline my-2 my-lg-0" action="<%= request.getContextPath()%>/LogoutServlet" method="post">
                <button class="btn btn-secondary my-2 my-sm-0" type="submit">Sign Out</button>
            </form>
        </div>
    </nav>
</header>
<%
    if (session.getAttribute("UserName")== null){
        response.sendRedirect("index.jsp");
    }
%>
<h1>User Page</h1>
Welcome ${UserName}

<p class="text-center">We welcome you to taste our delicious dishes prepared by the Michelin star recipients!</p>
<div class="container">
    <div class="row">
        <div class="col-md-11">
            <h5 class="text-center">Select your order</h5>
            <hr>
            <br>
            <form action="AddToCartServlet" method="post">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Item</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="food" items = "${FOOD_LIST}">
                        <tr>
                            <td>${food.itemName}</td>
                            <td>${food.itemDesc}</td>
                            <td>$${food.itemPrice}</td>
                            <td><input type="number" min="0" name="quantity"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <table class="table table-bordered">
                    <button type="submit" class="btn btn-secondary">Add to cart</button>
                </table>
            </form>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-11">
            <h5 class="text-center"></h5>
            <hr>
            <br>
            <form>
                <table class="table table-bordered">
                    <thead>

                    </thead>
                </table>
            </form>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-5">
            <h5 class="text-center">Your Cart</h5>
            <hr>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Item added</th>
                    <th>Amount</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="checkout" items = "${CHECKOUT_LIST}">
                    <tr>
                        <td>${checkout.itemName}</td>
                        <td>${checkout.amount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-sm-5">
            <h5 class="text-center">Your total</h5>
            <hr>
            <form action="CheckOutServlet" method="post">
                <table class="table table-sm table-borderless table-dark">
                    <tbody>
                    <tr>
                        <td>Subtotal:</td>
                        <td>subtotal goes here</td>
                    </tr>
                    <tr>
                        <td>Tax:</td>
                        <td>tax goes here</td>
                    </tr>
                    <tr>
                        <td>Total:</td>
                        <td>total goes here</td>
                    </tr>
                    </tbody>
                </table>
                <table>
                    <button type="submit" class="btn btn-secondary">Submit Order</button>
                </table>
            </form>
        </div>
    </div>
</div>
<br>
<br>
<br>


<div class="container">
    <img src="<c:url value='/Images/logo.png'/>" id="Logo" width = "300" height="200" class="rounded mx-auto d-block" alt="Logo_Index">
</div>
<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
