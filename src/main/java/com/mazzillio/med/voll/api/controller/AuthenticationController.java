package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.domain.user.AuthenticateData;
import com.mazzillio.med.voll.api.domain.user.User;
import com.mazzillio.med.voll.api.infra.security.JWTTokenData;
import com.mazzillio.med.voll.api.infra.security.TokenService;
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
    private AuthenticationManager authenticationManagerBean;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<Object> login(@RequestBody AuthenticateData data){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        Authentication auth = authenticationManagerBean.authenticate(authenticationToken);
        String tokenJWT = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(tokenJWT));
    }
}
