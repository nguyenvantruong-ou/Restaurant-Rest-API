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
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
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

    @Override
    public boolean createLobby(IBaseRequest input) {
        try{
            LobbyRequestDTO req = (LobbyRequestDTO) input;
            Lobby newL = new Lobby();
            newL.setLobName(req.getLobName());
            newL.setLobImage(req.getKey_Image());
            newL.setLobAddress(req.getLobAddress());
            newL.setLobPrice(req.getLobPrice());
            newL.setLobTotalTable(req.getLobTotalTable());
            newL.setLobIsActive(true);
            newL.setLobDescription(req.getLobDescription());
            _em.persist(newL);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Lobby getLobbyNew() {
        TypedQuery<Lobby> tp = _em.createQuery("SELECT a FROM Lobby a order by a.id DESC", Lobby.class);
        tp.setMaxResults(1);
        return tp.getSingleResult();
    }

    @Override
    public boolean deleteLobby(int id) {
        try {
            TypedQuery<Lobby> tp = _em.createQuery("SELECT a FROM Lobby a  " +
                            "WHERE a.id = :id", Lobby.class)
                    .setParameter("id", id);
            Lobby lobby = tp.getSingleResult();
            lobby.setLobIsActive(false);
            _em.merge(lobby);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateLobby(IBaseRequest input) {
        try{
            LobbyRequestDTO req = (LobbyRequestDTO) input;
            TypedQuery<Lobby> tp = _em.createQuery("SELECT a FROM Lobby a  " +
                            "WHERE a.id = :id", Lobby.class)
                    .setParameter("id", req.getId());
            Lobby lobby = tp.getSingleResult();
            lobby.setLobName(req.getLobName());
            if(req.getKey_Image() != null && req.getKey_Image() != "")
                lobby.setLobImage(req.getKey_Image());
            lobby.setLobAddress(req.getLobAddress());
            lobby.setLobPrice(req.getLobPrice());
            lobby.setLobTotalTable(req.getLobTotalTable());
            lobby.setLobDescription(req.getLobDescription());
            lobby.setLobIsActive(req.isLobIsActive());
            _em.merge(lobby);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
