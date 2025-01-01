package com.activity.Activity.controller;

import com.activity.Activity.DTO.UserLoginDTO;
import com.activity.Activity.DTO.UserRegisterDTO;
import com.activity.Activity.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO){
        return authenticationService.login(userLoginDTO);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserRegisterDTO userRegisterDTO){
        return authenticationService.register(userRegisterDTO);
    }
}
