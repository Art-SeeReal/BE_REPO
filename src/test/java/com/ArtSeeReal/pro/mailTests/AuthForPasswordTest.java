package com.ArtSeeReal.pro.mailTests;

import static com.ArtSeeReal.pro.enums.RegionType.SEOUL;
import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.repository.memory.MemoryRepository;
import com.ArtSeeReal.pro.service.MailService;
import com.ArtSeeReal.pro.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
public class AuthForPasswordTest {

    private final MailService mailService;
    private final UserService userService;
    private String userUid;
    @Autowired
    public AuthForPasswordTest(MailService mailService, UserService userService) {
        this.mailService = mailService;
        this.userService = userService;
    }

    @BeforeEach
    void 기초_세팅(){
        UserRequestDTO dto = UserRequestDTO
                .builder()
                .userId("testUser")
                .name("테스트")
                .password("test1234")
                .nickname("testNickname")
                .email("test@gmail.com")
//                .email("yusm1231@gmail.com")
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .regionType(SEOUL)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();

        userUid = userService.createUser(dto).getUid();
    }

    @Test
    void 아이디_다를_때(){
        String name = "errorName";
        String email = "errorEmail";
        String id = "errorId";

        assertThatThrownBy(() -> mailService.authForPassword(name,email,id))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 아이디_같고_이름_다를_때(){
        String name = "errorName";
        String email = "errorEmail";
        String id = "testUser";

        assertThatThrownBy(() -> mailService.authForPassword(name,email,id))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 아이디와_이름_같고_이메일_다를_때(){
        String name = "테스트";
        String email = "errorEmail";
        String id = "testUser";

        assertThatThrownBy(() -> mailService.authForPassword(name,email,id))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 정상작동_이메일전송() throws MessagingException, IOException {
        String name = "테스트";
        String email = "test@gmail.com";
        String id = "testUser";

        mailService.authForPassword(name, email, id);
    }
}
