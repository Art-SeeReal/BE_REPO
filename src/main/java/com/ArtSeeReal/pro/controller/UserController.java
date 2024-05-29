package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.introduce.IntroReadResponseDTO;
import com.ArtSeeReal.pro.dto.introduce.IntroUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioListResponseDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentListResponseDTO;
import com.ArtSeeReal.pro.dto.response.user.ApplicantResponseDTO;
import com.ArtSeeReal.pro.dto.user.*;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;

@Tag(name = "User API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Log4j2
public class UserController {

    private final UserService userService;
    private final MailService mailService;
    private final TokenService tokenService;
    private final IntroduceService introduceService;
    private final PortfolioService portfolioService;
    private final RecruitmentService recruitmentService;

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
    @GetMapping("/scrap/portfolios/{post-count}")
    public ResponseEntity<PortfolioListResponseDTO> scrapPortfolios(
            @RequestHeader(name = "Authorization", required = false, defaultValue = "") String header,
            @PathVariable(name = "post-count", required = false, value = "20") Long postCount) {
        return new ResponseEntity<>(portfolioService.myFavoritePortFolios(tokenService.getUserUid(header),postCount),HttpStatus.OK);
    }

    @GetMapping("/scrap/recruits/{post-count}")
    public ResponseEntity<RecruitmentListResponseDTO> scrapRecruits(
            @RequestHeader(name = "Authorization", required = false, defaultValue = "") String header,
            @PathVariable(name = "post-count", required = false, value = "20") Long postCount) {
        return new ResponseEntity<>(recruitmentService.myFavoriteRecruitments(tokenService.getUserUid(header),postCount),HttpStatus.OK);
    }

    @GetMapping("/apply/planner/{postId}")
    public ResponseEntity<ApplicantResponseDTO> readApplicants(@PathVariable("postId") String postId){
        return new ResponseEntity<>(userService.ApplicantList(postId),HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<HashMap<String,Boolean>> checkPassword(@RequestBody String password,
                                                 @RequestHeader("Authorization") String header){
        String userUid = tokenService.getUserUid(header);
        HashMap<String,Boolean> result = new HashMap<>();
        result.put("result", userService.checkPassword(userUid,password));
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
