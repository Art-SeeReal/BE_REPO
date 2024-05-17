package com.ArtSeeReal.pro.mailTests;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserUpdateRequestDTO;
import com.ArtSeeReal.pro.service.MailService;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
public class FindIdTest {

    private final MailService mailService;
    private final UserService userService;
    private String userUid;

    @Autowired
    public FindIdTest(MailService mailService, UserService userService) {
        this.mailService = mailService;
        this.userService = userService;
    }

    @BeforeEach
    void 기초_세팅(){
        UserCreateRequestDTO dto = UserCreateRequestDTO
                .builder()
                .userId("testUser")
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

        userUid = userService.createUser(dto).getUid();
    }

    @Test
    void 이메일이_잘못되거나_없을_때(){
        String email = "errorEmail";
        String name = "errorName";
        assertThatThrownBy(() -> mailService.findId(name,email))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이메일은_같으나_이름이_잘못되었을_때(){
        String email = "test@gmail.com";
        String name = "errorName";
        assertThatThrownBy(() -> mailService.findId(name,email))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 정상작동_8자이상(){
        String email = "test@gmail.com";
        String name = "테스트";
        assertThat(mailService.findId(name,email)).isEqualTo("testU***");
    }

}
