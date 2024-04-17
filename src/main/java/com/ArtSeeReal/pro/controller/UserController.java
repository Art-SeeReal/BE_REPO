package com.ArtSeeReal.pro.controller;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserUpdateRequestDTO;
import com.ArtSeeReal.pro.enums.UserType;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "User API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody UserRequestDTO dto){
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam String uid){
        // TODO : tempUser는 스프링 시큐리티 문제가 해결되면 될라나?
        return new ResponseEntity<>(userService.deleteUser(uid,"tempUser"), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateRequestDTO dto){
        return new ResponseEntity<>(userService.updateUser(dto), HttpStatus.OK);
    }
    @GetMapping("/info")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<UserType> info() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
    @GetMapping("/intro")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<UserType> intro(@RequestParam String intro) throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
    @GetMapping("/like/portfolios")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<UserType> portfolios() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
    @GetMapping("/like/recruits")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<UserType> recruits() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
    @PostMapping("/like")
    public ResponseEntity<Void> userLike(String yourUserUid) {
        userService.userLikesCreate("tempUid",yourUserUid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/like")
    public ResponseEntity<Void> userLikeDelete(String yourUserUid) {
        userService.userLikesDelete("tempUid",yourUserUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/exist/nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam(name = "nickname") String nickname) {
        return new ResponseEntity<>(userService.checkDuplicateUserNickname(nickname), HttpStatus.OK);
    }

    @GetMapping("/exist/email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam(name = "email") String email) {
        return new ResponseEntity<>(userService.checkDuplicateUserEmail(email), HttpStatus.OK);
    }

    @GetMapping("/exist/userid")
    public ResponseEntity<Boolean> checkUserid(@RequestParam(name = "userId") String userId) {
        return new ResponseEntity<>(userService.checkDuplicateUserId(userId), HttpStatus.OK);
    }
    @GetMapping("/userId")
    public ResponseEntity<String> findUserId(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email) {
        return new ResponseEntity<>(mailService.findId(name, email),HttpStatus.OK);
    }
    @GetMapping("/exist")
    public ResponseEntity<Void> findPassword(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "userId") String userId) throws MessagingException, IOException {
        mailService.authForPassword(name, email, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/cert/email")
    public ResponseEntity<String> emailCert(@RequestParam(name = "certCode") String certCode) {
        return new ResponseEntity<>(mailService.authCreateToken(certCode),HttpStatus.OK);
    }
    @PostMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody String token,String newPassword) {
        mailService.changePassword(token, newPassword);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{userId}/profile")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<UserResponseDTO> readUser(
            @RequestParam String userId) throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
    @GetMapping("/types")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<UserResponseDTO> readUserTypes() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }

}
