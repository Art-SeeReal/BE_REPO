package com.ArtSeeReal.pro.recruitmentTests;

import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentInfoResponseDTO;
import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.service.RecruitmentService;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class RecruitmentUpdateTest {

    private final RecruitmentService recruitService;
    private final UserService userService;
    private String userUid;
    private String uid;
    @Autowired
    public RecruitmentUpdateTest(RecruitmentService recruitService, UserService userService) {
        this.recruitService = recruitService;
        this.userService = userService;
    }
    @BeforeEach
    public void 더미데이터_생성(){
        UserCreateRequestDTO userDto = UserCreateRequestDTO
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

        userUid = userService.createUser(userDto).getUid();

        RecruitmentCreateRequestDTO dto = RecruitmentCreateRequestDTO.builder()
                .userUid(userUid)
                .title("testTitle")
                .content("testContent")
                .region(RegionType.SEOUL)
                .category(CategoryType.ART)
                .thumbnail("testThumbnail")
                .dueDate(LocalDateTime.now())
                .payment(1000000L)
                .build();
        uid = recruitService.createRecruitment(dto).getUid();
    }
    @Test
    public void 공고_업데이트(){
        RecruitmentUpdateRequestDTO dto = RecruitmentUpdateRequestDTO.builder()
                .uid(uid)
                .userUid(userUid)
                .title("updateTitle")
                .content("UpdateContent")
                .region(RegionType.BUSAN)
                .category(CategoryType.CRAFT)
                .thumbnail("updateThumbnail")
                .dueDate(LocalDateTime.now())
                .payment(2000000L)
                .build();
        RecruitmentInfoResponseDTO result = recruitService.updateRecruitment(dto);
        assertThat(result.getContent()).isEqualTo("UpdateContent");
    }
}
