package com.group18.capstone.servlet;

import com.group18.capstone.controller.UserEmail;
import com.group18.capstone.controller.UserForgot;
import com.group18.capstone.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet(name = "RecoverServlet", value = "/RecoverServlet")
public class RecoverServlet extends HttpServlet {

    private UserDao userDao;

    public void init(){
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String FirstName = request.getParameter("FirstName");
        String LastName = request.getParameter("LastName");
        String EmailAddress = request.getParameter("EmailAddress");

        UserForgot userForgot = new UserForgot();
        userForgot.setFirstName(FirstName);
        userForgot.setLastName(LastName);
        userForgot.setEmailAddress(EmailAddress);

        //result from query is returned as string[]
        // check if array is null ( meaning no result is retrieved from the query but it still returns "null,null"
        try {
            String[] resultArray = userDao.recoverPassword(userForgot);
            boolean isNull = false;
            if (resultArray != null){
                for (int i=0;i<resultArray.length;i++){
                    if (resultArray[i] == null){
                        isNull = true;
                        break;
                    }
                }
            }
            if (isNull == false){
                PrintWriter out = response.getWriter();
                out.println("<h2>"+"Your username and password are: " + Arrays.toString(resultArray) +"</h2>");
                out.println("<a href ="+"index.jsp"+">Return to index page"+"</a>");
            }else {
                response.sendRedirect("noaccount.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
