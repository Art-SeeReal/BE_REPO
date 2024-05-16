package com.ArtSeeReal.pro.userTests;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;

@SpringBootTest
@Transactional
public class UserLikesDeleteTest {

    private final UserService userService;
    private String yourUserUid;
    private String myUserUid;
    @Autowired
    public UserLikesDeleteTest(UserService userService) {
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
                .userId("test1")
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
        userService.userLikesCreate(myUserUid,yourUserUid);
    }

    @Test
    void 유저_좋아요_삭제_성공(){
        userService.userLikesDelete(myUserUid,yourUserUid);
    }
}
