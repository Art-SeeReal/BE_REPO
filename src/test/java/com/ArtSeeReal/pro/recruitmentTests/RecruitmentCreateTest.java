package com.ArtSeeReal.pro.recruitmentTests;

import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.service.RecruitmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class RecruitmentCreateTest {

    private final RecruitmentService recruitService;
    @Autowired
    public RecruitmentCreateTest(RecruitmentService recruitService) {
        this.recruitService = recruitService;
    }

    @Test
    public void 공고_만들기(){
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
        recruitService.createRecruitment(dto);
    }
}
