package com.group18.capstone.servlet;

import com.group18.capstone.dao.FoodDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteFoodServlet", value = "/DeleteFoodServlet")
public class DeleteFoodServlet extends HttpServlet {
    FoodDao foodDao;
    public DeleteFoodServlet(){}

    @Override
    public void init(){
        foodDao = new FoodDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int fid = Integer.parseInt(request.getParameter("id"));
        try {
            foodDao.deleteFood(fid);
            response.sendRedirect("admin-add-menu.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
