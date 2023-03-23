package com.mehmetkutaykeles.restfulapi.models;

public class Product {

    private long id;
    private float price;
    private int quantity;
    private String productName;
    private String ownerName;

    public Product(){

    }

    public Product(long id, float price, int quantity, String productName, String ownerName) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.productName = productName;
        this.ownerName = ownerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
            return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

}
