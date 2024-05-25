package com.ArtSeeReal.pro.repository.querydsl.main;

import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentListRequestDTO;
import com.ArtSeeReal.pro.dto.response.recuitment.RecruitmentListResponseDTO;
import com.ArtSeeReal.pro.dto.with.RecruitmentWithUserDTO;

public interface RecruitmentQueryDslRepository {

    RecruitmentWithUserDTO findUserAndRecruitmentByUid(String uid);
    RecruitmentListResponseDTO findListByRecruitmentDTO(RecruitmentListRequestDTO dto, String userUid);
}
