package com.ArtSeeReal.pro.userTests;

import static com.ArtSeeReal.pro.enums.RegionType.SEOUL;
import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.service.RecruitmentService;
import com.ArtSeeReal.pro.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        UserRequestDTO userRequestDTO = UserRequestDTO
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

        myUserUid = userService.createUser(userRequestDTO).getUid();

        UserRequestDTO userRequestDTO2 = UserRequestDTO
                .builder()
                .userId("test1")
                .name("테스트1")
                .password("test12341")
                .nickname("testNickname1")
                .email("test@gmail.com1")
                .emailSecret(true)
                .phone("010-1234-56781")
                .phoneSecret(true)
                .regionType(SEOUL)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();
        yourUserUid = userService.createUser(userRequestDTO2).getUid();
        userService.userLikesCreate(myUserUid,yourUserUid);
    }

    @Test
    void 유저_좋아요_삭제_성공(){
        userService.userLikesDelete(myUserUid,yourUserUid);
    }
}
