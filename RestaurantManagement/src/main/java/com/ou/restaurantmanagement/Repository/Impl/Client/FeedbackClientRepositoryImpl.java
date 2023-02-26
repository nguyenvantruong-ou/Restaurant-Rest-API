package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Request.FeedbackRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.Pojos.Feedback;
import com.ou.restaurantmanagement.Pojos.User;
import com.ou.restaurantmanagement.Repository.Client.FeedbackClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Repository
@Transactional
public class FeedbackClientRepositoryImpl implements FeedbackClientRepository {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public boolean createFeedback(IBaseRequest input) {
        try {
            FeedbackRequestDTO req = (FeedbackRequestDTO) input;
            Feedback newF = new Feedback();
            newF.setFeedContent(req.getContent());
            newF.setFeedCreatedDate(new Date());
            User user = new User();
            user.setId(req.getUserID());
            newF.setUser(user);
            newF.setFeedIsRead(false);
            _em.persist(newF);
            return true;
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            return false;
        }
    }
}
