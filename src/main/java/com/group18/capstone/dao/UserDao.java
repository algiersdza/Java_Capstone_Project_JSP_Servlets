package com.group18.capstone.dao;
import java.sql.*;

import com.group18.capstone.model.User;


public class UserDao {
    //email verfication

    public Boolean checkEmailUser (String email){
        String result="";
        String strQuery = "SELECT COUNT(*) FROM user.user where EmailAddress='"+email+"'";

//        String CHECK_EMAIL_SQL = "use user;" +
//        "SELECT * FROM user WHERE EmailAddress IN "+SQL_CLAUSE_EMAIL;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false", "root", "CST2355Database");
        { Statement st =connection.createStatement();
            ResultSet rs = st.executeQuery(strQuery);
            rs.next();
            String Countrow = rs.getString(1);
            if (Countrow.equals("0")){return true;}else {return false;}

        }catch (SQLException e)
        {printSQLExeception(e);}

    }



    public int registerUser (User user) throws ClassNotFoundException {
        String INSERT_INTO_SQL = "INSERT INTO user.user" + "(FirstName, LastName, UserName, Password, EmailAddress) VALUES "
                + "(?,?,?,?,?);";
        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false", "root", "CST2355Database");
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SQL)) {
            //preparedStatement.setInt(1,);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmailAddress());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLExeception(e);
        }
        return result;
    }

    private void printSQLExeception(SQLException error) {
        for (Throwable e: error) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = error.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}

