package com.ArtSeeReal.pro.service;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenService {
    String getTokenInCookie(HttpServletRequest request, String category);
    void tokenNullCheck(String token);
    void expiredCheck(String token);
    String getUserId(String token);
    String getRole(String token);
    String getCategory(String token);
    boolean checkTokenCategory(String token,String category);
    String createToken(String category, String userId, String role,Long time);
}
