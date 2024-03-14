package com.ArtSeeReal.pro.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 사용자 정보
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String password = userDetails.getPassword();

        // 콘솔에 ID와 PW 출력
        System.out.println("** 로그인 성공! **");
        System.out.println("** 사용자 ID: " + username + " **");
        System.out.println("** 사용자 PW: " + password + " **");

        // 기본 로그인 성공 처리 (home 페이지로 리디렉션)
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.sendRedirect("/home");
    }
}

