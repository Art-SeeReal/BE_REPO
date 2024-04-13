package com.ArtSeeReal.pro.securityTest;

import static com.ArtSeeReal.pro.enums.RegionType.SEOUL;
import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class FillerTest {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public FillerTest(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
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
                .regionType(SEOUL)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();

        userService.createUser(dto);
    }

    @Test
    void authenticate_무한루프_테스트(){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken("test", "test1234");
        authenticationManager.authenticate(authToken);
    }
}
