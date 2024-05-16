package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.entity.main.RefreshEntity;
import com.ArtSeeReal.pro.enums.error.ErrorCode;
import com.ArtSeeReal.pro.repository.jpa.main.RefreshRepository;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import com.ArtSeeReal.pro.service.RefreshService;
import com.ArtSeeReal.pro.service.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {

    private final TokenService tokenService;
    private final RefreshRepository refreshRepository;
    private final UserRepository userRepository; // TODO : 임시사용 삭제 예정 업데이트 후 유저 서비스에 유저 UID 반환하는 서비스 만들어야 함

    @Override
    public void reissueToken(String refreshToken) {
        tokenCategoryCheck(refreshToken,"refresh");
        tokenService.tokenNullCheck(refreshToken);
        tokenService.expiredCheck(refreshToken);
        String uid = userUidGetter(refreshToken);
        existsByRefreshCheck(uid);
    }

    @Override
    public void tokenCategoryCheck(String token,String category) {
        if (!tokenService.checkTokenCategory(token,category))
            throw new IllegalArgumentException(ErrorCode.NO_TYPE_REFRESH_TOKEN_ERROR.getMessage());
    }

    @Override
    public void existsByRefreshCheck(String uid) {
        if (!refreshRepository.existsById(uid))
            throw new IllegalArgumentException(ErrorCode.NOT_EXISTS_REFRESH_TOKEN_ERROR.getMessage());
    }

    @Override
    public String createRefreshToken(String username, String role) {
        String refreshToken = tokenService.createToken("refresh", username, role, 86400000L);
        RefreshEntity refreshEntity = RefreshEntity.builder()
                .uid(userUidGetter(refreshToken))
                .username(username)
                .refresh(refreshToken)
                .expiration(new Date(System.currentTimeMillis() + 86400000L).toString())
                .build();
        return refreshRepository.save(refreshEntity).getRefresh();
    }

    @Override
    public void settingTokens(HttpServletResponse response,String username,String role) {
        response.setHeader("access", tokenService.createToken("access", username, role, 600000L));
        String refreshToken = createRefreshToken(username,role);
        response.addCookie(createCookie(refreshToken));
    }

    @Override
    public void deleteToken(String uid) {
        refreshRepository.deleteById(uid);
    }
    @Override
    public String userUidGetter(String token){
        String userId = tokenService.getUserId(token);
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.ID_NOT_FOUND.getMessage()))
                .getUid();
    }
    @Override
    public Cookie createCookie(String value) {
        Cookie cookie = new Cookie("refresh", value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        return cookie;
    }
    @Override
    public Cookie deleteCookie(String value) {
        Cookie cookie = new Cookie("refresh", value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        return cookie;
    }
}
