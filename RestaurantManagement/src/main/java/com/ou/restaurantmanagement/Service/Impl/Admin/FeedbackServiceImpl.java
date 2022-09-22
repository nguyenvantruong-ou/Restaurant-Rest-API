package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Admin.FeedbackRepository;
import com.ou.restaurantmanagement.Service.Admin.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository _feedbackRepository;

    @Override
    public IBaseResponse amountUnreadFeedback() {
        try {
            return new Common(Code.OK, _feedbackRepository.amountUnreadFeedback(), " Thành công");
        }
        catch (Exception e){
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse getListFeedback() {
        try{
            return new Common(Code.OK, _feedbackRepository.getListFeedback(), "Thành công");
        }
        catch (Exception e){
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse getFeedbackDetail(int user_id) {
        try{
            return new Common(Code.OK, _feedbackRepository.getFeedbackDetail(user_id), "Thành công");
        }
        catch (Exception e){
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
        }
    }
}
