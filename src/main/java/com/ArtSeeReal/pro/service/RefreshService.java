package com.ArtSeeReal.pro.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public interface RefreshService {
    void reissueToken(String token);
    void tokenCategoryCheck(String token,String category);
    void existsByRefreshCheck(String token);
    String createRefreshToken(String username, String role);
    void settingTokens(HttpServletResponse response,String username,String role);
    void deleteToken(String uid);
    String userUidGetter(String token);
    Cookie createCookie(String value);
    Cookie deleteCookie(String value);
}
