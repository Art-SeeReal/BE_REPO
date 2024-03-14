package com.ArtSeeReal.pro.repository.querydsl.main;

import com.ArtSeeReal.pro.dto.with.RecruitmentWithUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentQueryDslRepository {

    Page<RecruitmentWithUserDTO> findByUserAndRecruitmentOrderByRegDateDesc(Pageable pageable);
    RecruitmentWithUserDTO findUserAndRecruitmentByUid(String uid);

}
