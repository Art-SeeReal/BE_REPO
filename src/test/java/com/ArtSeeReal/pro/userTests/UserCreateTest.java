package com.ArtSeeReal.pro.userTests;

import static com.ArtSeeReal.pro.enums.RegionType.SEOUL;
import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        UserRequestDTO dto = UserRequestDTO
                .builder()
                .userId("test")
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
