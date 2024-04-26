package com.ArtSeeReal.pro.userTests;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserLikesCreateTest {

    private final UserService userService;
    private String yourUserUid;
    private String myUserUid;
    @Autowired
    public UserLikesCreateTest(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    void 더미데이터_생성(){
        UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO
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

        myUserUid = userService.createUser(userCreateRequestDTO).getUid();

        UserCreateRequestDTO userCreateRequestDTO2 = UserCreateRequestDTO
                .builder()
                .userId("test11")
                .name("테스트1")
                .password("test12341")
                .nickname("testNickname1")
                .email("test@gmail.com1")
                .emailSecret(true)
                .phone("010-1234-56781")
                .phoneSecret(true)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();
        yourUserUid = userService.createUser(userCreateRequestDTO2).getUid();

    }

    @Test
    void 유저_좋아요_성공(){
        userService.userLikesCreate(myUserUid,yourUserUid);
    }
}
