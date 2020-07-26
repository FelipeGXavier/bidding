package com.licitacao.rest;


import com.licitacao.domain.User;
import com.licitacao.repository.UserRepository;
import com.licitacao.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public void save(@Valid @RequestBody UserRequest adminRequest){
        User admin = new User();
        admin.setUsername(adminRequest.getUsername());
        admin.setPassword(this.encoder.encode(adminRequest.getPassword()));
        admin.setAdmin(false);
        this.repository.save(admin);
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public void createAdmin(@Valid @RequestBody UserRequest adminRequest){
        User admin = new User();
        admin.setUsername(adminRequest.getUsername());
        admin.setPassword(this.encoder.encode(adminRequest.getPassword()));
        admin.setAdmin(true);
        this.repository.save(admin);
    }
}
