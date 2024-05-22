package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.introduce.IntroReadResponseDTO;
import com.ArtSeeReal.pro.dto.introduce.IntroUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.user.*;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.IntroduceService;
import com.ArtSeeReal.pro.service.MailService;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;

@Tag(name = "User API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final MailService mailService;
    private final TokenService tokenService;
    private final IntroduceService introduceService;

    @GetMapping
    public ResponseEntity<UserReadResponseDTO> userRead(
            @RequestHeader("Authorization") String header){
        String userUid = tokenService.getUserUid(header);
        return new ResponseEntity<>(userService.readUser(userUid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserCreateResponseDTO> signUp(@RequestBody UserCreateRequestDTO dto){
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(
            @RequestHeader("Authorization") String header,
            @RequestParam String userId){
        String delUserUid = tokenService.getUserUid(header);
        return new ResponseEntity<>(userService.deleteUser(userId,delUserUid), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<UserReadResponseDTO> updateUser(
            @RequestBody UserUpdateRequestDTO dto,
            @RequestHeader("Authorization") String header){
        dto.setUid(tokenService.getUserUid(header));
        return new ResponseEntity<>(userService.updateUser(dto), HttpStatus.OK);
    }
    @GetMapping("/info")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<UserType> info() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
    @PutMapping("/intro")
    public ResponseEntity<IntroReadResponseDTO> intro(
            @RequestBody IntroUpdateRequestDTO dto,
            @RequestHeader("Authorization") String header) {
        dto.setUid(tokenService.getUserUid(header));
        return new ResponseEntity<>(introduceService.updateIntro(dto),HttpStatus.OK);
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
    public ResponseEntity<Void> userLike(
            @RequestBody String yourUserUid,
            @RequestHeader("Authorization") String header) {
        userService.userLikesCreate(tokenService.getUserUid(header),yourUserUid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/like")
    public ResponseEntity<Void> userLikeDelete(
            @RequestParam("yourUserUid") String yourUserUid,
            @RequestHeader("Authorization") String header) {
        userService.userLikesDelete(tokenService.getUserUid(header),yourUserUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/like/users")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<Void> myLikeUsers() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
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
    public ResponseEntity<UserProfileReadResponseDTO> readUser(
            @PathVariable String userId)  {
        return new ResponseEntity<>(userService.readIntro(userId),HttpStatus.OK);
    }
    @GetMapping("/scrap/portfolios")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<Void> myScrapPortfolios() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }

    @GetMapping("/scrap/recruits")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<Void> myScrapRecruits() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
}
