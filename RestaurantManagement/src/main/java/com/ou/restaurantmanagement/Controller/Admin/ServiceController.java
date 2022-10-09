package com.ou.restaurantmanagement.Controller.Admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.restaurantmanagement.DTO.Request.ServiceRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.UserRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Service.Admin.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ServiceController {
    @Autowired
    private ServiceService _service;

    @GetMapping("/get-list-service")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public IBaseResponse getListService(@RequestParam(value = "kw") String kw){
        return _service.getListService(kw);
    }


    @PostMapping("/delete-service")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public IBaseResponse deleteService(@RequestParam(value = "id") int id){
        return _service.deleteService(id);
    }

    @PostMapping("/create-service")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public IBaseResponse createService(@RequestParam MultipartFile file, String service){
        ServiceRequestDTO req ;
        try {
            req = new ObjectMapper().readValue(service, ServiceRequestDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        req.setFile(file);
        return _service.createService(req);
    }

    @PostMapping("/update-service")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CrossOrigin
    public IBaseResponse updateService(@RequestParam MultipartFile file, String service){
        ServiceRequestDTO req ;
        try {
            req = new ObjectMapper().readValue(service, ServiceRequestDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        req.setFile(file);
        return _service.update(req);
    }
}
