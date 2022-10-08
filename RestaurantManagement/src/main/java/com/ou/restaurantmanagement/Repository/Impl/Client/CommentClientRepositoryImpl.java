package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Request.CommentRequestDTO;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.PermissionComment;
import com.ou.restaurantmanagement.DTO.Response.CommentResponse;
import com.ou.restaurantmanagement.DTO.Response.CommentStatisticResponse;
import com.ou.restaurantmanagement.Pojos.Comment;
import com.ou.restaurantmanagement.Repository.Client.CommentClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class CommentClientRepositoryImpl implements CommentClientRepository {
    @Autowired
    private EntityManager _em;

    @Override
    public CommentStatisticResponse getListCommentByLobID(int lob_id) {
        List<Comment> listComment = _em.createQuery("SELECT c FROM Comment c WHERE c.lobby.id = :id " +
                        "ORDER BY c.id desc ", Comment.class)
                .setParameter("id", lob_id)
                .getResultList();
        CommentStatisticResponse result = statisticRating(lob_id);
        result.setListComment(convertComment(listComment));
        return result;
    }

    private CommentStatisticResponse statisticRating(int lob_id){
        // avg star
        CommentStatisticResponse result = new CommentStatisticResponse();
        double avg;
        try {
            avg = _em.createQuery("SELECT AVG(c.cmtStar) FROM Comment c WHERE c.lobby.id = :id", Double.class)
                    .setParameter("id", lob_id)
                    .getSingleResult();
        }catch (Exception e){
            avg = 0;
        }
        result.setAvgStar((double) Math.round(avg * 10) / 10);

        // amount star
        int totalComment = 0;
        int[] array = new int[6];
        for(int i= 1; i <= 5; i++){
            array[i] = (int)(long)_em.createQuery("SELECT COUNT (c.id) FROM Comment c WHERE c.lobby.id = :id " +
                            "AND c.cmtStar = :star", Long.class)
                    .setParameter("id", lob_id)
                    .setParameter("star", i)
                    .getSingleResult();
            totalComment += array[i];
        }
        result.setAmount1Star(array[1]);
        result.setAmount2Star(array[2]);
        result.setAmount3Star(array[3]);
        result.setAmount4Star(array[4]);
        result.setAmount5Star(array[5]);
        result.setAmountTotalComment(totalComment);
        return result;
    }
    private List<CommentResponse> convertComment(List<Comment> list){
        List<CommentResponse> results = new ArrayList<>();
        list.forEach(s->{
            CommentResponse comment = new CommentResponse();
            comment.setId(s.getId());
            comment.setCmtContent(s.getCmtContent());
            comment.setCmtStar(s.getCmtStar());
            comment.setCreatedDay(s.getCreatedDay());
            comment.setIncognito(s.isIncognito());
            if(s.isIncognito()){
                comment.setName(incognito(s.getUser().getUserLastName(), s.getUser().getUserFirstName()));
            }
            else{
                comment.setName(s.getUser().getUserLastName() + " " + s.getUser().getUserFirstName());
                comment.setUserImage(s.getUser().getUserImage());
            }
            results.add(comment);
        });
        return results;
    }

    private String incognito(String lastname, String firstname){
        if(lastname.length() == 1)
            lastname = "*";
        else {
            for (int i = 1; i <= lastname.length() - 1; i++)
                if (lastname.charAt(i) != ' ' && lastname.charAt(i - 1) != ' ')
                    lastname = lastname.replace(lastname.charAt(i), '*');
        }
        if(firstname.length() == 1)
            firstname = "*";
        else {
            for (int i = 1; i <= firstname.length() - 1; i++)
                if (firstname.charAt(i) != ' ' && firstname.charAt(i - 1) != ' ')
                    firstname = firstname.replace(firstname.charAt(i), '*');
        }
        return lastname + " " + firstname;
    }

    @Override
    public boolean checkPermission(IBaseRequest input) {
        PermissionComment req = (PermissionComment) input;
        int amountBill =(int)(long) _em.createQuery("SELECT COUNT(o.id) FROM Order o " +
                "WHERE o.lob.id = :lob_id AND o.user.id = :user_id AND o.ordIsPayment = true", Long.class)
                .setParameter("user_id", req.getUserID())
                .setParameter("lob_id", req.getLobID())
                .getSingleResult();
        int amountComment = (int)(long) _em.createQuery("SELECT COUNT(c.id) FROM Comment c " +
                "WHERE c.user.id = :user_id AND c.lobby.id = :lob_id", Long.class)
                .setParameter("user_id", req.getUserID())
                .setParameter("lob_id", req.getLobID())
                .getSingleResult();

        if(amountBill > amountComment)
            return true;
        return false;
    }
    @Override
    public boolean addComment(IBaseRequest input) {
        try {
            CommentRequestDTO req = (CommentRequestDTO) input;
            Comment comment = new Comment();
            comment.setCmtContent(req.getContent());
            comment.setCmtStar(req.getStar());
            comment.setUser(req.getUser());
            comment.setLobby(req.getLobby());
            comment.setCreatedDay(new Date());
            comment.setIncognito(req.isIncognito());
            _em.persist(comment);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
