package com.ArtSeeReal.pro.config;

//import com.ArtSeeReal.pro.jwt.CustomLogoutFilter;
import com.ArtSeeReal.pro.jwt.CustomLogoutFilter;
import com.ArtSeeReal.pro.jwt.JWTFilter;
import com.ArtSeeReal.pro.jwt.JWTUtil;
import com.ArtSeeReal.pro.jwt.LoginFilter;
import com.ArtSeeReal.pro.repository.jpa.main.RefreshRepository;
import com.ArtSeeReal.pro.service.RefreshService;
import com.ArtSeeReal.pro.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;
    private final TokenService tokenService;

    @Value("${front-end.server-url}")
    private String frontEndServerUrl;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors((cors -> cors.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration configuration = new CorsConfiguration();

                configuration.setAllowedOrigins(Collections.singletonList(frontEndServerUrl)); // 허용할 프론트 서버
                configuration.setExposedHeaders(Collections.singletonList("Authorization"));
                configuration.setAllowedMethods(Collections.singletonList("*")); // get, post 등 모든 메소드 허용
                configuration.setAllowedHeaders(Collections.singletonList("*"));
                configuration.setAllowCredentials(true);
                configuration.setMaxAge(3600L);


                return configuration;
            }
        })));


        //csrf disable
        http
                .csrf(AbstractHttpConfigurer::disable);

        //From 로그인 방식 disable
        http
                .formLogin(AbstractHttpConfigurer::disable);

        //http basic 인증 방식 disable
        http
                .httpBasic(AbstractHttpConfigurer::disable);

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/reissue").permitAll() // refresh 토큰으로 access 토큰 재발급하는 경로
                        .requestMatchers("/users","/login","/").permitAll() // api-docs 는 스웨거가 API 문서를 생성하기 위해 접근하는 경로
                        .requestMatchers("/swagger-ui/**","/api-docs/**").permitAll() // api-docs 는 스웨거가 API 문서를 생성하기 위해 접근하는 경로
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());
        http
                .addFilterBefore(new JWTFilter(jwtUtil,tokenService), LoginFilter.class);
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),refreshService),
                        UsernamePasswordAuthenticationFilter.class);
        http
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshService, tokenService), LogoutFilter.class);
        //세션 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}