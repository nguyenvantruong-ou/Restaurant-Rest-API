package com.ou.restaurantmanagement.Repository.Admin;

import com.ou.restaurantmanagement.DTO.Response.FeedbackDetail;
import com.ou.restaurantmanagement.DTO.Response.FeedbackGeneral;
import com.ou.restaurantmanagement.Pojos.Feedback;

import java.util.List;

public interface FeedbackRepository {
    int amountUnreadFeedback();
    List<FeedbackGeneral> getListFeedback();
    List<FeedbackDetail> getFeedbackDetail(int user_id);
}
