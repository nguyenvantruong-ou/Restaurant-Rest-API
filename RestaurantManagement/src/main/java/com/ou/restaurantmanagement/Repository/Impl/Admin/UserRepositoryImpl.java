package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Constant.Role;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.UserRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.LobbyResponse;
import com.ou.restaurantmanagement.DTO.Response.UserResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;
import com.ou.restaurantmanagement.Pojos.User;
import com.ou.restaurantmanagement.Repository.Admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private EntityManager _em;

    @Override
    public UserResponse getList(IBaseRequest req) {
        UserRequestDTO input = (UserRequestDTO) req;
        TypedQuery<User> tp = _em.createQuery("SELECT a FROM User a  " +
                "WHERE (a.userFirstName LIKE :kw OR a.userLastName LIKE :kw)", User.class)
                .setParameter("kw", "%"+ input.getKw() + "%");
        List<User> listUser = tp.getResultList();

        UserResponse rep = new UserResponse();
        rep.setNumberPage(maxPage(listUser.size(), input.getSize()));

        tp.setFirstResult((input.getPage()-1)*input.getSize());
        tp.setMaxResults(input.getSize());

        listUser = tp.getResultList();
        rep.setListUser(listUser);
        return rep;
    }
    private int maxPage(int size, int sizePage){
        int max = size/sizePage;
        int d = size%sizePage;
        if (d > 0)
            max ++;
        return max;
    }


    @Override
    public User getUserByID(int id) {
        TypedQuery<User> tp = _em.createQuery("SELECT a FROM User a  " +
                "WHERE a.id = :id", User.class)
                .setParameter("id",id);
        return tp.getSingleResult();
    }

    @Override
    public boolean deleteUser(int userID){
        User user = getUserByID(userID);
        if(!user.getUserIsActive())
            return false;

        user.setUserIsActive(false);
        _em.merge(user);
        return true;
    }

    @Override
    public boolean isIdCart(String idCart, int id) {
        List<User> user  = _em.createQuery("SELECT a FROM User a " +
                        "WHERE a.userIdCard LIKE :idCart AND a.id NOT IN :userID", User.class)
                .setParameter("idCart", idCart)
                .setParameter("userID", id).getResultList();
        if(user.size() == 0)
            return true;
        return false;
    }

    @Override
    public boolean isUsername(String username, int id) {
        List<User> user  = _em.createQuery("SELECT a FROM User a " +
                        "WHERE a.userUsername LIKE :username AND a.id NOT IN :userID", User.class)
                .setParameter("username", username)
                .setParameter("userID", id).getResultList();
        if(user.size() == 0)
            return true;
        return false;
    }

    @Override
    public String checkRole(int role) {
        if(role == 1)
            return Role.ADMIN;
        if(role == 2)
            return Role.STAFF;
        return Role.USER;
    }

    @Override
//    @Transactional
    public boolean updateUser(UserRequestDTO user) {
        try {
            User u = getUserByID(user.getId());
            u.setUserIdCard(user.getUserIdCard());
            u.setUserPhoneNumber(user.getUserPhoneNumber());
            u.setUserSex(user.getUserSex());
            u.setUserLastName(user.getUserLastName());
            u.setUserFirstName(user.getUserFirstName());
            u.setUserDateOfBirth(u.getUserDateOfBirth());
            u.setUserUsename(user.getUserUsename());
            u.setUserPassword(user.getUserPassword());
            u.setUserEmail(user.getUserEmail());
            u.setUserAddress(user.getUserAddress());
            u.setUserRole(checkRole(user.getUserRole()));
            if(user.getUserImage() != null)
                u.setUserImage(user.getUserImage());
            _em.merge(u);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
