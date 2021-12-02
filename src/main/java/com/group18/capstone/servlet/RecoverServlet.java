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

        User userForgot = new UserBuilder().setFirstName(FirstName).setLastName(LastName).setEmailAddress(EmailAddress).createUser();
//        userForgot.setFirstName(FirstName);
//        userForgot.setLastName(LastName);
//        userForgot.setEmailAddress(EmailAddress);

        //result from query is returned as string[]
        // check if array is null ( meaning no result is retrieved from the query, but it still returns "null,null"
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
            if (!isNull){
                PrintWriter out = response.getWriter();
                out.println("<html lang="+"\"en\""+">");
                out.println("<meta charset="+"\"UTF-8\""+">");
                out.println("<meta name="+"\"viewport\""+"content=\"width=device-width,initial-scale=1\""+">");
                out.println("<head>"+"<title>"+"Recovery Sent"+"</title>"+"</head>");
                out.println("<body>");
                out.println("<h3"+" style="+"\"text-align:center\""+">"+"RestCo"+"</h3>");
                out.println("<h3>"+"We sent the recovery information to your email.***(MOCKUP)***"+"</h3>");
                out.println("<h2>"+"Your username and password are: " + Arrays.toString(resultArray) +"</h2>");
                out.println("<a href ="+"index.jsp"+">Return to index page"+"</a>");
                out.println("<footer>"+"<p>"+"<small>"+"©"+"2021 copyright."+" All Rights Reserved."+"</small>"+"</p>"+"</footer>");
                out.println("</body>");
                out.println("</html>");
            }else {
                response.sendRedirect("noaccount.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
