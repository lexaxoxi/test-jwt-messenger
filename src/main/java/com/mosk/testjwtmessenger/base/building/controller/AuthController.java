package com.mosk.testjwtmessenger.base.building.controller;

import com.mosk.testjwtmessenger.base.building.config.jwt.JwtProvider;
import com.mosk.testjwtmessenger.base.building.dto.request.AuthRequest;
import com.mosk.testjwtmessenger.base.building.dto.response.AuthResponse;
import com.mosk.testjwtmessenger.base.building.dto.request.RegistrationRequest;
import com.mosk.testjwtmessenger.base.building.entity.UserEntity;
import com.mosk.testjwtmessenger.base.building.service.UserService;
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
