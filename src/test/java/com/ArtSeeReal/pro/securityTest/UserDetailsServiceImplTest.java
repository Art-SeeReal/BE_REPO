package com.ArtSeeReal.pro.securityTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserDetailsServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    @Autowired
    public UserDetailsServiceImplTest(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @BeforeEach
    public void 더미데이터_생성(){
        UserRequestDTO dto = UserRequestDTO
                .builder()
                .userId("test")
                .name("테스트")
                .password("test1234")
                .nickname("testNickname")
                .email("test@gmail.com")
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .regionType(RegionType.SEOUL)
                .userType(UserType.AUTHOR)
                .regDate(LocalDateTime.now())
                .build();

        userService.createUser(dto);
    }

    @Test
    public void testLoadUserByUsername_Success() { // 정상 작동
        String userId = "test";

        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);

        assertThat(userDetails.getUsername())
                .isEqualTo(userId);
    }

    @Test
    public void 유저아이디_없음() { // id 불일치
        String userId = "test1234567";

        assertThatThrownBy(() -> userDetailsService.loadUserByUsername(userId))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
