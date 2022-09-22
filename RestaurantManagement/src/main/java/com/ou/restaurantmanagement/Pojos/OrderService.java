package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;

@Entity
@Table(name = "order_service")
public class OrderService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ord_id", nullable = false)
    private Order ord;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ser_id", nullable = false)
    private Service ser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrd() {
        return ord;
    }

    public void setOrd(Order ord) {
        this.ord = ord;
    }

    public Service getSer() {
        return ser;
    }

    public void setSer(Service ser) {
        this.ser = ser;
    }

}