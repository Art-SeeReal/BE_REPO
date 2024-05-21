package com.ArtSeeReal.pro.jwt;

import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.etc.UserDetailsImpl;
import com.ArtSeeReal.pro.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Log4j2
@Component
public class JWTFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 access키에 담긴 토큰을 꺼냄
        String accessToken = "";
        try{
            accessToken = tokenService.getTokenInCookie(request,"access");
            // 토큰이 없다면 다음 필터로 넘김
        } catch (NullPointerException e){
            filterChain.doFilter(request, response);
            return;
        }



        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            tokenService.expiredCheck(accessToken);
        } catch (ExpiredJwtException e) {
            //response body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired"); // 클라이언트에게 에러 메시지를 보내기 위해 PrintWriter 사용

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 토큰이 access인지 확인 (발급시 페이로드에 명시)
        if (!tokenService.checkTokenCategory(accessToken,"access")) {
            //response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token"); // 클라이언트에게 에러 메시지를 보내기 위해 PrintWriter 사용

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // username, role 값을 획득
        String username = tokenService.getUserId(accessToken);
        String role = tokenService.getRole(accessToken);

        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(User
                .builder()
                .userId(username)
                .userType(UserType.valueOf(role.replace("ROLE_","")))
                .build());

        // 토큰 검증 성공 후 인증 정보 설정
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetailsImpl, null, userDetailsImpl.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}
