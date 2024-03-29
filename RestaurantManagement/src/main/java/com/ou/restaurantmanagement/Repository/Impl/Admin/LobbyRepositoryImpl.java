package com.ou.restaurantmanagement.Repository.Impl.Admin;

import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.LobbyRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.LobbyCustomResponse;
import com.ou.restaurantmanagement.DTO.Response.LobbyResponse;
import com.ou.restaurantmanagement.Pojos.Lobby;
import com.ou.restaurantmanagement.Repository.Admin.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class LobbyRepositoryImpl implements LobbyRepository {
    @PersistenceContext
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

        // update slug
        List<LobbyCustomResponse> rs = new ArrayList<>();
        listLobby.forEach( (Lobby lob) -> {
            LobbyCustomResponse r = new LobbyCustomResponse(lob, createSlug(removeDiacritics(lob.getLobName())));
            rs.add(r);
        });

        rep.setListLobby(rs);

        return rep;
    }

    private static String removeDiacritics(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        return nfdNormalizedString.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    private String createSlug(String inputString) {
        String slug = inputString.trim().toLowerCase()
                .replaceAll("\\s+", "-") // replace whitespace with dash
                .replaceAll("[^\\p{ASCII}]", "") // remove non-ASCII characters
                .replaceAll("[^a-zA-Z0-9-ả]", "") // remove any remaining non-alphanumeric characters
                .replaceAll("-{2,}", "-"); // replace multiple dashes with a single dash
        return slug;
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
