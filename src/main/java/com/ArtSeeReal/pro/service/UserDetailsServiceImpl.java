package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.etc.UserDetailsImpl;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username)
                .orElseThrow(() -> new IllegalArgumentException("유저아이디가 없음"));
        return new UserDetailsImpl(user);
    }
}
