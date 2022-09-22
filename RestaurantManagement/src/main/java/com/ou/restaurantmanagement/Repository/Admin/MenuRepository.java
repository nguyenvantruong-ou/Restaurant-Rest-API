package com.ou.restaurantmanagement.Repository.Admin;

import com.ou.restaurantmanagement.Pojos.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
