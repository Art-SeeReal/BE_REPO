package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    private final UserService userService;

    public AuthorizationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserRequestDTO dto){
        try {
            userService.createUser(dto);
            return ResponseEntity.ok("join success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
