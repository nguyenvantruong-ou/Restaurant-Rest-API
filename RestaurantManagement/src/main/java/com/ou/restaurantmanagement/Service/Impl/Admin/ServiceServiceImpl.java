package com.ou.restaurantmanagement.Service.Impl.Admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.ServiceRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Admin.ServiceRepository;
import com.ou.restaurantmanagement.Service.Admin.ServiceService;
import com.ou.restaurantmanagement.Utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository _serviceRepository;
    @Override
    public IBaseResponse getListService(String kw) {
        return new Common(Code.OK, _serviceRepository.getService(kw), "Lấy danh sách thành công");
    }

    @Override
    public IBaseResponse deleteService(int id) {
        boolean flag;
        try {
            if(!_serviceRepository.deleteService(id))
                flag = false;
            else
                flag = true;
        }
        catch (Exception e){
            flag = false;
        }
        if(flag)
            return new Common(Code.OK, null, "Xóa thành công");
        return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
    }

    @Override
    public IBaseResponse createService(IBaseRequest input) {
        ServiceRequestDTO req = (ServiceRequestDTO) input;
        String message = "";
        int code;
        if(req.getSerPrice() == null) {
            message = "Vui lòng nhập giá dịch vụ!";
            code = Code.NOT_FOUND;
        }
        else {
            try {
                if(req.getFile().getSize() > 0)
                    req.setSerImage(upImage(req.getFile()));
                if (!_serviceRepository.createService(input)) {
                    message = "Vui lòng kiểm tra lại";
                    code = Code.NOT_FOUND;
                }
                else {
                    message = "Tạo mới thành công";
                    code = Code.OK;
                }
            } catch (Exception e) {
                message = "Vui lòng kiểm tra lại";
                code = Code.NOT_FOUND;
            }
        }
        return new Common(code, null, message);
    }

    @Override
    public IBaseResponse update(IBaseRequest input) {
        ServiceRequestDTO s = (ServiceRequestDTO) input;
        if(s.getFile().getSize() > 0)
            s.setSerImage(upImage(s.getFile()));
        if(_serviceRepository.updateService(input))
            return new Common(Code.OK, null, "Cập nhật thành công");
        else
            return new Common(Code.NOT_FOUND, null, "Vui lòng kiểm tra lại!");
    }

    private String upImage(MultipartFile f){
        Cloudinary cloudinary = CloudinaryUtil.getCloudinaryClient();
        try {
            Map r = cloudinary.uploader().upload(f.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            return (String) r.get("secure_url");
        } catch (IOException e) {
            return null;
        }
    }


}
