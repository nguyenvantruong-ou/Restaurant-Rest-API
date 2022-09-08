package com.ou.restaurantmanagement.Pojos;
import javax.persistence.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "lobby")
public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lob_id", nullable = false)
    private Integer id;

    @Column(name = "lob_name")
    private String lobName;

    @Column(name = "lob_address")
    private String lobAddress;

    @Column(name = "lob_price", precision = 12)
    private BigDecimal lobPrice;

    @Column(name = "lob_is_active")
    private Boolean lobIsActive;

    @Column(name = "lob_total_table")
    private Integer lobTotalTable;

    @Column(name = "lob_image")
    private String lobImage;

    @Column(name = "lob_description", length = 500)
    private String lobDescription;

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

    public String getLobName() {
        return lobName;
    }

    public void setLobName(String lobName) {
        this.lobName = lobName;
    }

    public String getLobAddress() {
        return lobAddress;
    }

    public void setLobAddress(String lobAddress) {
        this.lobAddress = lobAddress;
    }

    public BigDecimal getLobPrice() {
        return lobPrice;
    }

    public void setLobPrice(BigDecimal lobPrice) {
        this.lobPrice = lobPrice;
    }

    public Boolean getLobIsActive() {
        return lobIsActive;
    }

    public void setLobIsActive(Boolean lobIsActive) {
        this.lobIsActive = lobIsActive;
    }

    public Integer getLobTotalTable() {
        return lobTotalTable;
    }

    public void setLobTotalTable(Integer lobTotalTable) {
        this.lobTotalTable = lobTotalTable;
    }

    public String getLobImage() {
        return lobImage;
    }

    public void setLobImage(String lobImage) {
        this.lobImage = lobImage;
    }

    public String getLobDescription() {
        return lobDescription;
    }

    public void setLobDescription(String lobDescription) {
        this.lobDescription = lobDescription;
    }

}