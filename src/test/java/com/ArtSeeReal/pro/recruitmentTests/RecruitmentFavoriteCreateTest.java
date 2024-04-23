package com.ArtSeeReal.pro.recruitmentTests;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
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
public class RecruitmentFavoriteCreateTest {

    private final RecruitmentService recruitmentService;
    private final UserService userService;
    private String recruitmentUid;
    private String userUid;
    @Autowired
    public RecruitmentFavoriteCreateTest(RecruitmentService recruitmentService, UserService userService) {
        this.recruitmentService = recruitmentService;
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

        userUid = userService.createUser(userCreateRequestDTO).getUid();

        RecruitmentCreateRequestDTO dto = RecruitmentCreateRequestDTO.builder()
                .userUid(userUid)
                .title("testTitle")
                .content("testContent")
                .region(RegionType.SEOUL)
                .category(CategoryType.CRAFT)
                .thumbnail("testThumbnail")
                .dueDate(LocalDateTime.now())
                .payment(1000000L)
                .build();
        recruitmentUid = recruitmentService.createRecruitment(dto).getUid();
    }

    @Test
    void 공고_즐겨찾기_성공(){
        recruitmentService.favoriteRecruitmentCreate(userUid,recruitmentUid);
    }
}
