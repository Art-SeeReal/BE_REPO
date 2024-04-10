package com.ArtSeeReal.pro.recruitmentTests;

import static com.ArtSeeReal.pro.enums.RegionType.SEOUL;
import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.service.RecruitmentService;
import com.ArtSeeReal.pro.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RecruitmentPageTest {

    private final RecruitmentService recruitService;
    private final UserService userService;
    private String userUid;
    private String selectedUid;
    @Autowired
    public RecruitmentPageTest(RecruitmentService recruitService, UserService userService) {
        this.recruitService = recruitService;
        this.userService = userService;
    }
    @BeforeEach
    public void 더미데이터_생성(){

        for (int i = 1; i <= 10; i++) {
            UserRequestDTO userRequestDTO = UserRequestDTO
                    .builder()
                    .userId("test"+i)
                    .name("테스트"+i)
                    .password("test1234"+i)
                    .nickname("testNickname"+i)
                    .email("test@gmail.com"+i)
                    .emailSecret(true)
                    .phone("010-1234-5678"+i)
                    .phoneSecret(true)
                    .regionType(SEOUL)
                    .userType(AUTHOR)
                    .regDate(LocalDateTime.now())
                    .build();

            userUid = userService.createUser(userRequestDTO).getUid();

            for (int j = 1; j <= 10 ; j++) {
                RecruitmentCreateRequestDTO dto = RecruitmentCreateRequestDTO.builder()
                        .userUid(userUid)
                        .title("testTitle"+j*i)
                        .content("testContent"+j*i)
                        .region(RegionType.SEOUL)
                        .category(CategoryType.ART)
                        .thumbnail("testThumbnail"+j*i)
                        .dueDate(LocalDateTime.now())
                        .build();
                recruitService.createRecruitment(dto);
            }
        }

    }
    @Test
    public void 공고_페이징(){
        Page<RecruitmentReadResponseDTO> dto = recruitService.pageReadRecruitment(1);
        assertThat(dto.getContent().get(5).getContent()).isEqualTo("testContent50");
        for (int i = 0; i < dto.getContent().size(); i++) {
            System.out.println(i+"번째 데이터 : " + dto.getContent().get(i).toString());
        }
    }
}
