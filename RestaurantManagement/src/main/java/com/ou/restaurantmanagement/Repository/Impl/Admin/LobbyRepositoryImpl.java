package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.LobbyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.LobbyResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;
import com.ou.restaurantmanagement.Repository.Admin.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LobbyRepositoryImpl implements LobbyRepository {
    @Autowired
    private EntityManager _em;

    @Override
    public LobbyResponse getListLobby(IBaseRequest req) {
        LobbyRequestDTO input = (LobbyRequestDTO) req;
        TypedQuery<Lobby> tp;
        if(input.getKw().trim() == null || input.getKw().trim() == "")
            tp = _em.createQuery("SELECT a FROM Lobby a", Lobby.class);
        else
            tp = _em.createQuery("SELECT a FROM Lobby a  WHERE a.lobName LIKE :kw", Lobby.class)
                    .setParameter("kw", "%"+input.getKw()+"%");
        List<Lobby> listLobby = tp.getResultList();

        LobbyResponse rep = new LobbyResponse();
        rep.setNumberPage(maxPage(listLobby.size(), input.getSize()));

        tp.setFirstResult((input.getPage()-1)*input.getSize());
        tp.setMaxResults(input.getSize());

        listLobby = tp.getResultList();
        rep.setListLobby(listLobby);

        return rep;
    }

    private int maxPage(int size, int sizePage){
        int max = size/sizePage;
        int d = size%sizePage;
        if (d > 0)
            max ++;
        return max;
    }
}
