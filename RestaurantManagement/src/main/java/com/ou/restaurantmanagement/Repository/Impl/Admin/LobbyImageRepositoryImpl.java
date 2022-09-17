package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.Pojos.Lobby;
import com.ou.restaurantmanagement.Pojos.LobbyImage;
import com.ou.restaurantmanagement.Repository.Admin.LobbyImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LobbyImageRepositoryImpl implements LobbyImageRepository {
    @Autowired
    private EntityManager _em;

    @Override
    public boolean saveImage(List<String> listImage, Lobby lobby) {
        try {
            listImage.forEach(s -> {
                LobbyImage image = new LobbyImage();
                image.setImage(s);
                image.setLob(lobby);
                _em.persist(image);
            });
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateImage(List<String> listImage,Lobby lobby) {
        try {
            TypedQuery<LobbyImage> tp = _em.createQuery("SELECT a FROM LobbyImage a  " +
                            "WHERE a.lob.id = :id", LobbyImage.class)
                    .setParameter("id", lobby.getId());
            List<LobbyImage> list = tp.getResultList();
            list.forEach(s->_em.remove(s));
            listImage.forEach(s->{
                LobbyImage im = new LobbyImage();
                im.setImage(s);
                im.setLob(lobby);
                _em.persist(im);
            });
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
