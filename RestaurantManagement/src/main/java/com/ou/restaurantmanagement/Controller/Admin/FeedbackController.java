package com.ou.restaurantmanagement.Controller.Admin;

import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FeedbackController {
    @Autowired
    private FeedbackService _feedbackService;

    @GetMapping("/get-amount-unread-feedback")
    public IBaseResponse getAmountUnreadFeedback(){
        return  _feedbackService.amountUnreadFeedback();
    }

    @GetMapping("/get-list-feedback-general")
    public IBaseResponse getListFeedback(){
        return _feedbackService.getListFeedback();
    }

    @GetMapping("/get-list-feedback-detail")
    public IBaseResponse getFeedbackDetail(@RequestParam(value = "user-id") int id){
        return _feedbackService.getFeedbackDetail(id);
    }
}
