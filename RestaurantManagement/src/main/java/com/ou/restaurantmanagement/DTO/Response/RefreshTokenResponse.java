package com.ou.restaurantmanagement.DTO.Response;

public class RefreshTokenResponse {
    private String Token;
    private String RefreshToken;

    public RefreshTokenResponse(String accessToken, String refreshToken){
        Token = accessToken;
        RefreshToken = refreshToken;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getRefreshToken() {
        return RefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        RefreshToken = refreshToken;
    }
}
