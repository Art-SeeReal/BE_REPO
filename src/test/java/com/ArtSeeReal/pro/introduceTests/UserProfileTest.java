package com.ArtSeeReal.pro.introduceTests;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserProfileReadResponseDTO;
import com.ArtSeeReal.pro.service.IntroduceService;
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
public class UserProfileTest {

    private final UserService userService;

    @Autowired
    public UserProfileTest(UserService userService) {
        this.userService = userService;
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
                .emailSecret(false)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();

        userService.createUser(dto);
    }

    @Test
    void 프로파일_검색(){
        UserProfileReadResponseDTO dto = userService.readIntro("test");
        assertThat(dto.getNickName()).isEqualTo("testNickname");
        assertThat(dto.getEmail()).isEqualTo("test@gmail.com");
        assertThat(dto.getPhone()).isEqualTo(null);
        assertThat(dto.getIntro()).isEqualTo("");
    }
}
