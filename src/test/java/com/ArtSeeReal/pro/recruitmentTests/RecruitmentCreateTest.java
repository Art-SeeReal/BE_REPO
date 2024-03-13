package com.ArtSeeReal.pro.recruitmentTests;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.service.RecruitmentService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
                .regionType(RegionType.SEOUL)
                .category(0L)
                .thumbnail("testThumbnail")
                .dueDate(LocalDateTime.now())
                .build();
        recruitService.createRecruitment(dto);
    }
}