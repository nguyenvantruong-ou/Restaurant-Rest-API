package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;

@Entity
@Table(name = "type_customer")
public class TypeCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_cus_id", nullable = false)
    private Integer id;

    @Column(name = "type_cus_name", nullable = false, length = 12)
    private String typeCustomerName;

    @Column(name = "type_cus_points", nullable = false, length = 12)
    private int typeCustomerPoints;

    @Column(name = "type_cus_discount", nullable = false, length = 12)
    private double typeCustomerDiscount;

    @Column(name = "type_customer_status", nullable = false, length = 12)
    private Boolean typeCustomerStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCustomerName() {
        return typeCustomerName;
    }

    public void setTypeCustomerName(String typeCustomerName) {
        this.typeCustomerName = typeCustomerName;
    }

    public int getTypeCustomerPoints() {
        return typeCustomerPoints;
    }

    public void setTypeCustomerPoints(int typeCustomerPoints) {
        this.typeCustomerPoints = typeCustomerPoints;
    }

    public double getTypeCustomerDiscount() {
        return typeCustomerDiscount;
    }

    public void setTypeCustomerDiscount(double typeCustomerDiscount) {
        this.typeCustomerDiscount = typeCustomerDiscount;
    }

    public Boolean getTypeCustomerStatus() {
        return typeCustomerStatus;
    }

    public void setTypeCustomerStatus(Boolean typeCustomerStatus) {
        this.typeCustomerStatus = typeCustomerStatus;
    }
}
