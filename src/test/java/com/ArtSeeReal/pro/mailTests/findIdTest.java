package com.ArtSeeReal.pro.mailTests;

import static com.ArtSeeReal.pro.enums.RegionType.SEOUL;
import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserUpdateRequestDTO;
import com.ArtSeeReal.pro.service.MailService;
import com.ArtSeeReal.pro.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class findIdTest {

    private final MailService mailService;
    private final UserService userService;
    private String userUid;

    @Autowired
    public findIdTest(MailService mailService, UserService userService) {
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

    @Test
    @AfterEach
    void 정상작동_8자이하_5자이상(){
        UserUpdateRequestDTO dto = UserUpdateRequestDTO
                .builder()
                .uid(userUid)
                .userId("qwert")
                .name("테스트")
                .password("test1234")
                .nickname("changedNickname")
                .email("test@gmail.com")
                .emailSecret(false)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .regionType(SEOUL)
                .userType(AUTHOR)
                .modUserUid("승미니")
                .build();

        userService.updateUser(dto);


        String email = "test@gmail.com";
        String name = "테스트";
        assertThat(mailService.findId(name,email)).isEqualTo("qwe**");
    }

}
