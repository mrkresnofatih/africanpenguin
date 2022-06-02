package dev.mrkresnofatih.africanpenguin.services;

import dev.mrkresnofatih.africanpenguin.exceptions.IncorrectCredentialsException;
import dev.mrkresnofatih.africanpenguin.models.LoginCredentials;
import dev.mrkresnofatih.africanpenguin.models.LoginTokenResponse;

public interface IAuthService {
    LoginTokenResponse LoginGetToken(LoginCredentials loginCredentials) throws IncorrectCredentialsException;

    Boolean ValidateToken(String token);
}
