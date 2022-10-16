package com.ou.restaurantmanagement.DTO.Response;

import java.util.Date;

public class FeedbackDetail {
    private int id;
    private Date feedCreatedDate;
    private String content;

    public Date getFeedCreatedDate() {
        return feedCreatedDate;
    }

    public void setFeedCreatedDate(Date feedCreatedDate) {
        this.feedCreatedDate = feedCreatedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
