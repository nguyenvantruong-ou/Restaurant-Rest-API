package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", nullable = false)
    private Integer id;

    @Column(name = "menu_name", nullable = false, length = 45)
    private String menuName;

    @Column(name = "menu_price", precision = 12)
    private BigDecimal menuPrice;

    @Column(name = "menu_is_active")
    private Boolean menuIsActive;

    @Column(name = "menu_image", length = 300)
    private String menuImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public BigDecimal getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(BigDecimal menuPrice) {
        this.menuPrice = menuPrice;
    }

    public Boolean getMenuIsActive() {
        return menuIsActive;
    }

    public void setMenuIsActive(Boolean menuIsActive) {
        this.menuIsActive = menuIsActive;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

}