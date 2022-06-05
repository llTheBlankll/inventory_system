package com.nytri.inventory_system.entity;

import javax.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @Column(name = "product_id", nullable = false, length = 40)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 40)
    private String productName;

    @Column(name = "product_description", nullable = false, length = 40)
    private String productDescription;

    @Column(name = "product_unit", length = 40)
    private String productUnit;

    @Column(name = "product_price", nullable = false)
    private Double productPrice;

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;

    @Column(name = "product_status", length = 25)
    private String productStatus;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "product_other_details", length = 100)
    private String productOtherDetails;

    public Stock(Integer id, String productName, String productDescription, String productUnit, Double productPrice, Integer productQuantity, String productStatus, Categories category, String productOtherDetails) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productUnit = productUnit;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productStatus = productStatus;
        this.category = category;
        this.productOtherDetails = productOtherDetails;
    }

    public Stock() {

    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductOtherDetails() {
        return productOtherDetails;
    }

    public void setProductOtherDetails(String productOtherDetails) {
        this.productOtherDetails = productOtherDetails;
    }

}