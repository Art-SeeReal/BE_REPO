package com.ArtSeeReal.pro.repository.querydsl.main;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadRequestDTO;
import com.ArtSeeReal.pro.dto.with.RecruitmentWithUserDTO;
import java.util.List;

public interface RecruitmentQueryDslRepository {

    List<RecruitmentWithUserDTO> findByUserAndRecruitmentOrderByRegDateDesc(RecruitmentReadRequestDTO dto);
    RecruitmentWithUserDTO findUserAndRecruitmentByUid(String uid);

}
