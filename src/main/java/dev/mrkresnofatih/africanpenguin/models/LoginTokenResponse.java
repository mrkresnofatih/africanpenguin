package dev.mrkresnofatih.africanpenguin.models;

import javax.validation.constraints.NotBlank;

public class LoginTokenResponse {
    @NotBlank
    private String Token;

    public LoginTokenResponse(String token) {
        Token = token;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
