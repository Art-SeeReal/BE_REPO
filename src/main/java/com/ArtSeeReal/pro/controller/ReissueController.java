package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.entity.main.RefreshEntity;
import com.ArtSeeReal.pro.jwt.JWTUtil;
import com.ArtSeeReal.pro.repository.jpa.main.RefreshRepository;
import com.ArtSeeReal.pro.service.RefreshService;
import com.ArtSeeReal.pro.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class ReissueController {
    private final RefreshService refreshService;
    private final TokenService tokenService;

    @PostMapping("/reissue")
    public ResponseEntity<Void> reissue(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = tokenService.getTokenInCookie(request,"refresh");
        refreshService.reissueToken(refreshToken);
        String userId = tokenService.getUserId(refreshToken);
        String role = tokenService.getRole(refreshToken);
        refreshService.settingTokens(response,userId,role);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}