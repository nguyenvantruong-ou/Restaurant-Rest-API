package com.ou.restaurantmanagement.Repository.Client;



import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Response.CommentStatisticResponse;


public interface CommentClientRepository {
    CommentStatisticResponse getListCommentByLobID(int lob_id);
    boolean checkPermission(IBaseRequest input);
    boolean addComment(IBaseRequest input);
}
