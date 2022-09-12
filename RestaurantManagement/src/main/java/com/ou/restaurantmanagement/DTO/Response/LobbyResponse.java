package com.ou.restaurantmanagement.DTO.Response;

import com.ou.restaurantmanagement.Pojos.Lobby;

import java.util.List;

public class LobbyResponse {
    private List<Lobby> listLobby;
    private int numberPage;

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public List<Lobby> getListLobby() {
        return listLobby;
    }

    public void setListLobby(List<Lobby> listLobby) {
        this.listLobby = listLobby;
    }
}
