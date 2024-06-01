package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.user.UserReadResponseDTO;
import com.ArtSeeReal.pro.dto.with.UserIntroduceDTO;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.IntroduceService;
import com.ArtSeeReal.pro.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;
@Tag(name = "Users API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final IntroduceService introduceService;

    @PostMapping("/logout")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<?> logout(@RequestBody String temp) throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
    @GetMapping("/find-user")
    public ResponseEntity<UserReadResponseDTO> findUser(@RequestParam String uid){
        return new ResponseEntity<>(userService.readUser(uid), HttpStatus.OK);
    }

    @GetMapping("{userId}/profile")
    public ResponseEntity<UserIntroduceDTO> readUserIntro(@PathVariable("userId")String userId){
        return new ResponseEntity<>(introduceService.readUserIntroduce(userId),HttpStatus.OK);
    }

    @GetMapping("/{user-id}/type")
    public ResponseEntity<HashMap<String,String>> getUserType(@PathVariable("userId")String userId){
        HashMap<String,String> result = new HashMap<>();
        result.put("result", userService.getUserType(userId).getCode());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
