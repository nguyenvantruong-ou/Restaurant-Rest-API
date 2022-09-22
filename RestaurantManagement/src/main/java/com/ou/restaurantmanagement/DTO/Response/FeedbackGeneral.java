package com.ou.restaurantmanagement.DTO.Response;

public class FeedbackGeneral {
    private String username;
    private String name;
    private int user_id;
    private String lastFeedback;
    private int totalUnreadFeedback;

    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLastFeedback() {
        return lastFeedback;
    }

    public void setLastFeedback(String lastFeedback) {
        this.lastFeedback = lastFeedback;
    }

    public int getTotalUnreadFeedback() {
        return totalUnreadFeedback;
    }

    public void setTotalUnreadFeedback(int totalUnreadFeedback) {
        this.totalUnreadFeedback = totalUnreadFeedback;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
