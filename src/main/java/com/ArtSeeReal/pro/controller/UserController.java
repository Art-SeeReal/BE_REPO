package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserResponseDTO;
import com.ArtSeeReal.pro.service.MailService;
import com.ArtSeeReal.pro.service.UserService;
import jakarta.mail.MessagingException;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto){
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.OK);
    }

    @GetMapping("/user/duplicate/userId")
    public ResponseEntity<Boolean> checkDuplicateUserId(@RequestParam(name = "userId") String userId){
        return new ResponseEntity<>(userService.checkDuplicateUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/user/duplicate/nickname")
    public ResponseEntity<Boolean> checkDuplicateNickname(@RequestParam(name = "nickname") String nickname){
        return new ResponseEntity<>(userService.checkDuplicateUserNickname(nickname), HttpStatus.OK);
    }

    @GetMapping("/user/duplicate/email")
    public ResponseEntity<Boolean> checkDuplicateEmail(@RequestParam(name = "email") String email){
        return new ResponseEntity<>(userService.checkDuplicateUserEmail(email), HttpStatus.OK);
    }

    @GetMapping("/user/idFind")
    public ResponseEntity<String> idFind(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email) throws MessagingException, IOException {
        mailService.findId(name,email);
        return new ResponseEntity<>("전송되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/user/changePassword")
    public ResponseEntity<String> changePassword(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "userId") String userId) throws MessagingException, IOException {
        mailService.changePassword(name,email,userId);
        return new ResponseEntity<>("전송되었습니다.", HttpStatus.OK);
    }

}
