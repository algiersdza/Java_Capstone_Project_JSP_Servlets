package com.group18.capstone.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.group18.capstone.controller.User;
import com.group18.capstone.controller.UserBuilder;

public class UserDao {
    // add new user | Called from admin page
    private static final String INSERT_USER_SQL = "INSERT INTO user.user" + "  (FirstName, LastName, UserName, Password, EmailAddress, Role) VALUES "
            + " (?, ?, ?, ?, ?, ?);";

    // add new user | Called from UserRegister servlet.
    private static final String INSERT_INTO_SQL = "INSERT INTO user.user" + "(FirstName, LastName, UserName, Password, EmailAddress) VALUES "
            + "(?,?,?,?,?);";

    // Email validation | Called from UserRegister servlet
    private static final String IS_EMAIL_EXIST_SQL = "SELECT * FROM user.user WHERE EmailAddress = ?;";

    // checks if user is admin or user | Called from UserRegister servlet | result will validate user for appropriate page to show
    private static final String IS_USER_ADMIN_SQL = "SELECT Role FROM user.user WHERE UserName = ? AND Password = ?;";

    // Login validation | Called from LoginServlet
    private static final String IS_LOGIN_CORRECT_SQL = "SELECT * FROM user.user WHERE UserName = ? AND Password = ?;";

    // returns username and password in a string array | Called from RecoverServlet
    private static final String RECOVER_PASSWORD_SQL = "SELECT UserName, Password FROM user.user WHERE FirstName = ? AND LastName = ? AND EmailAddress = ?;";

    // edit existing user (shows initial existing information in the edit fields) | Called from admin page
    private static final String SELECT_USER_BY_ID = "SELECT UserID, FirstName, LastName, UserName, Password, EmailAddress, Role FROM" +
            " user.user WHERE UserID = ?";

    // show all users in admin page (List) | Called from admin page
    private static final String SELECT_ALL_USER = "SELECT * FROM user.user";

    // deletes user from database and list | Called from admin page
    private static final String DELETE_USER_SQL = "DELETE from user.user WHERE UserID = ?;";

    // used with SELECT_USER_BY_ID | Called from editform
    private static final String UPDATE_USER_SQL = "UPDATE user.user SET FirstName=?,LastName=?,UserName=?,Password=?,EmailAddress=?,Role = ? WHERE UserID = ?;";

    // is user admin?
    public boolean isAdmin(User userLogin) throws ClassNotFoundException, SQLException {
        String role;
         Connection singleCon = ConnectionDao.getSingleCon();
        try (
             PreparedStatement preparedStatement = singleCon.prepareStatement
                     (IS_USER_ADMIN_SQL)) {
            preparedStatement.setString(1, userLogin.getUserName());
            preparedStatement.setString(2, userLogin.getPassword());
            //System.out.println(preparedStatement);
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
        try(Connection singleCon = ConnectionDao.getSingleCon();
            PreparedStatement preparedStatement = singleCon.prepareStatement
                    (RECOVER_PASSWORD_SQL)){
            preparedStatement.setString(1, userForgot.getFirstName());
            preparedStatement.setString(2, userForgot.getLastName());
            preparedStatement.setString(3, userForgot.getEmailAddress());
            //System.out.println(preparedStatement);
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
        try(Connection singleCon = ConnectionDao.getSingleCon();
            PreparedStatement preparedStatement = singleCon.prepareStatement(IS_LOGIN_CORRECT_SQL)){
            preparedStatement.setString(1, userLogin.getUserName());
            preparedStatement.setString(2, userLogin.getPassword());
            //System.out.println(preparedStatement);
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
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(IS_EMAIL_EXIST_SQL)) {
            preparedStatement.setString(1, user.getEmailAddress());
            //System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
            return status;
        }
    }

    // register user works.
    public int registerUser (User user) throws ClassNotFoundException {
        int result = 0;
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(INSERT_INTO_SQL)) {
            //preparedStatement.setInt(1,);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmailAddress());
            //System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    // Below this line is all done within the admin.jsp
    //-------------------------------------------------//
    // select all users to show on admin.jsp CRUD
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(SELECT_ALL_USER)) {
            //System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int UserID = rs.getInt("UserID");
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String EmailAddress = rs.getString("EmailAddress");
                String Role = rs.getString("Role");
                users.add(new UserBuilder().setUserID(UserID).setFirstName(FirstName).setLastName(LastName).setUserName(UserName).setPassword(Password).setEmailAddress(EmailAddress).setRole(Role).createUser());
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    // get a single user for the editform.jsp
    public User getSingleUser(int UserID){
        User record = null;
        try (Connection singleCon = ConnectionDao.getSingleCon();
                 PreparedStatement preparedStatement = singleCon.prepareStatement(SELECT_USER_BY_ID)){
            preparedStatement.setInt(1, UserID);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                int userID = rs.getInt("UserID");
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String EmailAddress = rs.getString("EmailAddress");
                String Role = rs.getString("Role");
                record = new UserBuilder().setUserID(userID).setFirstName(FirstName).setLastName(LastName).setUserName(UserName).setPassword(Password).setEmailAddress(EmailAddress).setRole(Role).createUser();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return record;
    }

    // edit user, id can't be changed *WARNING*
    public void updateUser(User user) throws SQLException {
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(UPDATE_USER_SQL)) {
            //System.out.println("updated User:"+preparedStatement);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmailAddress());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setInt(7, user.getUserID());
            preparedStatement.executeUpdate();
        }
    }

    // insert user
    public int addUser(User user) throws SQLException {
        int result = 0;
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmailAddress());
            preparedStatement.setString(6, user.getRole());
            //System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    // delete user
    public void deleteUser(int UserID) throws SQLException {
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setInt(1, UserID);
            preparedStatement.execute();
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
