package com.group18.capstone.dao;

import com.group18.capstone.controller.Food;
import com.group18.capstone.controller.FoodBuilder;
import com.group18.capstone.controller.User;
import com.group18.capstone.controller.UserBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {

    // add to menu SQL
    private static final String ADD_FOOD_SQL = "INSERT INTO user.food" + "(ItemName, ItemDesc, ItemPrice) VALUES "
            + "(?,?,?);";
    private static final String DELETE_FOOD_SQL = "DELETE FROM user.food WHERE FoodID = ?;";
    private static final String SELECT_ALL_FOOD_SQL = "SELECT * FROM user.food;";


    public int addFood(Food food) throws SQLException {
        int result = 0;
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(ADD_FOOD_SQL)) {
            preparedStatement.setString(1, food.getItemName());
            preparedStatement.setString(2, food.getItemDesc());
            preparedStatement.setDouble(3, food.getItemPrice());
            //System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    public void deleteFood(int FoodID) throws SQLException {
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(DELETE_FOOD_SQL)) {
            preparedStatement.setInt(1, FoodID);
            preparedStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Food> selectAllItems() {
        List<Food> foods = new ArrayList<>();
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(SELECT_ALL_FOOD_SQL)) {
            //System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int FoodID = rs.getInt("FoodID");
                String ItemName = rs.getString("ItemName");
                String ItemDesc = rs.getString("ItemDesc");
                float ItemPrice = rs.getFloat("ItemPrice");
                foods.add(new FoodBuilder().setFoodID(FoodID).setItemName(ItemName).setItemDesc(ItemDesc).setItemPrice(ItemPrice).createFood());
//                users.add(new UserBuilder().setUserID(UserID).setFirstName(FirstName).setLastName(LastName).setUserName(UserName).setPassword(Password).setEmailAddress(EmailAddress).setRole(Role).createUser());
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return foods;
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
