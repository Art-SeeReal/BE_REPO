package com.ArtSeeReal.pro.userTests;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class UserDeleteTest {

    private final UserService userService;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private String userId;

    @Autowired
    public UserDeleteTest(UserService userService, UserRepository userRepository, TokenService tokenService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @BeforeEach
    public void 더미데이터_생성(){
        UserCreateRequestDTO dto = UserCreateRequestDTO
                .builder()
                .userId("test")
                .name("테스트")
                .password("test1234")
                .nickname("testNickname")
                .email("test@gmail.com")
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();
        userId = userService.createUser(dto).getUserId();
    }
    @Test
    public void 유저_삭제_테스트(){
        String token = tokenService.createToken("access",userId,"AUTHOR",600000L);
        String delUserUid = tokenService.getUserUid("배뤼어 " + token);
        userService.deleteUser(userId,delUserUid);
        assertThat(userRepository.existsByUid(userId)).isFalse();
    }
}
