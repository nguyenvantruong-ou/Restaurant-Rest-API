package com.ou.restaurantmanagement.Controller.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.CommentRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.PermissionComment;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Client.CommentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentClientController {
    @Autowired
    private CommentClientService _commentService;

    @GetMapping("/get-list-comment")
    public IBaseResponse getListComment(@RequestParam("lob_id") int lob_id){
        return _commentService.getListCommentByLobID(lob_id);
    }

    @PostMapping("/check-permission-comment")
    @PreAuthorize("hasAnyAuthority('USER')")
    public IBaseResponse checkPermission(@RequestBody PermissionComment input){
        return _commentService.checkPermission(input);
    }

    @PostMapping("/add-comment")
    @PreAuthorize("hasAnyAuthority('USER')")
    public IBaseResponse addComment(@RequestBody CommentRequestDTO input){
        return _commentService.addComment(input);
    }
}
