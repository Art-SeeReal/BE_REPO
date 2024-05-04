package com.ArtSeeReal.pro.securityTest;

import com.ArtSeeReal.pro.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class FilterChainTest {

    private final SecurityConfig securityConfig;

    @Autowired
    public FilterChainTest(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }



}
