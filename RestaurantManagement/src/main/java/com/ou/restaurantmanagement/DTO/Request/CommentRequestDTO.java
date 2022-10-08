package com.ou.restaurantmanagement.DTO.Request;

import com.ou.restaurantmanagement.Pojos.Lobby;
import com.ou.restaurantmanagement.Pojos.User;

public class CommentRequestDTO implements IBaseRequest{
    private String content;
    private int star;
    private int userID;
    private int lobID;
    private boolean isIncognito;
    private User user;
    private Lobby lobby;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getLobID() {
        return lobID;
    }

    public void setLobID(int lobID) {
        this.lobID = lobID;
    }

    public boolean isIncognito() {
        return isIncognito;
    }

    public void setIsIncognito(boolean incognito) {
        isIncognito = incognito;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }
}
