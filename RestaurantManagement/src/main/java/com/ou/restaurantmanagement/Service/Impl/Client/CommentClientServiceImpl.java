package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.CommentRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.PermissionComment;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;
import com.ou.restaurantmanagement.Pojos.User;
import com.ou.restaurantmanagement.Repository.Client.CommentClientRepository;
import com.ou.restaurantmanagement.Service.Client.CommentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentClientServiceImpl implements CommentClientService {
    @Autowired
    private CommentClientRepository _commentRepository;

    @Override
    public IBaseResponse getListCommentByLobID(int lob_id) {
        if(lob_id > 0)
            return new Common(Code.OK, _commentRepository.getListCommentByLobID(lob_id),
                "Lấy danh sách bình luận thành công");
        return new Common(Code.INVALID_REQUEST, null, "Vui lòng kiểm tra lại!");
    }

    @Override
    public IBaseResponse checkPermission(IBaseRequest input) {
        PermissionComment req = (PermissionComment) input;
        if(req.getUserID() > 0 && req.getLobID() > 0){
            try{
                return new Common(Code.OK, _commentRepository.checkPermission(input),
                        "Kiểm tra quyền thành công");
            }catch (Exception e){
                System.out.println("ERROR: " + e);
                return new Common(Code.INVALID_REQUEST, null, "Vui lòng kiểm tra lại!");
            }
        }
        return new Common(Code.INVALID_REQUEST, null, "Vui lòng kiểm tra lại!");
    }

    @Override
    public IBaseResponse addComment(IBaseRequest input) {
        CommentRequestDTO req = (CommentRequestDTO) input;
        // create user
        User user = new User();
        user.setId(req.getUserID());
        req.setUser(user);
        //create lobby
        Lobby lobby = new Lobby();
        lobby.setId(req.getLobID());
        req.setLobby(lobby);
        if(_commentRepository.addComment(input))
            return new Common(Code.OK, null, "Thêm bình luận thành công");
        return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
    }
}
