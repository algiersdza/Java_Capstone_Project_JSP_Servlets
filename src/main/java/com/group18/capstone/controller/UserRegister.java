package com.group18.capstone.controller;

import com.group18.capstone.dao.UserDao;
import com.group18.capstone.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserRegister", value = "/UserRegister")
public class UserRegister extends HttpServlet {
    private UserDao userDao;

    public UserRegister(){}
    public void init(){
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        String FirstName = request.getParameter("FirstName");
        String LastName = request.getParameter("LastName");
        String UserName = request.getParameter("UserName");
        String Password = request.getParameter("Password");
        String EmailAddress = request.getParameter("EmailAddress");
        // email verification




        user.setFirstName(FirstName);
        user.setLastName(LastName);
        user.setUserName(UserName);
        user.setPassword(Password);
        user.setEmailAddress(EmailAddress);
        // pass the user info to sql
        try {
            userDao.registerUser(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.println("<p>"+"email sent"+"<p>");

        // to redirect to JSP page after registering.
        response.sendRedirect("index.jsp");
    }
}
