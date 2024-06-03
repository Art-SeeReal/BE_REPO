package com.ArtSeeReal.pro.userTests;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import com.ArtSeeReal.pro.service.MailService;
import com.ArtSeeReal.pro.service.UserService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
public class UserLostInfo {

    private final MailService mailService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserLostInfo(MailService mailService, UserService userService, UserRepository userRepository) {
        this.mailService = mailService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void 더미데이터_생성(){
        UserCreateRequestDTO dto = UserCreateRequestDTO
                .builder()
                .userId("test")
                .name("테스트")
                .password("test1234")
                .nickname("testNickname")
                .email("yusm1231@gmail.com") // 자신의 이메일을 넣어서 실험 해보면 됩니다.
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();

        userService.createUser(dto);
    }

    @Test
    public void 아이디찾기_이메일이_존재하지_않을_때() throws MessagingException, IOException {
        String name = "테스트";
        String email = "yusm@gmail.com";

        assertThatThrownBy(() -> mailService.findId(name, email))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 아이디찾기_이름이_같지_않을_때() throws MessagingException, IOException {
        String name = "yusm";
        String email = "yusm1231@gmail.com"; // 자신의 이메일을 넣어서 실험 해보면 됩니다.

        assertThatThrownBy(() -> mailService.findId(name, email))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
