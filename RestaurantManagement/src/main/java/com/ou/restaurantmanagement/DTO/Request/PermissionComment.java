package com.ou.restaurantmanagement.DTO.Request;

public class PermissionComment implements IBaseRequest{
    private int userID;
    private int lobID;

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
}
