package com.ArtSeeReal.pro.recruitmentTests;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.service.RecruitmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class RecruitmentUpdateTest {

    private final RecruitmentService recruitService;
    private String uid;
    @Autowired
    public RecruitmentUpdateTest(RecruitmentService recruitService) {
        this.recruitService = recruitService;
    }
    @BeforeEach
    public void 더미데이터_생성(){

        RecruitmentCreateRequestDTO dto = RecruitmentCreateRequestDTO.builder()
                .userUid("testUid")
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
                .title("updateTitle")
                .content("UpdateContent")
                .region(RegionType.BUSAN)
                .category(CategoryType.CRAFT)
                .thumbnail("updateThumbnail")
                .dueDate(LocalDateTime.now())
                .payment(2000000L)
                .build();
        RecruitmentReadResponseDTO result = recruitService.updateRecruitment(dto);
        assertThat(result.getContent()).isEqualTo("UpdateContent");
    }
}
