package com.group18.capstone.servlet;

import com.group18.capstone.controller.User;
import com.group18.capstone.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteUserServlet", value = "/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    UserDao userDao;
    public DeleteUserServlet(){}
    public void init(){
        userDao = new UserDao();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("id"));
        try {
            userDao.deleteUser(uid);
            response.sendRedirect("admin.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
