package com.ArtSeeReal.pro.jwt;

import com.ArtSeeReal.pro.entity.main.RefreshEntity;
import com.ArtSeeReal.pro.etc.UserDetailsImpl;
import com.ArtSeeReal.pro.repository.jpa.main.RefreshRepository;
import com.ArtSeeReal.pro.service.RefreshService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Log4j2
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final RefreshService refreshService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        // 클라이언트 요청에서 username, password 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        // 스프링 시큐리티에서 userId와 password 검증하기 위해 토큰에 담기
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        // 토큰에 담은 유저 정보를 검증을 위해 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    // 로그인 성공 시 실행하는 메소드 (여기서 jwt 발급)
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication){

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String username = authentication.getName();
        String role = auth.getAuthority();

        refreshService.settingTokens(response,username,role);

        response.setStatus(HttpStatus.OK.value());

    }

    // 로그인 실패 시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){
        response.setStatus(401); //로그인 실패시 401 응답 코드 반환
    }

}
