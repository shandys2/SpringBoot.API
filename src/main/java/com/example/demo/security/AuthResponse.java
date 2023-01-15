package com.example.demo.security;

public class AuthResponse {
    Integer user_id;
    String username;
    String accessToken;

    public AuthResponse(Integer user_id, String username, String accessToken) {
        this.user_id = user_id;
        this.username = username;
        this.accessToken = accessToken;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
