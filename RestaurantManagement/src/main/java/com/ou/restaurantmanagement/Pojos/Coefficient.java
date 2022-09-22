package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;

@Entity
@Table(name = "coefficient")
public class Coefficient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coef_id", nullable = false)
    private Integer id;

    @Column(name = "coef_type_date", length = 100)
    private String coefTypeDate;

    @Column(name = "coef_type_lesson", length = 100)
    private String coefTypeLesson;

    @Column(name = "coef_value")
    private Double coefValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoefTypeDate() {
        return coefTypeDate;
    }

    public void setCoefTypeDate(String coefTypeDate) {
        this.coefTypeDate = coefTypeDate;
    }

    public String getCoefTypeLesson() {
        return coefTypeLesson;
    }

    public void setCoefTypeLesson(String coefTypeLesson) {
        this.coefTypeLesson = coefTypeLesson;
    }

    public Double getCoefValue() {
        return coefValue;
    }

    public void setCoefValue(Double coefValue) {
        this.coefValue = coefValue;
    }

}