package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.user.UserResponseDTO;
import com.ArtSeeReal.pro.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "My API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/my")
public class MyInfoController {

    private final UserService userService;
    @GetMapping
    public ResponseEntity<UserResponseDTO> readUser(@RequestParam String uid){
        return new ResponseEntity<>(userService.readUser(uid),HttpStatus.OK);
    }
}
