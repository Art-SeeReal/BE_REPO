package com.ArtSeeReal.pro.oauthTests;

import com.ArtSeeReal.pro.dto.user.KakaoUser;
import com.ArtSeeReal.pro.dto.user.NaverUser;
import com.ArtSeeReal.pro.service.OAuthService;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OAuthServiceTest {

    @Autowired
    private OAuthService oAuthService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private UserService userService;

    @MockBean
    private TokenService tokenService;

    @Test
    public void testFetchKakaoToken() {
        String code = "sampleCode";
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = String.format("grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
                "kakaoClientId", "kakaoRedirectUri", code);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("access_token", "sampleAccessToken");

        Mockito.when(restTemplate.postForEntity(tokenUrl, entity, Map.class))
                .thenReturn(new ResponseEntity<>(responseMap, HttpStatus.OK));

        String accessToken = oAuthService.fetchKakaoToken(code);

        assertEquals("sampleAccessToken", accessToken);
    }

    @Test
    public void testFetchNaverToken() {
        String code = "sampleCode";
        String tokenUrl = "https://nid.naver.com/oauth2.0/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = String.format("grant_type=authorization_code&client_id=%s&client_secret=%s&redirect_uri=%s&code=%s",
                "naverClientId", "naverClientSecret", "naverRedirectUri", code);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("access_token", "sampleAccessToken");

        Mockito.when(restTemplate.postForEntity(tokenUrl, entity, Map.class))
                .thenReturn(new ResponseEntity<>(responseMap, HttpStatus.OK));

        String accessToken = oAuthService.fetchNaverToken(code);

        assertEquals("sampleAccessToken", accessToken);
    }

    @Test
    public void testFetchKakaoUserData() {
        String accessToken = "sampleAccessToken";
        String userUrl = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        KakaoUser kakaoUser = new KakaoUser();
        // 필요한 kakaoUser 필드를 설정합니다.

        Mockito.when(restTemplate.exchange(userUrl, HttpMethod.GET, entity, KakaoUser.class))
                .thenReturn(new ResponseEntity<>(kakaoUser, HttpStatus.OK));

        KakaoUser userData = oAuthService.fetchKakaoUserData(accessToken);

        assertEquals(kakaoUser, userData);
    }

    @Test
    public void testFetchNaverUserData() {
        String accessToken = "sampleAccessToken";
        String userUrl = "https://openapi.naver.com/v1/nid/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        NaverUser naverUser = new NaverUser();
        // 필요한 naverUser 필드를 설정합니다.

        Mockito.when(restTemplate.exchange(userUrl, HttpMethod.GET, entity, NaverUser.class))
                .thenReturn(new ResponseEntity<>(naverUser, HttpStatus.OK));

        NaverUser userData = oAuthService.fetchNaverUserData(accessToken);

        assertEquals(naverUser, userData);
    }
}
