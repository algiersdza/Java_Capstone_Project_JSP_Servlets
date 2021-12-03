package com.group18.capstone.controller;

public class CheckOut extends Food{

    private int FoodID;
    private String ItemName;
    private float ItemPrice;
    private int Quantity;

    public CheckOut(){}
    CheckOut(int foodID, String itemName,float itemPrice,int quantity){
        this.FoodID = foodID;
        this.ItemName = itemName;
        this.ItemPrice = itemPrice;
        this.Quantity = quantity;
    }

    //override because parent class is return 0, while i want this object from checkout specifically.
    @Override
    public int getFoodID() {
        return FoodID;
    }

    @Override
    public String getItemName() {
        return ItemName;
    }

    @Override
    public float getItemPrice() {
        return ItemPrice;
    }

    //    CheckOut(String itemName, float itemPrice,int quantity){
//        this.ItemName = itemName;
//        this.ItemPrice = itemPrice;
//        this.Quantity = quantity;
//    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    @Override
    public String toString() {
        return "CheckOut{" +
                "FoodID=" + FoodID +
                ", ItemName='" + ItemName + '\'' +
                ", ItemPrice=" + ItemPrice +
                ", Quantity=" + Quantity +
                '}';
    }
}