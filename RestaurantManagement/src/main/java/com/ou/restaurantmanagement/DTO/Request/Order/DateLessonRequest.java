package com.ou.restaurantmanagement.DTO.Request.Order;

import java.util.Date;

public class DateLessonRequest {
    private Date bookingDate;
    private String lesson;

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
