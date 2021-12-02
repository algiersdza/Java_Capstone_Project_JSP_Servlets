package com.group18.capstone.controller;

public class FoodBuilder {
    private int foodID;
    private String itemName;
    private String itemDesc;
    private float itemPrice;

    public FoodBuilder setFoodID(int foodID) {
        this.foodID = foodID;
        return this;
    }

    public FoodBuilder setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public FoodBuilder setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
        return this;
    }

    public FoodBuilder setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public Food createFood() {
        return new Food(foodID, itemName, itemDesc, itemPrice);
    }
}