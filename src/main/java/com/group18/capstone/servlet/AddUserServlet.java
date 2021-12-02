package com.group18.capstone.servlet;

import com.group18.capstone.controller.User;
import com.group18.capstone.controller.UserBuilder;
import com.group18.capstone.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "AddUserServlet", value = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private UserDao userDao;

    public AddUserServlet(){}
    public void init(){
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
//        UserEmail userEmail = new UserEmail();

        String FirstName = request.getParameter("FirstName");
        String LastName = request.getParameter("LastName");
        String UserName = request.getParameter("UserName");
        String Password = request.getParameter("Password");
        String EmailAddress = request.getParameter("EmailAddress");
        String Role = request.getParameter("Role");
        // email verification
        User user = new UserBuilder().setEmailAddress(EmailAddress).createUser();
//        user.setEmailAddress(EmailAddress);

        try {
            // if email is already present in the database return sign up failed
            if (userDao.checkEmailUser(user)){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Email Already Registered!');");
                out.println("location='admin.jsp';");
                out.println("</script>");
                //response.sendRedirect("admin.jsp");
            }else {
                user = new UserBuilder().setFirstName(FirstName).setLastName(LastName).setUserName(UserName).setPassword(Password).setEmailAddress(EmailAddress).setRole(Role).createUser();
//                user.setFirstName(FirstName);
//                user.setLastName(LastName);
//                user.setUserName(UserName);
//                user.setPassword(Password);
//                user.setEmailAddress(EmailAddress);
//                user.setRole(Role);
                // pass the user info to sql
                userDao.addUser(user);
                // to redirect to JSP page after registering.
                response.sendRedirect("admin.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
