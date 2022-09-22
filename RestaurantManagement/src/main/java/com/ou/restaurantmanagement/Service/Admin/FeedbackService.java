package com.ou.restaurantmanagement.Service.Admin;

import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface FeedbackService {
    IBaseResponse amountUnreadFeedback();
    IBaseResponse getListFeedback();
    IBaseResponse getFeedbackDetail(int user_id);
}
