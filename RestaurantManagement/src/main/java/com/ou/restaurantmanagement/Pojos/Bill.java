package com.ou.restaurantmanagement.Pojos;

import java.math.BigDecimal;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @Column(name = "bill_id", nullable = false)
    private Integer id;

//    @MapsId
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "bill_id", nullable = false)
//    private Order order;

    @Column(name = "bill_created_date")
    private LocalDate billCreatedDate;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "bill_total_money", length = 45)
    private BigDecimal billTotalMoney;

    @Column(name = "bill_note", length = 500)
    private String billNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_discount_id")
    private Discount discount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBillCreatedDate() {
        return billCreatedDate;
    }

    public void setBillCreatedDate(LocalDate billCreatedDate) {
        this.billCreatedDate = billCreatedDate;
    }


    public BigDecimal getBillTotalMoney() {
        return billTotalMoney;
    }

    public void setBillTotalMoney(BigDecimal billTotalMoney) {
        this.billTotalMoney = billTotalMoney;
    }

    public String getBillNote() {
        return billNote;
    }

    public void setBillNote(String billNote) {
        this.billNote = billNote;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}