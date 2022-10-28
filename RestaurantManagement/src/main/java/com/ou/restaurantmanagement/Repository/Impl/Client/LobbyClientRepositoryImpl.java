package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.LobbyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.LobbyComboboxResponse;
import com.ou.restaurantmanagement.DTO.Response.LobbyDetailsResponse;
import com.ou.restaurantmanagement.DTO.Response.LobbyResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;
import com.ou.restaurantmanagement.Pojos.LobbyImage;
import com.ou.restaurantmanagement.Repository.Client.LobbyClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Override
    public LobbyDetailsResponse getLobbyByID(int lob_id) {
        LobbyDetailsResponse result = new LobbyDetailsResponse();
        Lobby lobby = _em.createQuery("SELECT l FROM Lobby l WHERE l.id = :id", Lobby.class)
                .setParameter("id", lob_id)
                .getSingleResult();
        List<String> listImage = _em.createQuery("SELECT i.image FROM LobbyImage i " +
                "WHERE i.lob.id = :id", String.class)
                .setParameter("id", lob_id)
                .getResultList();

        result.setId(lobby.getId());
        result.setLobName(lobby.getLobName());
        result.setLobAddress(lobby.getLobAddress());
        result.setLobPrice(lobby.getLobPrice());
        result.setLobTotalTable(lobby.getLobTotalTable());
        result.setLobImage(lobby.getLobImage());
        result.setLobDescription(lobby.getLobDescription());
        result.setListImage(listImage);
        return result;
    }

    @Override
    public List<LobbyComboboxResponse> getLobbyCombobox() {
        List<LobbyComboboxResponse> results = new ArrayList<>();
        List<Object[]> listLobby = _em.createQuery("SELECT l.id, l.lobName, l.lobImage, l.lobPrice " +
                        "FROM Lobby l WHERE l.lobIsActive = true", Object[].class)
                .getResultList();
        listLobby.forEach(s->{
            LobbyComboboxResponse r = new LobbyComboboxResponse();
            r.setLobId((int) s[0]);
            r.setLobName((String) s[1]);
            r.setLobImage((String) s[2]);
            r.setLobPrice((BigDecimal) s[3]);

            results.add(r);
        });
        return results;
    }

}
