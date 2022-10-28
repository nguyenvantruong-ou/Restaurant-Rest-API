package com.ou.restaurantmanagement.DTO.Response;

import java.math.BigDecimal;

public class LobbyComboboxResponse implements IBaseResponse{
    private int lobId;
    private String lobName;
    private String lobImage;
    private BigDecimal lobPrice;

    public int getLobId() {
        return lobId;
    }

    public void setLobId(int lobId) {
        this.lobId = lobId;
    }

    public String getLobName() {
        return lobName;
    }

    public void setLobName(String lobName) {
        this.lobName = lobName;
    }

    public String getLobImage() {
        return lobImage;
    }

    public void setLobImage(String lobImage) {
        this.lobImage = lobImage;
    }

    public BigDecimal getLobPrice() {
        return lobPrice;
    }

    public void setLobPrice(BigDecimal lobPrice) {
        this.lobPrice = lobPrice;
    }
}
