package com.ou.restaurantmanagement.DTO.Response;

import java.time.LocalDate;

public class FeedbackDetail {
    private int id;
    private LocalDate feedCreatedDate;
    private String content;

    public LocalDate getFeedCreatedDate() {
        return feedCreatedDate;
    }

    public void setFeedCreatedDate(LocalDate feedCreatedDate) {
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
