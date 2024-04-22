package com.ArtSeeReal.pro.controller;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;

import com.ArtSeeReal.pro.dto.user.UserResponseDTO;
import com.ArtSeeReal.pro.service.MailService;
import com.ArtSeeReal.pro.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Users API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final MailService mailService;
    @PostMapping("/login")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<?> login(@RequestBody String temp) throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
    @PostMapping("/logout")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<?> logout(@RequestBody String temp) throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
    @GetMapping("/find-user")
    public ResponseEntity<UserResponseDTO> findUser(@RequestParam String uid){
        return new ResponseEntity<>(userService.readUser(uid), HttpStatus.OK);
    }

}
