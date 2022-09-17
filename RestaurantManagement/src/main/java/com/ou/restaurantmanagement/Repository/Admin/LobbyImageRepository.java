package com.ou.restaurantmanagement.Repository.Admin;

import com.ou.restaurantmanagement.Pojos.Lobby;

import java.util.List;

public interface LobbyImageRepository {
    boolean saveImage(List<String> listImage, Lobby lobby);
    boolean updateImage(List<String> listImage, Lobby lobby);
}
