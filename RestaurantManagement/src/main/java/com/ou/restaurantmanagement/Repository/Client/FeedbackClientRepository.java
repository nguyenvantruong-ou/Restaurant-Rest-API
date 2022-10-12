package com.ou.restaurantmanagement.Repository.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;

public interface FeedbackClientRepository {
    boolean createFeedback(IBaseRequest input);
}
