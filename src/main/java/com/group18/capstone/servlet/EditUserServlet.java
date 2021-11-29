package com.group18.capstone.servlet;

import com.group18.capstone.controller.User;
import com.group18.capstone.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditUserServlet", value = "/EditUserServlet")
public class EditUserServlet extends HttpServlet {
    UserDao userDao;
    User user;

    public EditUserServlet(){}

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user = new User();
        int UserID;
        String FirstName,LastName,UserName,Password,EmailAddress,Role;
        UserID = Integer.parseInt(request.getParameter("UserID"));
        FirstName = request.getParameter("FirstName");
        LastName = request.getParameter("LastName");
        UserName = request.getParameter("UserName");
        Password = request.getParameter("Password");
        EmailAddress = request.getParameter("EmailAddress");
        Role = request.getParameter("Role");
        user = new User(UserID,FirstName,LastName,UserName,Password,EmailAddress,Role);
        try {
            userDao.updateUser(user);
            response.sendRedirect("admin.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
