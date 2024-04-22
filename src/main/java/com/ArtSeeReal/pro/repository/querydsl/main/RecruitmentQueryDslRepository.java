package com.ArtSeeReal.pro.repository.querydsl.main;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadRequestDTO;
import com.ArtSeeReal.pro.dto.with.RecruitmentWithUserDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentQueryDslRepository {

    List<RecruitmentWithUserDTO> findByUserAndRecruitmentOrderByRegDateDesc(RecruitmentReadRequestDTO dto);
    RecruitmentWithUserDTO findUserAndRecruitmentByUid(String uid);

}
