package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Role;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.RegisterRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.CommentStatisticResponse;
import com.ou.restaurantmanagement.Pojos.TypeCustomer;
import com.ou.restaurantmanagement.Pojos.User;
import com.ou.restaurantmanagement.Pojos.UserToken;
import com.ou.restaurantmanagement.Repository.Client.UserClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class UserClientRepositoryImpl implements UserClientRepository {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public boolean getStatusAccount(String username) {
        boolean status = false;
        status = _em.createQuery("SELECT u.userIsActive FROM User u " +
                        "WHERE u.userUsername LIKE :username", Boolean.class)
                .setParameter("username", username)
                .getSingleResult();
        return status;
    }

    @Override
    public int register(IBaseRequest input) {
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
            user.setUserIsActive(false);
            user.setUserEmail(req.getUserEmail());
            user.setUserAddress(req.getUserAddress());
            user.setUserImage(req.getUserImage());
            user.setUserRole(Role.USER);

            TypeCustomer t = new TypeCustomer();
            t.setId(1);

            user.setTypeCustomer(t);

            _em.persist(user);
            return getLastestId();
        }
        catch (Exception e) {
            return 0;
        }
    }

    private int getLastestId(){
        int lasterId = _em.createQuery("SELECT MAX(u.id) FROM User u", Integer.class)
                .getSingleResult();
        return lasterId;
    }

    @Override
    public void setStatusUSer(String username) {
        User user = _em.createQuery("SELECT u FROM User u WHERE u.userUsername like :username"
                ,User.class)
                .setParameter("username", username)
                .getSingleResult();
        user.setUserIsActive(true);
        _em.merge(user);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = _em.createQuery("SELECT u FROM User u WHERE u.userUsername like :username"
                        ,User.class)
                .setParameter("username", username)
                .getSingleResult();
        return user;
    }

    @Override
    public void createUserToken(UserToken userToken) {
        _em.persist(userToken);
    }

    @Override
    public void updateUserToken(UserToken userToken) {
        _em.merge(userToken);
    }

    @Override
    public UserToken readUserToken(String username) {
        UserToken result = _em.createQuery("SELECT t FROM UserToken t, User u " +
                                "WHERE u.userUsername like :username AND u.id = t.id"
                        , UserToken.class)
                .setParameter("username", username)
                .getSingleResult();
        return result;
    }

    @Override
    public User getUserById(int id) {
        User result = _em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        return result;
    }

}
