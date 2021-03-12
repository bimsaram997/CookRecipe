package com.myapp.cookrecipe;

public class FoodData {
    private  String itemName;
    private String itemDescription;
    private String itemPrice;
    private int itemImage;
    private String itemLink;
    public FoodData(String itemName, String itemDescription, String itemPrice,  String itemLink,int itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        this.itemLink = itemLink;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public int getItemImage() {
        return itemImage;
    }

    public String getItemLink() {
        return itemLink;
    }
}
