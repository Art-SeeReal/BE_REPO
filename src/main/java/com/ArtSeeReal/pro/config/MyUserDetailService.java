package com.ArtSeeReal.pro.config;

import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {
    private final UserService userService;

    public MyUserDetailService(UserService userService){
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
        Optional<User> findUser = userService.findUserId(insertedUserId);
        User user = findUser.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .roles(String.valueOf(user.getUserType()))
                .build();
    }
}
