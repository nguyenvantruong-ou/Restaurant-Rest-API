package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.LobbyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.LobbyResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;
import com.ou.restaurantmanagement.Repository.Client.LobbyClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LobbyClientRepositoryImpl implements LobbyClientRepository {
    @Autowired
    private EntityManager _em;

    @Override
    public LobbyResponse getListLobby(IBaseRequest input) {
        LobbyRequestDTO req = (LobbyRequestDTO) input;
        TypedQuery<Lobby> tp;
        if(req.getKw().trim() == null || req.getKw().trim() == "")
            tp = _em.createQuery("SELECT a FROM Lobby a WHERE a.lobIsActive = true", Lobby.class);
        else
            tp = _em.createQuery("SELECT a FROM Lobby a  WHERE a.lobName LIKE :kw " +
                            "AND a.lobIsActive = true", Lobby.class)
                    .setParameter("kw", "%"+req.getKw()+"%");
        List<Lobby> listLobby = tp.getResultList();

        LobbyResponse rep = new LobbyResponse();
        rep.setNumberPage(maxPage(listLobby.size(), req.getSize()));

        tp.setFirstResult((req.getPage()-1)*req.getSize());
        tp.setMaxResults(req.getSize());

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
