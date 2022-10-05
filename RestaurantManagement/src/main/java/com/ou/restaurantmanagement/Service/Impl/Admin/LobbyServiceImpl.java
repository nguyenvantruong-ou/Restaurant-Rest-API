package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.LobbyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;
import com.ou.restaurantmanagement.Repository.Admin.LobbyImageRepository;
import com.ou.restaurantmanagement.Repository.Admin.LobbyRepository;
import com.ou.restaurantmanagement.Service.Admin.LobbyService;
import com.ou.restaurantmanagement.Utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LobbyServiceImpl implements LobbyService {
    @Autowired
    private LobbyRepository _lobbyRep;

    @Autowired
    private LobbyImageRepository _image;

    @Override
    public IBaseResponse ReadLobby(IBaseRequest input) {
        LobbyRequestDTO req = (LobbyRequestDTO) input;
        if(req.getSize() > 0 && req.getPage() > 0)
            try {
                return new Common(Code.OK, _lobbyRep.getListLobby(input), "Tìm kiếm thành công");
            }
        catch (Exception e){
                return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
        }
        return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
    }

    @Override
    public IBaseResponse createLobby(IBaseRequest input) {
        LobbyRequestDTO req = (LobbyRequestDTO) input;
        List<String> listImage = new ArrayList<>() ;
        try{
            req.setKey_Image(CloudinaryUtil.upImage(req.getLobImage()));
            req.getListImage().forEach(s->
            {
                listImage.add(CloudinaryUtil.upImage(s));
            });
            if(_lobbyRep.createLobby(req)){
                _image.saveImage(listImage, _lobbyRep.getLobbyNew());}
            return new Common(Code.OK, null, "Thêm mới thành công");
        }
        catch (Exception e){
            return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse deleteLobby(int id) {
        if(id > 0){
            boolean flag = _lobbyRep.deleteLobby(id);
            if(flag == true)
                return new Common(Code.OK, null, "Xóa sảnh thành công");
            else
                return new Common(Code.NOT_FOUND, null, "Xóa sảnh thất bại!");
        }
        return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
    }

    @Override
    public IBaseResponse updateLobby(IBaseRequest input) {
        boolean flag = false;
        try{  // upload image to cloudinary
            LobbyRequestDTO req = (LobbyRequestDTO) input;
            List<String> listImage = new ArrayList<>();
            if(req.getLobImage().getSize() > 0)
                req.setKey_Image(CloudinaryUtil.upImage(req.getLobImage()));
            if(req.getListImage().get(0).getSize() > 0) {
                req.getListImage().forEach(s ->
                {
                    listImage.add(CloudinaryUtil.upImage(s));
                });
            }
            // update lobby
            if(_lobbyRep.updateLobby(input)) {
                if (req.getListImage().get(0).getSize() > 0) {
                    Lobby lobby = new Lobby();
                    lobby.setId(req.getId());
                    if (_image.updateImage(listImage, lobby))
                        flag =true;
                    else
                        flag = false;

                }
                else flag =true;
            }else flag = false;

        }
        catch (Exception e){
            flag = false;
        }
        if (flag)
            return new Common(Code.OK, null, "Đã sửa thành công");
        else
            return new Common(Code.INVALID, null, "Sửa thất bại! Vui lòng kiểm tra lại!");
    }

}
