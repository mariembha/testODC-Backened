package com.commerce.microcommerce.web.controller;

import com.commerce.microcommerce.model.AuthenticationMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping(path = "/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public AuthenticationMessage basicauth() {
        return new AuthenticationMessage("You are authenticated");
    }
}
