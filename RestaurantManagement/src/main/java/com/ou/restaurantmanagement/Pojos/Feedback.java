package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id", nullable = false)
    private Integer id;

    @Column(name = "feed_content")
    private String feedContent;

    @Column(name = "feed_created_date")
    private LocalDate feedCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "feed_is_read")
    private Boolean feedIsRead;

    public Boolean getFeedIsRead() {
        return feedIsRead;
    }

    public void setFeedIsRead(Boolean feedIsRead) {
        this.feedIsRead = feedIsRead;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedContent() {
        return feedContent;
    }

    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent;
    }

    public LocalDate getFeedCreatedDate() {
        return feedCreatedDate;
    }

    public void setFeedCreatedDate(LocalDate feedCreatedDate) {
        this.feedCreatedDate = feedCreatedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}