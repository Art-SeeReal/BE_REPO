package com.ArtSeeReal.pro.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new SimplePasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/images/**","/view/join", "/auth/join").permitAll()  // 로그인 안해도 접속 가능하게 예외 처리
                        .anyRequest().authenticated() // 어떠한 요청이라도 인증 필요
                )
                .formLogin(login -> login // form 방식 로그인 사용
                        .loginPage("/view/login") // 커스텀 로그인 페이지 지정
                        .loginProcessingUrl("/login") // 로그인 처리 URL 지정
                        .usernameParameter("userid") // submit할 아이디
                        .passwordParameter("pw") // submit할 비밀번
                        .defaultSuccessUrl("/view/dashboard", true) // 성공 시 성공페이지로 이동
                        .permitAll() // 로그인 성공페이지 이동이 막히면 안되므로 허용
                )
                .logout(withDefaults()); // 로그아웃은 기본설정으로 (/logout 으로 인증해제)
        return http.build();
    }
}
