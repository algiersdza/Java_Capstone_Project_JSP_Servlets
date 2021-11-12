package com.group18.capstone.dao;

import com.group18.capstone.controller.User;
import com.group18.capstone.controller.UserCurd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurdDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/user?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "CST2355Database";

    private static final String INSERT_USER_SQL = "INSERT INTO user" + "  (FirstName, LastName, UserName, Password, EmailAddress, Role) VALUES "
            + " (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "SELECT UserID, FirstName, UserName, Password, EmailAddress, Role FROM" +
            " user WHERE UserID = ?";
    private static final String SELECT_ALL_USER = "SELECT * FROM user";
    private static final String DELETE_USER_SQL = "DELETE from user WHERE UserID = ?;";
    private static final String UPDATE_USER_SQL = "UPDATE user SET Role = ? WHERE UserID = ?;";

    public CurdDao(){}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        return connection;
    }

    // select user
    public UserCurd selectUser(int UserID){
        UserCurd userCurd = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);){
            preparedStatement.setInt(1,UserID);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String EmailAddress = rs.getString("EmailAddress");
                String Role = rs.getString("Role");
                userCurd = new UserCurd(UserID,FirstName,LastName,UserName,Password,EmailAddress,Role);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return userCurd;
    }

    // update user
    public boolean updateRole(UserCurd userCurd) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);) {
            System.out.println("updated User:"+statement);
            statement.setString(1, userCurd.getRole());
            statement.setInt(2, userCurd.getUserID());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    // insert user
    public void insertUser(UserCurd userCurd) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, userCurd.getFirstName());
            preparedStatement.setString(2, userCurd.getLastName());
            preparedStatement.setString(3, userCurd.getUserName());
            preparedStatement.setString(4, userCurd.getPassword());
            preparedStatement.setString(5, userCurd.getEmailAddress());
            preparedStatement.setString(6, userCurd.getRole());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // delete user
    public boolean deleteUser(int UserID) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);) {
            statement.setInt(1, UserID);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    //select all user
    public List<UserCurd> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<UserCurd> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
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
                users.add(new UserCurd(UserID,FirstName,LastName,UserName ,Password ,EmailAddress ,Role));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
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
