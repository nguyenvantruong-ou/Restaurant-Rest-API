package com.ou.restaurantmanagement.DTO.Request;

public class FeedbackRequestDTO implements IBaseRequest{
    private String content;
    private int userID;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
