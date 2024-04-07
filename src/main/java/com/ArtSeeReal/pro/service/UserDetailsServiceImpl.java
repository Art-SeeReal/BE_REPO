package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.etc.UserDetailsImpl;
import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userData = userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")); // todo userUid로 검색하자

        return new UserDetailsImpl(userData);

    }
}
