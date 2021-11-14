package com.mosk.testjwtmessenger.controller;

import com.mosk.testjwtmessenger.config.jwt.JwtProvider;
import com.mosk.testjwtmessenger.dto.request.AuthRequest;
import com.mosk.testjwtmessenger.dto.response.AuthResponse;
import com.mosk.testjwtmessenger.dto.request.RegistrationRequest;
import com.mosk.testjwtmessenger.entity.UserEntity;
import com.mosk.testjwtmessenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    //регистрация нового пользователя
    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        userService.saveUser(u);
        return "welcome!";
    }

    //авторизация пользователя и получение токена безопасности
    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
}
