package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id", nullable = false)
    private Integer id;

    @Column(name = "ord_created_date")
    private LocalDate ordCreatedDate;

    @Column(name = "ord_booking_date")
    private LocalDate ordBookingDate;

    @Column(name = "ord_booking_lesson", length = 45)
    private String ordBookingLesson;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coef_id")
    private Coefficient coef;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lob_id")
    private Lobby lob;

    @Column(name = "ord_is_payment")
    private boolean ordIsPayment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getOrdCreatedDate() {
        return ordCreatedDate;
    }

    public void setOrdCreatedDate(LocalDate ordCreatedDate) {
        this.ordCreatedDate = ordCreatedDate;
    }

    public LocalDate getOrdBookingDate() {
        return ordBookingDate;
    }

    public void setOrdBookingDate(LocalDate ordBookingDate) {
        this.ordBookingDate = ordBookingDate;
    }

    public String getOrdBookingLesson() {
        return ordBookingLesson;
    }

    public void setOrdBookingLesson(String ordBookingLesson) {
        this.ordBookingLesson = ordBookingLesson;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Coefficient getCoef() {
        return coef;
    }

    public void setCoef(Coefficient coef) {
        this.coef = coef;
    }

    public Lobby getLob() {
        return lob;
    }

    public void setLob(Lobby lob) {
        this.lob = lob;
    }

    /**
     * @return the ordIsPayment
     */
    public boolean isOrdIsPayment() {
        return ordIsPayment;
    }

    /**
     * @param ordIsPayment the ordIsPayment to set
     */
    public void setOrdIsPayment(boolean ordIsPayment) {
        this.ordIsPayment = ordIsPayment;
    }

}