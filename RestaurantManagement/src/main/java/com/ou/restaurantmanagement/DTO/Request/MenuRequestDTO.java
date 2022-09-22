package com.ou.restaurantmanagement.DTO.Request;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class MenuRequestDTO implements IBaseRequest{
    private  int id;
    private String menuName;

    private BigDecimal menuPrice;

    private Boolean menuIsActive;

    private String menuImage;
    private MultipartFile file;


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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
