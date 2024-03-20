package com.ArtSeeReal.pro.userTests;

import static com.ArtSeeReal.pro.enums.RegionType.SEOUL;
import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserUpdateRequestDTO;
import com.ArtSeeReal.pro.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserUpdateTest {

    private final UserService userService;
    private String userUid;

    @Autowired
    public UserUpdateTest(UserService userService) {
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
        userUid = userService.createUser(dto).getUid();
    }

    @Test
    public void 업데이트_테스트(){
        UserUpdateRequestDTO dto = UserUpdateRequestDTO
                .builder()
                .uid(userUid)
                .userId("changedUserId")
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

        UserResponseDTO urd = userService.updateUser(dto);
        assertThat(urd.getUserId()).isEqualTo("changedUserId");
        assertThat(urd.getNickname()).isEqualTo("changedNickname");
        assertThat(urd.isEmailSecret()).isFalse();
    }
}
