package dev.mrkresnofatih.africanpenguin.controllers;

import dev.mrkresnofatih.africanpenguin.exceptions.IncorrectCredentialsException;
import dev.mrkresnofatih.africanpenguin.models.LoginCredentials;
import dev.mrkresnofatih.africanpenguin.models.LoginTokenResponse;
import dev.mrkresnofatih.africanpenguin.models.ResponseHandler;
import dev.mrkresnofatih.africanpenguin.models.ResponsePackage;
import dev.mrkresnofatih.africanpenguin.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    private final IAuthService _authService;

    @Autowired
    public AuthController(IAuthService authService) {
        _authService = authService;
    }

    @PostMapping(path = "/login")
    public ResponsePackage<LoginTokenResponse> Login(@Valid @RequestBody LoginCredentials loginCredentials) throws IncorrectCredentialsException {
        var loginTokenResponse = _authService.LoginGetToken(loginCredentials);
        return new ResponseHandler<LoginTokenResponse>().WrapSuccess(loginTokenResponse);
    }

    @GetMapping(path = "/test")
    public ResponsePackage<String> Test() {
        return new ResponseHandler<String>().WrapSuccess("success");
    }
}
