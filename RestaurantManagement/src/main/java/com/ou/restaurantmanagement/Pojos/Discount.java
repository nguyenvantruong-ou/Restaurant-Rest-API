package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id", nullable = false)
    private Integer id;

    @Column(name = "discount_value")
    private double discountValue;

    @Column(name = "discount_from_date")
    private Date discountFromDate;

    @Column(name = "discount_to_date")
    private Date discountToDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public Date getDiscountFromDate() {
        return discountFromDate;
    }

    public void setDiscountFromDate(Date discountFromDate) {
        this.discountFromDate = discountFromDate;
    }

    public Date getDiscountToDate() {
        return discountToDate;
    }

    public void setDiscountToDate(Date discountToDate) {
        this.discountToDate = discountToDate;
    }
}
