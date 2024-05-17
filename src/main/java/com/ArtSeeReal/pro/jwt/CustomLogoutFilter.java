package com.ArtSeeReal.pro.jwt;

import com.ArtSeeReal.pro.service.RefreshService;
import com.ArtSeeReal.pro.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {

    private final RefreshService refreshService;
    private final TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain filterChain) throws IOException, ServletException {

        //path and method verify
        if (!request.getRequestURI().matches("^\\/logout$")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        //get refresh token
        String refreshToken = tokenService.getTokenInCookie(request,"refresh");

        //refresh null check
        tokenService.tokenNullCheck(refreshToken);

        //expired check
        tokenService.expiredCheck(refreshToken);

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        refreshService.tokenCategoryCheck(refreshToken,"refresh");

        String uid = refreshService.userUidGetter(refreshToken);
        refreshService.existsByRefreshCheck(uid);

        //로그아웃 진행
        //Refresh 토큰 DB에서 제거
        refreshService.deleteToken(uid);

        //Refresh 토큰 Cookie 값 0
        Cookie cookie = refreshService.deleteCookie(null);

        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}