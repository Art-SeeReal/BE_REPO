package com.ArtSeeReal.pro.controller;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserUpdateRequestDTO;
import com.ArtSeeReal.pro.service.MailService;
import com.ArtSeeReal.pro.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import java.io.IOException;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Auth API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
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

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody UserRequestDTO dto){
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.OK);
    }

    @GetMapping("/find-id")
    public ResponseEntity<String> findId(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email) throws MessagingException, IOException {
        mailService.findId(name, email);
        return new ResponseEntity<>("전송되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/find-user")
    public ResponseEntity<UserResponseDTO> findUser(@RequestParam String uid){
        return new ResponseEntity<>(userService.readUser(uid), HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "userId") String userId) throws MessagingException, IOException {
        mailService.changePassword(name,email,userId);
        return new ResponseEntity<>("전송되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam(name = "nickname") String nickname) {
        return new ResponseEntity<>(userService.checkDuplicateUserNickname(nickname), HttpStatus.OK);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam(name = "email") String email) {
        return new ResponseEntity<>(userService.checkDuplicateUserEmail(email), HttpStatus.OK);
    }

    @GetMapping("/check-userid")
    public ResponseEntity<Boolean> checkUserid(@RequestParam(name = "userId") String userId) {
        return new ResponseEntity<>(userService.checkDuplicateUserId(userId), HttpStatus.OK);
    }
}
