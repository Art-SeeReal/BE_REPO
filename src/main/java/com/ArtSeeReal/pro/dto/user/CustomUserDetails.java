package com.ArtSeeReal.pro.dto.user;

import com.ArtSeeReal.pro.entity.main.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // role 값 반환
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_" + user.getUserType().getName().toUpperCase());
        return authorities;
    }

    @Override
    public String getPassword() { // pw 값 반환
        return user.getPassword();
    }

    @Override
    public String getUsername() { // id 값 반환
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() { // 계정 만료 여부 (true = 유효)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정 블락 여부 (true = 블락 아님)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // true = 유효
        return true;
    }

    @Override
    public boolean isEnabled() { // true = 유효
        return true;
    }
}
