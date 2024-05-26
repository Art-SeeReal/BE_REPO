package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.dto.user.KakaoUser;
import com.ArtSeeReal.pro.dto.user.NaverUser;
import com.ArtSeeReal.pro.dto.user.UserCreateResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserReadResponseDTO;
import com.ArtSeeReal.pro.service.OAuthService;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {
    private String kakaoClientId = System.getenv("KAKAO_CLIENT_ID");

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;

    private String naverClientId = System.getenv("NAVER_CLIENT_ID");
    private String naverClientSecret = System.getenv("CLIENT_SECRET");

    @Value("${naver.redirect_uri}")
    private String naverRedirectUri;

    private final RestTemplate restTemplate;
    private final UserService userService;
    private final TokenService tokenService;

    @Override
    public String fetchKakaoToken(String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = String.format("grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
                kakaoClientId, kakaoRedirectUri, code);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, entity, Map.class);

        return (String) response.getBody().get("access_token");
    }

    @Override
    public String fetchNaverToken(String code) {
        String tokenUrl = "https://nid.naver.com/oauth2.0/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = String.format("grant_type=authorization_code&client_id=%s&client_secret=%s&redirect_uri=%s&code=%s",
                naverClientId, naverClientSecret, naverRedirectUri, code);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, entity, Map.class);

        return (String) response.getBody().get("access_token");
    }

    public KakaoUser fetchKakaoUserData(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<KakaoUser> response = restTemplate
                .exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET, entity, KakaoUser.class);

        return response.getBody();
    }

    @Override
    public NaverUser fetchNaverUserData(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<NaverUser> response = restTemplate
                .exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.GET, entity, NaverUser.class);

        return response.getBody();
    }

    @Override
    public String kakaoLogin(KakaoUser user) {
        if (userService.checkDuplicateUserId(String.valueOf(user.getId()))) {
            UserCreateResponseDTO dto = userService.createUser(user.kakaoToUserDTO());
            return tokenService.createToken("access", dto.getUserId(), dto.getUserType().getLabel(), 600000L);
        } else {
            String userUid = userService.getUserUid(String.valueOf(user.getId()));
            UserReadResponseDTO dto = userService.readUser(userUid);
            return tokenService.createToken("access", dto.getUserId(), dto.getUserType().getLabel(), 600000L);
        }
    }

    @Override
    public String naverLogin(NaverUser user) {
        if (userService.checkDuplicateUserId(String.valueOf(user.getResponse().getId()))) {
            UserCreateResponseDTO dto = userService.createUser(user.getResponse().kakaoToUserDTO());
            return tokenService.createToken("access", dto.getUserId(), dto.getUserType().getLabel(), 600000L);
        } else {
            String userUid = userService.getUserUid(String.valueOf(user.getResponse().getId()));
            UserReadResponseDTO dto = userService.readUser(userUid);
            return tokenService.createToken("access", dto.getUserId(), dto.getUserType().getLabel(), 600000L);
        }
    }
}