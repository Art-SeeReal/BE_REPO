package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentCreateResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentInfoResponseDTO;
import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentListRequestDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentListResponseDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentReadResponseDTO;

public interface RecruitmentService {
    RecruitmentCreateResponseDTO createRecruitment(RecruitmentCreateRequestDTO dto);
    RecruitmentReadResponseDTO readRecruitment(String boardUid, String userUid);
    RecruitmentInfoResponseDTO updateRecruitment(RecruitmentUpdateRequestDTO dto);
    String deleteRecruitment(String userUid, String boardUid);
    void applyRecruitmentCreate(String userUid, String recruitmentUid);
    void applyRecruitmentDelete(String userUid, String recruitmentUid);
    void favoriteRecruitmentCreate(String userUid, String recruitmentUid);
    void favoriteRecruitmentDelete(String userUid, String recruitmentUid);
    void automaticDeletionOfNotices();
    RecruitmentListResponseDTO readRecruitment(RecruitmentListRequestDTO dto, String userUid);
    RecruitmentListResponseDTO myFavoriteRecruitments(String userUid, Long postCount);
}
