package com.ArtSeeReal.pro.jwt;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.etc.UserDetailsImpl;
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
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Log4j2
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 access키에 담긴 토큰을 꺼냄
        String accessToken = request.getHeader("access");

        // 토큰이 없다면 다음 필터로 넘김
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            //response body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired"); // 클라이언트에게 에러 메시지를 보내기 위해 PrintWriter 사용

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 토큰이 access인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {
            //response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token"); // 클라이언트에게 에러 메시지를 보내기 위해 PrintWriter 사용

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // username, role 값을 획득
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUserId(username);
        userRequestDTO.setUserType(UserType.valueOf(role));
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(userRequestDTO.from());

        // 토큰 검증 성공 후 인증 정보 설정
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetailsImpl, null, userDetailsImpl.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);

//        //request에서 Authorization 헤더를 찾음
//        String authorization= request.getHeader("Authorization");
//
//        //Authorization 헤더 검증
//        if (authorization == null || !authorization.startsWith("Bearer ")) {
//            log.error("token null");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        log.info("authorization now");
//
//        //Bearer 부분 제거 후 순수 토큰만 획득
//        String token = authorization.split(" ")[1];
//
//        //토큰 소멸 시간 검증
//        if (jwtUtil.isExpired(token)) {
//            log.error("token expired");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //토큰에서 username 과 role 획득
//        String username = jwtUtil.getUsername(token);
////        String role = jwtUtil.getRole(token);
//        String role = jwtUtil.getRole(token).split("_")[1];
//
//        UserRequestDTO userRequestDTO = new UserRequestDTO();
//        userRequestDTO.setUserId(username);
//        userRequestDTO.setPassword("temppassword");
//        userRequestDTO.setUserType(UserType.valueOf(role));
//
//        // 저장된 사용자 정보를 조회하여 UserDetails에 회원 정보 객체 담기
//        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(userRequestDTO.from());
////        UserDetailsImpl userDetailsImpl = userDetailsImpl.loadUserByUsername(username);
//
//        // 스프링 시큐리티 인증 토큰 생성
//        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetailsImpl, null, userDetailsImpl.getAuthorities());
//
//        // 세션에 사용자 등록 : 인증 정보를 SecurityContextHolder에 저장하여, 이후의 보안 처리에서 참조할 수 있도록
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        filterChain.doFilter(request, response);


    }
}
