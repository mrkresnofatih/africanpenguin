package dev.mrkresnofatih.africanpenguin.models;

import javax.validation.constraints.NotBlank;

public class LoginCredentials {
    @NotBlank
    private String Username;

    @NotBlank
    private String Password;

    public LoginCredentials(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
