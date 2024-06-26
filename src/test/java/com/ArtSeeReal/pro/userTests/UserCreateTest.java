package com.ArtSeeReal.pro.userTests;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
public class UserCreateTest {


    private final UserService userService;
    @Autowired
    public UserCreateTest(UserService userService) {
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
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();

        userService.createUser(dto);
    }
    @Test
    public void 아이디_중복_검사_오류(){
        String id = "test";
        assertThatThrownBy(() -> userService.checkDuplicateUserId(id))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void 아이디_중복_검사_성공() {
        String id = "yusm";
        boolean result = userService.checkDuplicateUserId(id);
        assertThat(result).isFalse();
    }
    @Test
    public void 닉네임_중복_검사_오류(){
        String id = "testNickname";
        assertThatThrownBy(() -> userService.checkDuplicateUserNickname(id))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void 닉네임_중복_검사_성공() {
        String id = "yusmNickname";
        boolean result = userService.checkDuplicateUserNickname(id);
        assertThat(result).isFalse();
    }
    @Test
    public void 이메일_중복_검사_오류(){
        String id = "test@gmail.com";
        assertThatThrownBy(() -> userService.checkDuplicateUserEmail(id))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void 이메일_중복_검사_성공() {
        String id = "yusm@gmail.com";
        boolean result = userService.checkDuplicateUserEmail(id);
        assertThat(result).isFalse();
    }
}
