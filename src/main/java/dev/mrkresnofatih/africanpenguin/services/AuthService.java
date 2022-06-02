package dev.mrkresnofatih.africanpenguin.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.mrkresnofatih.africanpenguin.exceptions.IncorrectCredentialsException;
import dev.mrkresnofatih.africanpenguin.models.LoginCredentials;
import dev.mrkresnofatih.africanpenguin.models.LoginTokenResponse;
import dev.mrkresnofatih.africanpenguin.utilities.TimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements IAuthService {
    @Value("${application.default-username}")
    private String Username;

    @Value("${application.default-password}")
    private String Password;

    @Value("${application.jwt-secret}")
    private String JwtSecret;

    @Override
    public LoginTokenResponse LoginGetToken(LoginCredentials loginCredentials) throws IncorrectCredentialsException {
        var username = loginCredentials.getUsername();
        var password = loginCredentials.getPassword();
        var valid = username.equals(Username) && password.equals(Password);
        if (!valid) {
            throw new IncorrectCredentialsException();
        }
        var token = generateToken();
        return new LoginTokenResponse(token);
    }

    @Override
    public Boolean ValidateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtSecret);
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Long now = TimeUtils.GetUtcNowMilliseconds();
            var role = jwt
                    .getClaim("role")
                    .asString();
            var hasAdminRole = Objects.isNull(role) || role.equals("admin");
            var expiry = jwt
                    .getClaim("expiresAt")
                    .asLong();
            var notExpired = Objects.isNull(expiry) || (expiry > now);
            return hasAdminRole && notExpired;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    private String generateToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtSecret);
            Long nextExpire = TimeUtils.GetUtcLaterMilliseconds(30);
            return JWT
                    .create()
                    .withClaim("role", "admin")
                    .withClaim("expiresAt", nextExpire)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            return null;
        }
    }
}
