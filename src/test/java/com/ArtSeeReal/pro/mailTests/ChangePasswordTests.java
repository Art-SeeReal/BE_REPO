package com.ArtSeeReal.pro.mailTests;

import static com.ArtSeeReal.pro.enums.RegionType.SEOUL;
import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
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
public class ChangePasswordTests {

    private final MailService mailService;
    private final UserService userService;
    private final MemoryRepository memoryRepository;
    private final UserRepository userRepository;
    private String token;
    private String userUid;
    @Autowired
    public ChangePasswordTests(MailService mailService, UserService userService, MemoryRepository memoryRepository,
                               UserRepository userRepository) {
        this.mailService = mailService;
        this.userService = userService;
        this.memoryRepository = memoryRepository;
        this.userRepository = userRepository;
    }

    @BeforeEach
    void 기초_세팅() throws MessagingException, IOException {
        UserRequestDTO dto = UserRequestDTO
                .builder()
                .userId("testUser")
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

        userUid = userService.createUser(dto).getUid();

        String name = "테스트";
        String email = "test@gmail.com";
        String id = "testUser";

        mailService.authForPassword(name, email, id);
        token = mailService.authCreateToken(memoryRepository.findAuthStr());
    }

    @Test
    void 잘못된_UUID(){
        String errorToken = "zxczxczxczxczxc";
        String password = "qweqweqwe";
        assertThatThrownBy(() -> mailService.changePassword(errorToken,password))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 올바른_UUID(){ // 간혹 에러가 뜰 수 있으나 개발자의 역량 문제로 올바른 테스트 코드를 짜지 못하겠다...
        String password = "qweqweqwe";
        mailService.changePassword(token,password);
        assertThat(userRepository.findById(userUid).get().getPassword())
                .isEqualTo(password);
    }
}
