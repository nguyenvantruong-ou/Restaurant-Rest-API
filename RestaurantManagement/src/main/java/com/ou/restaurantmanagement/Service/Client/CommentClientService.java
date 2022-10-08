package com.ou.restaurantmanagement.Service.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;

public interface CommentClientService {
    IBaseResponse getListCommentByLobID(int lob_id);
    IBaseResponse checkPermission(IBaseRequest input);
    IBaseResponse addComment(IBaseRequest input);
}
