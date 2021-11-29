package com.group18.capstone.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    private String jdbcURL = "jdbc:mysql://localhost:3306/user?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "CST2355Database";
    private static final String INSERT_USER_SQL = "INSERT INTO user.user" + "  (FirstName, LastName, UserName, Password, EmailAddress, Role) VALUES "
            + " (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "SELECT UserID, FirstName, LastName, UserName, Password, EmailAddress, Role FROM" +
            " user.user WHERE UserID = ?";
    private static final String SELECT_ALL_USER = "SELECT * FROM user.user";
    private static final String DELETE_USER_SQL = "DELETE from user.user WHERE UserID = ?;";
    private static final String UPDATE_USER_SQL = "UPDATE user.user SET FirstName=?,LastName=?,UserName=?,Password=?,EmailAddress=?,Role = ? WHERE UserID = ?;";



    // is user admin?

    public boolean isAdmin(User userLogin) throws ClassNotFoundException, SQLException {
        String role;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
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
    public String[] recoverPassword(User userForgot) throws ClassNotFoundException, SQLException{

        String password = null;
        String username = null;
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
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
    public boolean isLoginCorrect(User userLogin) throws ClassNotFoundException, SQLException {
        boolean status = false;
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
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
    public boolean checkEmailUser (User user) throws ClassNotFoundException,SQLException {
        boolean status = false;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user.user WHERE EmailAddress = ?")) {
            preparedStatement.setString(1, user.getEmailAddress());
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

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
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
// select all users to show on admin.jsp CRUD

    public List<User> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<User> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int UserID = rs.getInt("UserID");
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String EmailAddress = rs.getString("EmailAddress");
                String Role = rs.getString("Role");
                users.add(new User(UserID,FirstName,LastName,UserName ,Password ,EmailAddress ,Role));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }
    // get a single user for the editform.jsp
    public User getSingleUser(int UserID){
        User record = null;
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                 PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)){
            statement.setInt(1, UserID);
            ResultSet rs= statement.executeQuery();
            while(rs.next()){
                int userID = rs.getInt("UserID");
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String EmailAddress = rs.getString("EmailAddress");
                String Role = rs.getString("Role");
                record = new User(userID,FirstName,LastName,UserName ,Password ,EmailAddress ,Role);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return record;
    }

    // edit user
    public void updateUser(User user) throws SQLException {
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);) {
            System.out.println("updated User:"+statement);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmailAddress());
            statement.setString(6, user.getRole());
            statement.setInt(7, user.getUserID());
            statement.executeUpdate();
        }

    }

    // insert user
    public int addUser(User user) throws SQLException {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmailAddress());
            preparedStatement.setString(6, user.getRole());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    // delete user
    public void deleteUser(int UserID) throws SQLException {
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setInt(1, UserID);
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
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
