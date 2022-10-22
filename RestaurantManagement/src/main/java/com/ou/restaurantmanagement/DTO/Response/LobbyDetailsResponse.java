package com.ou.restaurantmanagement.DTO.Response;


import java.math.BigDecimal;
import java.util.List;

public class LobbyDetailsResponse implements IBaseResponse{
    private int id;
    private String lobName;
    private String lobAddress;
    private BigDecimal lobPrice;
    private Integer lobTotalTable;
    private String lobImage;
    private String lobDescription;
    private List<String> listImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<String> getListImage() {
        return listImage;
    }

    public void setListImage(List<String> listImage) {
        this.listImage = listImage;
    }
}
