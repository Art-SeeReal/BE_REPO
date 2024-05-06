package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.enums.error.ErrorCode;
import com.ArtSeeReal.pro.jwt.JWTUtil;
import com.ArtSeeReal.pro.service.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JWTUtil jwtUtil; // TODO 토큰 서비스랑 합쳐도 될거같음

    @Override
    public String getTokenInCookie(HttpServletRequest request, String category) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies)
            if (cookie.getName().equals(category))
                return cookie.getValue();
        throw new NullPointerException(ErrorCode.NO_REFRESH_TOKEN_ERROR.getMessage());
    }

    @Override
    public void tokenNullCheck(String token) {
        if (token == null)
            throw new NullPointerException(ErrorCode.NO_TOKEN_ERROR.getMessage());
    }

    @Override
    public void expiredCheck(String token) {
        if (jwtUtil.isExpired(token))
            throw new IllegalArgumentException(ErrorCode.EXPIRED_TOKEN_ERROR.getMessage());
    }

    @Override
    public String getUserId(String token) {
        return jwtUtil.getUsername(token);
    }

    @Override
    public String getRole(String token) {
        return jwtUtil.getRole(token);
    }

    @Override
    public String getCategory(String token) {
        return jwtUtil.getCategory(token);
    }

    @Override
    public boolean checkTokenCategory(String token,String category) {
        return jwtUtil.getCategory(token).equals(category);
    }

    @Override
    public String createToken(String category, String userId, String role, Long time) {
        return jwtUtil.createJwt(category, userId, role, time);
    }
}
