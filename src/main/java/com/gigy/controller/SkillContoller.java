package com.gigy.controller;


import com.gigy.repository.PersonRepository;
import com.gigy.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAuthorizationServer
@RestController
@RequestMapping("/skill")
public class SkillContoller{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SkillRepository skillRepo;

}
