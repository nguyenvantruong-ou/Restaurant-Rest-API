package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Role;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.RegisterRequestDTO;
import com.ou.restaurantmanagement.Pojos.User;
import com.ou.restaurantmanagement.Repository.Client.UserClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

@Repository
@Transactional
public class UserClientRepositoryImpl implements UserClientRepository {
    @Autowired
    private EntityManager _em;

    @Override
    public boolean register(IBaseRequest input) {
        try {
            RegisterRequestDTO req = (RegisterRequestDTO) input;
            User user = new User();
            user.setUserIdCard(req.getUserIdCard());
            user.setUserPhoneNumber(req.getUserPhoneNumber());
            user.setUserSex(req.getUserSex());
            user.setUserLastName(req.getUserLastName());
            user.setUserFirstName(req.getUserFirstName());
            user.setUserDateOfBirth(req.getUserDateOfBirth());
            user.setUserJoinedDate(new Date());
            user.setUserUsename(req.getUserUsename());
            user.setUserPassword(req.getUserPassword());
            user.setUserIsActive(true);
            user.setUserEmail(req.getUserEmail());
            user.setUserAddress(req.getUserAddress());
            user.setUserImage(req.getUserImage());
            user.setUserRole(Role.USER);
            _em.persist(user);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
