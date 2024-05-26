package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.user.KakaoUser;
import com.ArtSeeReal.pro.dto.user.NaverUser;

public interface OAuthService {
    String fetchKakaoToken(String code);
    String fetchNaverToken(String code);
    KakaoUser fetchKakaoUserData(String accessToken);
    NaverUser fetchNaverUserData(String accessToken);
    String kakaoLogin(KakaoUser user);
    String naverLogin(NaverUser user);
}
