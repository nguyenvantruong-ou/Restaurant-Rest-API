package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Client.FeedbackClientRepository;
import com.ou.restaurantmanagement.Service.Client.FeedbackClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackClientServiceImpl implements FeedbackClientService {
    @Autowired
    private FeedbackClientRepository _feedbackRepository;

    @Override
    public IBaseResponse createFeedback(IBaseRequest input) {
        if(_feedbackRepository.createFeedback(input))
            return new Common(Code.OK, null, "Thêm phản hồi thành công");
        return new Common(Code.INVALID_REQUEST, null, "Vui lòng kiểm tra lại!");
    }
}
