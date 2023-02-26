package com.ou.restaurantmanagement.Repository.Impl.Client;

import com.ou.restaurantmanagement.DTO.Response.MenuResponse;
import com.ou.restaurantmanagement.Pojos.Dish;
import com.ou.restaurantmanagement.Pojos.Menu;
import com.ou.restaurantmanagement.Repository.Client.MenuClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuClientRepositoryImpl implements MenuClientRepository {
    @PersistenceContext
    private EntityManager _em;

    @Override
    public List<MenuResponse> getListMenu(String sort) {
        List<Menu> listMenu;
        if(sort.compareTo("default") == 0) {          // default
            listMenu = _em.createQuery("SELECT m FROM Menu m WHERE m.menuIsActive = true", Menu.class)
                    .getResultList();
        }
        else if (sort.compareTo("ascending") == 0) {  // ascending
                listMenu = _em.createQuery("SELECT m FROM Menu m WHERE m.menuIsActive = true " +
                        "ORDER BY m.menuPrice asc ", Menu.class).getResultList();
            }
            else {                     // decrease
                listMenu = _em.createQuery("SELECT m FROM Menu m WHERE m.menuIsActive = true " +
                        "ORDER BY m.menuPrice desc ", Menu.class).getResultList();
            }
        List<MenuResponse> results = new ArrayList<>();
        listMenu.forEach(s->{
            MenuResponse mr = new MenuResponse();
            mr.setId(s.getId());
            mr.setMenuName(s.getMenuName());
            mr.setMenuPrice(s.getMenuPrice());
            mr.setMenuImage(s.getMenuImage());
            mr.setListDish(getListDish(s.getId()));
            results.add(mr);
        });
        return results;

    }

    private List<Dish> getListDish(int menu_id){
        return _em.createQuery("SELECT d FROM Dish d, MenuDish md " +
                "WHERE d.id = md.dish.id AND md.menu.id = :menu_id AND d.dishIsActive = true", Dish.class)
                .setParameter("menu_id", menu_id)
                .getResultList();
    }
}
