package com.ou.restaurantmanagement.DTO.Response;

import java.util.Date;

public class CommentResponse implements IBaseResponse{
    private int id;
    private String cmtContent;
    private Integer cmtStar;
    private Date createdDay;
    private boolean isIncognito;
    private String name;
    private String userImage;

    public CommentResponse(){
        this.userImage = "https://res.cloudinary.com/dfgdkopg4/image/upload/v1665027843/private-browsing_wqcikm.jpg";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public Integer getCmtStar() {
        return cmtStar;
    }

    public void setCmtStar(Integer cmtStar) {
        this.cmtStar = cmtStar;
    }

    public Date getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(Date createdDay) {
        this.createdDay = createdDay;
    }

    public boolean isIncognito() {
        return isIncognito;
    }

    public void setIncognito(boolean incognito) {
        isIncognito = incognito;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
