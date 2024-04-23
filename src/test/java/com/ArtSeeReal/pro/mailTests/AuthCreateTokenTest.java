package com.ArtSeeReal.pro.mailTests;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.repository.memory.MemoryRepository;
import com.ArtSeeReal.pro.service.MailService;
import com.ArtSeeReal.pro.service.UserService;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AuthCreateTokenTest {

    private final MailService mailService;
    private final UserService userService;
    private final MemoryRepository memoryRepository;

    @Autowired
    public AuthCreateTokenTest(MailService mailService, UserService userService, MemoryRepository memoryRepository) {
        this.mailService = mailService;
        this.userService = userService;
        this.memoryRepository = memoryRepository;
    }
    @BeforeEach
    void 기초_세팅() throws MessagingException, IOException {
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

        userService.createUser(dto).getUid();

        String name = "테스트";
        String email = "test@gmail.com";
        String id = "testUser";

        mailService.authForPassword(name, email, id);
    }
    @Test
    void 잘못된_토큰(){
        String authStr = "QWE";
        assertThatThrownBy(() -> mailService.authCreateToken(authStr))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 올바른_토큰(){
        assertThat(mailService.authCreateToken(memoryRepository.findAuthStr()))
                .isEqualTo(memoryRepository.findTokens());
    }

}
