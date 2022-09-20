package com.ou.restaurantmanagement.DTO.Request;

import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;

public class ServiceRequestDTO implements IBaseRequest{
    private Integer id;

    private String serName;

    private BigDecimal serPrice;

    private Boolean serIsActive;

    private String serDescription;

    private String serImage;

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public BigDecimal getSerPrice() {
        return serPrice;
    }

    public void setSerPrice(BigDecimal serPrice) {
        this.serPrice = serPrice;
    }

    public Boolean getSerIsActive() {
        return serIsActive;
    }

    public void setSerIsActive(Boolean serIsActive) {
        this.serIsActive = serIsActive;
    }

    public String getSerDescription() {
        return serDescription;
    }

    public void setSerDescription(String serDescription) {
        this.serDescription = serDescription;
    }


    public String getSerImage() {
        return serImage;
    }

    public void setSerImage(String serImage) {
        this.serImage = serImage;
    }
}
