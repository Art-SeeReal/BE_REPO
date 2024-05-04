package com.ArtSeeReal.pro.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;

@Controller
@ResponseBody
@Log4j2
public class AdminController {

    @GetMapping("/admin")
    public String adminP() {
        log.info("adminp start");
        // 세션 현재 사용자 id
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
log.info("id : " + id);
        // 세션 현재 사용자 role
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "admin Controller : id = " + id + " & role = " + role;
    }
}