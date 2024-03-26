package com.ArtSeeReal.pro.userTests;

import static com.ArtSeeReal.pro.enums.RegionType.SEOUL;
import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
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
        UserRequestDTO dto = UserRequestDTO
                .builder()
                .userId("test")
                .name("테스트")
                .password("test1234")
                .nickname("testNickname")
                .email("yusm1231@gmail.com") // 자신의 이메일을 넣어서 실험 해보면 됩니다.
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

//    @Test
//    public void 아이디_찾기_정상작동() throws MessagingException, IOException {
//        String name = "테스트";
//        String email = "yusm1231@gmail.com"; // 자신의 이메일을 넣어서 실험 해보면 됩니다.
//
//        mailService.findId(name, email);
//    }

    @Test
    public void 비밀번호찾기_이메일이_존재하지_않을_때() throws MessagingException, IOException {
        String name = "테스트";
        String email = "yusm@gmail.com";
        String userid = "test";

        assertThatThrownBy(() -> mailService.changePassword(name,email,userid))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 비밀번호찾기_이름이_같지_않을_때() throws MessagingException, IOException {
        String name = "yusm";
        String email = "yusm1231@gmail.com"; // 자신의 이메일을 넣어서 실험 해보면 됩니다.
        String userid = "test";

        assertThatThrownBy(() -> mailService.changePassword(name,email,userid))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 비밀번호찾기_아이디_찾기_다름() throws MessagingException, IOException {
        String name = "테스트";
        String email = "yusm1231@gmail.com"; // 자신의 이메일을 넣어서 실험 해보면 됩니다.
        String userid = "yusm";

        assertThatThrownBy(() -> mailService.changePassword(name,email,userid))
                .isInstanceOf(IllegalArgumentException.class);
    }

//    @Test
//    public void 비밀번호찾기_아이디_찾기_정상작동() throws MessagingException, IOException {
//        String name = "테스트";
//        String email = "yusm1231@gmail.com"; // 자신의 이메일을 넣어서 실험 해보면 됩니다.
//        String userid = "test";
//
//        mailService.changePassword(name,email,userid);
//        userRepository.findByEmail("yusm1231@gmail.com").get();
//
//        assertThat(userRepository.findByEmail("yusm1231@gmail.com").get().getPassword())
//                .isEqualTo("test1234");
//
//    }
}
