package com.group18.capstone.servlet;

import com.group18.capstone.controller.Food;
import com.group18.capstone.controller.FoodBuilder;
import com.group18.capstone.dao.FoodDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "AddToMenuServlet", value = "/AddToMenuServlet")
public class AddToMenuServlet extends HttpServlet {
    FoodDao foodDao;

    public AddToMenuServlet(){}

    @Override
    public void init(){
        foodDao = new FoodDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin-add-menu.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String ItemName = request.getParameter("ItemName");
        String ItemDesc = request.getParameter("ItemDesc");
        float ItemPrice = Float.parseFloat(request.getParameter("ItemPrice"));
        Food food = new FoodBuilder().setItemName(ItemName).setItemDesc(ItemDesc).setItemPrice(ItemPrice).createFood();
        try {
            foodDao.addFood(food);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('New food added!');");
            out.println("location='admin-add-menu.jsp';");
            out.println("</script>");
            response.sendRedirect("admin-add-menu.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
