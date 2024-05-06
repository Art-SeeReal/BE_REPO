package com.ArtSeeReal.pro.introduceTests;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ArtSeeReal.pro.dto.introduce.IntroReadResponseDTO;
import com.ArtSeeReal.pro.dto.introduce.IntroUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.service.IntroduceService;
import com.ArtSeeReal.pro.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IntroduceUpdateTest {

    private final IntroduceService introduceService;
    private final UserService userService;
    private String userUid;
    @Autowired
    public IntroduceUpdateTest(IntroduceService introduceService, UserService userService) {
        this.introduceService = introduceService;
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

        userUid = userService.createUser(dto).getUid();
        introduceService.createIntro(userUid);
    }

    @Test
    void 자기소개_생성(){
        IntroUpdateRequestDTO updateDto = IntroUpdateRequestDTO.builder()
                .uid(userUid)
                .content("updateIntro")
                .build();
        IntroReadResponseDTO dto = introduceService.updateIntro(updateDto);
        assertThat(dto.getUid()).isEqualTo(userUid);
        assertThat(dto.getContent()).isEqualTo("updateIntro");
    }
}
