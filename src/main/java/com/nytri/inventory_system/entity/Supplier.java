package com.nytri.inventory_system.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @Column(name = "supplier_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "supplier_name", nullable = false, length = 32)
    private String supplierName;

    @Column(name = "supplier_address", nullable = false, length = 64)
    private String supplierAddress;

    @Column(name = "supplier_phone", length = 24)
    private String supplierPhone;
    @Column(name = "supplier_fax", length = 24)
    private String supplierFax;

    @Column(name = "supplier_email", nullable = false, length = 64)
    private String supplierEmail;

    @Column(name = "supplier_other_details", length = 64)
    private String supplierOtherDetails;


    public Supplier() {

    }


    public Supplier(Integer id, String supplierName, String supplierAddress, String supplierPhone, String supplierFax, String supplierEmail, String supplierOtherDetails, List<Stock> supplierStock) {
        this.id = id;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierPhone = supplierPhone;
        this.supplierFax = supplierFax;
        this.supplierEmail = supplierEmail;
        this.supplierOtherDetails = supplierOtherDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierFax() {
        return supplierFax;
    }

    public void setSupplierFax(String supplierFax) {
        this.supplierFax = supplierFax;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierOtherDetails() {
        return supplierOtherDetails;
    }

    public void setSupplierOtherDetails(String supplierOtherDetails) {
        this.supplierOtherDetails = supplierOtherDetails;
    }

}