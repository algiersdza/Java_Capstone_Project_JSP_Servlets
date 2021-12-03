package com.group18.capstone.dao;

import com.group18.capstone.controller.*;


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
    private static final String SELECT_FOOD_FOR_CHECKOUT_SQL = "SELECT * FROM user.food WHERE FoodID = ?;";
    private static final String SELECT_PRICE_FOR_TOTAL_SQL = "SELECT ItemPrice FROM user.food WHERE FoodID =?;";


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

    // add to cart
    // find the food from db
    // sent to cart/CheckOut
    public CheckOut getSingleFood(int fid){
        CheckOut record = null;
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(SELECT_FOOD_FOR_CHECKOUT_SQL)){
            preparedStatement.setInt(1, fid);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                int FoodID = rs.getInt("FoodID");
                String FoodName = rs.getString("ItemName");
                float ItemPrice = Float.parseFloat(rs.getString("ItemPrice"));
                record = new CheckOutBuilder().setFoodID(FoodID).setItemName(FoodName).setItemPrice(ItemPrice).setQuantity(1).createCheckOut();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return record;
    }

    //display total
    public float getTotal(ArrayList<CheckOut> cartList){
        float sum = 0;
        try (Connection singleCon = ConnectionDao.getSingleCon();
            PreparedStatement preparedStatement = singleCon.prepareStatement(SELECT_PRICE_FOR_TOTAL_SQL)) {
            if (cartList.size() > 0){
                for (CheckOut item:cartList){
                    preparedStatement.setInt(1,item.getFoodID());
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()){
                        sum+=rs.getFloat("ItemPrice")*item.getQuantity();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public List<CheckOut> getCartStuff(ArrayList<CheckOut> cartList) {
//        System.out.println("inside getCartStuff ->cartList: "+cartList);
        List<CheckOut> stuff = new ArrayList<>();
        try (Connection singleCon = ConnectionDao.getSingleCon();
             PreparedStatement preparedStatement = singleCon.prepareStatement(SELECT_FOOD_FOR_CHECKOUT_SQL)){
            if (cartList.size() > 0) {
                for (CheckOut item:cartList) {
//                    System.out.println("inside getCartStuff-> item: "+item);
//                    System.out.println("inside getCartStuff-> item.getFoodID(): "+item.getFoodID());
                    preparedStatement.setInt(1,item.getFoodID());
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        CheckOut row = new CheckOutBuilder().setFoodID(rs.getInt("FoodID"))
                                .setItemName(rs.getString("ItemName"))
                                .setItemPrice(rs.getFloat("ItemPrice")*item.getQuantity())
                                .setQuantity(item.getQuantity())
                                .createCheckOut();
                        stuff.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
//        System.out.println("inside getCartStuff -> stuff: "+stuff);
        return stuff;
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
