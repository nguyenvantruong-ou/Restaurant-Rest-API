package com.ou.restaurantmanagement.DTO.Response;

import com.ou.restaurantmanagement.Pojos.Branch;
import com.ou.restaurantmanagement.Pojos.Lobby;

import java.math.BigDecimal;

public class LobbyCustomResponse {
    private Integer id;

    private String lobName;

    private String lobSlug;

    private String lobAddress;

    private BigDecimal lobPrice;

    private Boolean lobIsActive;

    private Integer lobTotalTable;

    private String lobImage;

    private String lobDescription;

    private Branch branchName;

    public LobbyCustomResponse(Lobby lobby, String slug){
        setId(lobby.getId());
        setLobName(lobby.getLobName());
        setLobSlug(slug);
        setLobPrice(lobby.getLobPrice());
        setLobIsActive(lobby.getLobIsActive());
        setLobTotalTable(lobby.getLobTotalTable());
        setLobImage(lobby.getLobImage());
        setLobDescription(lobby.getLobDescription());
        setBranchName(lobby.getBranch());
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

    public String getLobSlug() {
        return lobSlug;
    }

    public void setLobSlug(String lobSlug) {
        this.lobSlug = lobSlug;
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

    public Branch getBranchName() {
        return branchName;
    }

    public void setBranchName(Branch branchName) {
        this.branchName = branchName;
    }
}
