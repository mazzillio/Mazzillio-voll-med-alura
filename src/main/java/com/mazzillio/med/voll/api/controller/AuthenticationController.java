package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.domain.user.AuthenticateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity<Object> login(@RequestBody AuthenticateData data){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        Authentication auth = authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
