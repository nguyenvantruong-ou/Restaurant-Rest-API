package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;
import java.math.BigDecimal;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ser_id", nullable = false)
    private Integer id;

    @Column(name = "ser_name", length = 255)
    private String serName;

    @Column(name = "ser_price", precision = 10)
    private BigDecimal serPrice;

    @Column(name = "ser_is_active")
    private Boolean serIsActive;

    @Column(name = "ser_description")
    private String serDescription;

    @Column(name = "ser_image")
    private String serImage;

    @Transient
    private MultipartFile file;

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
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