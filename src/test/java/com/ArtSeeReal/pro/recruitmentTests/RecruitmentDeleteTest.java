package com.ArtSeeReal.pro.recruitmentTests;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.service.RecruitmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class RecruitmentDeleteTest {

    private final RecruitmentService recruitService;
    private String uid;
    @Autowired
    public RecruitmentDeleteTest(RecruitmentService recruitService) {
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
    public void 공고_삭제하기(){
        recruitService.deleteRecruitment(uid);
    }
}
