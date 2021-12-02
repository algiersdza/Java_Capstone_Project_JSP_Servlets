package com.group18.capstone.controller;

public class Food {


    private int FoodID;
    private String ItemName;
    private String ItemDesc;
    private float ItemPrice;
    public Food(){}

    public Food(int foodID, String itemName, String itemDesc, float itemPrice){
        this.FoodID = foodID;
        this.ItemName = itemName;
        this.ItemDesc = itemDesc;
        this.ItemPrice = itemPrice;
    }

    public int getFoodID() {
        return FoodID;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getItemDesc() {
        return ItemDesc;
    }

    public double getItemPrice() {
        return ItemPrice;
    }



}
