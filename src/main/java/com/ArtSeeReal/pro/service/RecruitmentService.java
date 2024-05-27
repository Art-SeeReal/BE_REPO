package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentListRequestDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentListResponseDTO;

public interface RecruitmentService {
    RecruitmentCreateResponseDTO createRecruitment(RecruitmentCreateRequestDTO dto);
    com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentReadResponseDTO readRecruitment(String boardUid, String userUid);
    RecruitmentReadResponseDTO updateRecruitment(RecruitmentUpdateRequestDTO dto);
    String deleteRecruitment(String userUid, String boardUid);
    void applyRecruitmentCreate(String userUid, String recruitmentUid);
    void applyRecruitmentDelete(String userUid, String recruitmentUid);
    void favoriteRecruitmentCreate(String userUid, String recruitmentUid);
    void favoriteRecruitmentDelete(String userUid, String recruitmentUid);
    void automaticDeletionOfNotices();
    RecruitmentListResponseDTO readRecruitment(RecruitmentListRequestDTO dto, String userUid);
}
