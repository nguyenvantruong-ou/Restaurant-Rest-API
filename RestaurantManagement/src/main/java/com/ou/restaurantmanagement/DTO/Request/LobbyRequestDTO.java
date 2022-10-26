package com.ou.restaurantmanagement.DTO.Request;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public class LobbyRequestDTO implements IBaseRequest{
    // phan trang
    private int size;
    private int page;

    // tim kiem
    private String kw;

    //

    private int id;
    private String lobName;

    private String lobAddress;

    private BigDecimal lobPrice;

    private Integer lobTotalTable;
    private boolean lobIsActive;

    private MultipartFile lobImage;
    private String key_Image;
    private List<MultipartFile> listImage;
    private List<String> listLinkImage;
    private String lobDescription;

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

    public MultipartFile getLobImage() {
        return lobImage;
    }

    public void setLobImage(MultipartFile lobImage) {
        this.lobImage = lobImage;
    }

    public List<MultipartFile> getListImage() {
        return listImage;
    }

    public void setListImage(List<MultipartFile> listImage) {
        this.listImage = listImage;
    }

    public String getLobDescription() {
        return lobDescription;
    }

    public void setLobDescription(String lobDescription) {
        this.lobDescription = lobDescription;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getKey_Image() {
        return key_Image;
    }

    public void setKey_Image(String key_Image) {
        this.key_Image = key_Image;
    }

    public List<String> getListLinkImage() {
        return listLinkImage;
    }

    public void setListLinkImage(List<String> listLinkImage) {
        this.listLinkImage = listLinkImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLobIsActive() {
        return lobIsActive;
    }

    public void setLobIsActive(boolean lobIsActive) {
        this.lobIsActive = lobIsActive;
    }
}
