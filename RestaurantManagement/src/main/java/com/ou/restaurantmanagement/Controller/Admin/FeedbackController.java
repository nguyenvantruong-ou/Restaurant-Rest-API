package com.ou.restaurantmanagement.Controller.Admin;

import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class FeedbackController {
    @Autowired
    private FeedbackService _feedbackService;

    @GetMapping("/get-amount-unread-feedback")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public IBaseResponse getAmountUnreadFeedback(){
        return  _feedbackService.amountUnreadFeedback();
    }

    @GetMapping("/get-list-feedback-general")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public IBaseResponse getListFeedback(){
        return _feedbackService.getListFeedback();
    }

    @GetMapping("/get-list-feedback-detail")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public IBaseResponse getFeedbackDetail(@RequestParam(value = "user-id") int id){
        return _feedbackService.getFeedbackDetail(id);
    }
}
