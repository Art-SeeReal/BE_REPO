package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.user.KakaoUser;
import com.ArtSeeReal.pro.dto.user.NaverUser;
import com.ArtSeeReal.pro.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService oAuthService;

    @PostMapping("/kakao")
    public ResponseEntity<String> kakaoAuth(@RequestBody String code) {
        String accessToken = oAuthService.fetchKakaoToken(code);
        KakaoUser userData = oAuthService.fetchKakaoUserData(accessToken);
        return new ResponseEntity<>(oAuthService.kakaoLogin(userData), HttpStatus.OK);
    }

    @PostMapping("/naver")
    public ResponseEntity<String> naverAuth(@RequestBody String code) {
        String accessToken = oAuthService.fetchNaverToken(code);
        NaverUser userData = oAuthService.fetchNaverUserData(accessToken);
        return new ResponseEntity<>(oAuthService.naverLogin(userData), HttpStatus.OK);
    }
}
