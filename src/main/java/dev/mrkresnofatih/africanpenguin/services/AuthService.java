package dev.mrkresnofatih.africanpenguin.services;

import dev.mrkresnofatih.africanpenguin.exceptions.IncorrectCredentialsException;
import dev.mrkresnofatih.africanpenguin.models.LoginCredentials;
import dev.mrkresnofatih.africanpenguin.models.LoginTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    @Value("${application.default-username}")
    private String Username;

    @Value("${application.default-password}")
    private String Password;

    @Override
    public LoginTokenResponse LoginGetToken(LoginCredentials loginCredentials) throws IncorrectCredentialsException {
        var username = loginCredentials.getUsername();
        var password = loginCredentials.getPassword();
        var valid = username.equals(Username) && password.equals(Password);
        if (!valid) {
            throw new IncorrectCredentialsException();
        }
        return new LoginTokenResponse("authToken");
    }

    @Override
    public Boolean ValidateToken(String token) {
        return token.equals("authToken");
    }
}
