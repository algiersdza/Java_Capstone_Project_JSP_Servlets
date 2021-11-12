package com.group18.capstone.dao;
import java.sql.*;

import com.group18.capstone.controller.UserEmail;
import com.group18.capstone.controller.UserForgot;
import com.group18.capstone.controller.UserLogin;
import com.group18.capstone.controller.User;

// db name is user
// mysql root
// password: CST2355Database

//CREATE TABLE IF NOT EXISTS `user`.`user` (
//                                             `UserID` int(3) AUTO_INCREMENT NOT NULL,
//                                             `FirstName` VARCHAR(45) DEFAULT NULL,
//                                             `LastName` VARCHAR(45) DEFAULT NULL,
//                                             `UserName` VARCHAR(45) DEFAULT NULL,
//                                             `Password` VARCHAR(45) DEFAULT NULL,
//                                             `EmailAddress` VARCHAR(45) DEFAULT NULL,
//                                             `Role` VARCHAR(45) DEFAULT 'user' NULL,
//                                             PRIMARY KEY (UserID))
//    ENGINE = InnoDB DEFAULT CHARSET =utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
//
//INSERT INTO `user`.user (FirstName, LastName, UserName, Password, EmailAddress, Role) VALUES
//                        ('Root','Admin','AdminSys','iamroot','root@mail.com','admin');




public class UserDao {
    // is user admin?

    public boolean isAdmin(UserLogin userLogin) throws ClassNotFoundException, SQLException {
        String role;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false", "root", "CST2355Database");
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT Role FROM user.user WHERE UserName = ? AND Password = ?")) {
            preparedStatement.setString(1, userLogin.getUserName());
            preparedStatement.setString(2, userLogin.getPassword());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                role = rs.getString("Role");
                if (role.equals("user")) {
                    return true;
                }
            }
            return false;
        }

    }

    // password recovery
    public String[] recoverPassword(UserForgot userForgot) throws ClassNotFoundException, SQLException{

        String password = null;
        String username = null;
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false", "root", "CST2355Database");
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT UserName, Password FROM user.user WHERE FirstName = ? AND LastName = ? AND EmailAddress = ?")){
            preparedStatement.setString(1, userForgot.getFirstName());
            preparedStatement.setString(2, userForgot.getLastName());
            preparedStatement.setString(3, userForgot.getEmailAddress());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                password = rs.getString("Password");
                username = rs.getString("UserName");

//                userForgot.setPassword(password);
//                userForgot.setUserName(username);
            }

        }catch (SQLException e){
            printSQLException(e);
        }

        return new String[] {username,password};
    }


    // user login works.
    public boolean isLoginCorrect(UserLogin userLogin) throws ClassNotFoundException, SQLException {
        boolean status = false;
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false", "root", "CST2355Database");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user.user WHERE UserName = ? AND Password = ?")){
            preparedStatement.setString(1, userLogin.getUserName());
            preparedStatement.setString(2, userLogin.getPassword());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        }catch (SQLException e){
            printSQLException(e);
        }
        return status;
    }

    //email verification works.
    public boolean checkEmailUser (UserEmail userEmail) throws ClassNotFoundException,SQLException {
        boolean status = false;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false", "root", "CST2355Database");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user.user WHERE EmailAddress = ?")) {
            preparedStatement.setString(1, userEmail.getEmailAddress());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
            return status;
        }
    }

    // register user works.
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
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException error){
        for (Throwable e : error) {
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
