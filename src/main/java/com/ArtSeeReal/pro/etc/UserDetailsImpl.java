package com.ArtSeeReal.pro.etc;

import com.ArtSeeReal.pro.entity.main.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    public UserDetailsImpl(User user) {
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
    public String getUsername() { // id 값 반환. 아래 getUserId 쓰는 것이 더 직관적.
        return user.getUserId();
    }

    public String getUserId(){
        return user.getUserId();
    }

    public String getUserUid(){ // todo view로 보내줄 때 사용?
        return user.getUid();
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
