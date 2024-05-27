package com.ArtSeeReal.pro.repository.querydsl.main;

import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentListRequestDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentListResponseDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.with.RecruitmentWithUserDTO;

public interface RecruitmentQueryDslRepository {

    RecruitmentWithUserDTO findUserAndRecruitmentByUid(String uid);
    RecruitmentListResponseDTO findListByRecruitmentDTO(RecruitmentListRequestDTO dto, String userUid);
    RecruitmentReadResponseDTO findRecruitmentReadByIdAndUserId(String recruitmentUid, String userUid);
}
