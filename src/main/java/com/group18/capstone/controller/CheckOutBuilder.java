package com.group18.capstone.controller;

public class CheckOutBuilder {
    private int foodID;
    private String itemName;
    private float itemPrice;
    private int quantity;


    public CheckOutBuilder setFoodID(int foodID){
        this.foodID = foodID;
        return this;
    }
    public CheckOutBuilder setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public CheckOutBuilder setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }
    public CheckOutBuilder setQuantity(int quantity){
        this.quantity = quantity;
        return this;
    }

    public CheckOut createCheckOut() {
        return new CheckOut(foodID,itemName,itemPrice,quantity);
    }
}