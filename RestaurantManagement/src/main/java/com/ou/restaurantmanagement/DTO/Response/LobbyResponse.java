package com.ou.restaurantmanagement.DTO.Response;

import com.ou.restaurantmanagement.Pojos.Lobby;

import java.util.List;

public class LobbyResponse {
    private List<LobbyCustomResponse> listLobby;
    private int numberPage;

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public List<LobbyCustomResponse> getListLobby() {
        return listLobby;
    }

    public void setListLobby(List<LobbyCustomResponse> listLobby) {
        this.listLobby = listLobby;
    }
}
