package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.etc.MyUserDetails;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserId(username)
                .orElseThrow(() -> new IllegalArgumentException("X"));

        if(user != null)
            return new MyUserDetails(user);
        return null;
    }
}
