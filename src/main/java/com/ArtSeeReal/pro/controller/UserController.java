package com.ArtSeeReal.pro.controller;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;

import com.ArtSeeReal.pro.dto.user.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "User API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{userId}/profile")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<UserResponseDTO> readUser(
            @RequestParam String userId) throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }

}
