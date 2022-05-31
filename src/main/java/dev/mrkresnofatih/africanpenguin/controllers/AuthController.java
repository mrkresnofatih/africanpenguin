package dev.mrkresnofatih.africanpenguin.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Value("${environment-settings.application-id}")
    private String applicationId;

    public AuthController() {
    }

    @GetMapping(path = "/login")
    public String Login() {
        return "logged in" + applicationId;
    }
}
