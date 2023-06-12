package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Response.FeedbackDetail;
import com.ou.restaurantmanagement.DTO.Response.FeedbackGeneral;
import com.ou.restaurantmanagement.Pojos.Feedback;
import com.ou.restaurantmanagement.Pojos.User;
import com.ou.restaurantmanagement.Repository.Admin.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Repository
@Transactional
public class FeedbackRepositoryImpl implements FeedbackRepository {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public int amountUnreadFeedback() {
        int total =(int)(long) _em.createQuery("SELECT count(f.id) FROM Feedback f  " +
                "WHERE f.feedIsRead = false ", Long.class).getSingleResult();
        return total;
    }

    @Override
    public List<FeedbackGeneral> getListFeedback() {
//        List<Object[]> r = _em.createQuery("SELECT f.user.userUsername, f.user.userLastName, f.user.userFirstName," +
//                        "f.user.id, f.user.userImage FROM Feedback f " +
//                        " ORDER BY f.id DESC", Object[].class)
//                .getResultList();
        List<Object[]> r = _em.createQuery("SELECT f.user.userUsername, f.user.userLastName, f.user.userFirstName," +
                        "f.user.id, f.user.userImage " +
                        "FROM Feedback f " +
                        "WHERE f.id IN (" +
                        "   SELECT MAX(f2.id) " +
                        "   FROM Feedback f2 " +
                        "   GROUP BY f2.user.id" +
                        ")", Object[].class)
                .getResultList();
        List<FeedbackGeneral> results= new ArrayList<>();
        r.forEach(s->{
            FeedbackGeneral f = new FeedbackGeneral();
            f.setUsername((String) s[0]);
            f.setName((String)(s[1] + " " + s[2]));
            f.setUser_id((int) s[3]);
            f.setTotalUnreadFeedback(totalUnreadByUserID((int) s[3]));
            f.setLastFeedback(getLastFeedback((int) s[3]));
            f.setAvatar((String) s[4]);
            results.add(f);
        });
        return results;
    }

    private String getLastFeedback(int user_id){
        String r = _em.createQuery("SELECT f.feedContent FROM Feedback f  WHERE f.user.id = :id " +
                "ORDER BY f.id DESC ", String.class)
                .setParameter("id", user_id)
                .getResultList().get(0);

        return r;
    }

    private int totalUnreadByUserID(int user_id){
        int r =(int)(long) _em.createQuery("SELECT count(f.id) FROM Feedback f  WHERE f.user.id = :id " +
                        "AND f.feedIsRead = false ", Long.class)

                .setParameter("id", user_id)
                .getResultList().get(0);

        return r;
    }

    @Override
    public List<FeedbackDetail> getFeedbackDetail(int user_id) {
        List<Object[]> r = _em.createQuery("SELECT f.id, f.feedContent, f.feedCreatedDate FROM Feedback f WHERE f.user.id = :id", Object[].class)
                .setParameter("id", user_id)
                .getResultList();
        List<FeedbackDetail> results = new ArrayList<>();
        r.forEach(s->{
            FeedbackDetail f = new FeedbackDetail();
            f.setId((int) s[0]);
            f.setContent((String) s[1]);
            f.setFeedCreatedDate((Date) s[2]);
            results.add(f);
        });
        updateRead(results);
        return results;
    }

    // update feed_id_read
    private void updateRead(List<FeedbackDetail> list){
        list.forEach(s->{
            Feedback f =getFeedbackByID(s.getId());
            if(!f.getFeedIsRead()) {
                f.setFeedIsRead(true);
                _em.merge(f);
            }
        });
    }

    private Feedback getFeedbackByID(int id){
        Feedback f = _em.createQuery("select f from Feedback f where f.id = :id ", Feedback.class)
                .setParameter("id", id)
                .getSingleResult();
        return f;
    }
}
