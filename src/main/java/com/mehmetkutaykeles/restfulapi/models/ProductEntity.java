package com.mehmetkutaykeles.restfulapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("products")
public class ProductEntity {

    @Id
    @Column("ID")
    private long id;

    @Column("PRICE")
    private float price;

    @Column("QUANTITY")
    private int quantity;

    @Column("PRODUCT_NAME")
    private String productName;

    @Column("OWNER_NAME")
    private String ownerName;

    public ProductEntity(){

    }
    public ProductEntity(long id, float price, int quantity, String productName, String ownerName) {
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
