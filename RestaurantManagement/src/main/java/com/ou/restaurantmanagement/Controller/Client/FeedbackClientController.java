package com.ou.restaurantmanagement.Controller.Client;

import com.ou.restaurantmanagement.DTO.Request.FeedbackRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Client.FeedbackClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class FeedbackClientController {
    @Autowired
    private FeedbackClientService _feedbackService;

    @PostMapping("/create-new-feedback")
    @PreAuthorize("hasAnyAuthority('USER')")
    @CrossOrigin
    public IBaseResponse createFeedback(@RequestBody FeedbackRequestDTO input){
        return _feedbackService.createFeedback(input);
    }
}
