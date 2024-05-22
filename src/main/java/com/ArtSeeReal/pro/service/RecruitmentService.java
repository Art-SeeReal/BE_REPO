package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.recruitment.*;
import org.springframework.data.domain.Page;

public interface RecruitmentService {
    RecruitmentCreateResponseDTO createRecruitment(RecruitmentCreateRequestDTO dto);
    RecruitmentReadResponseDTO readRecruitment(String boardUid);
    RecruitmentReadResponseDTO updateRecruitment(RecruitmentUpdateRequestDTO dto);
    String deleteRecruitment(String userUid, String boardUid);
    Page<RecruitmentReadResponseDTO> pageReadRecruitment(RecruitmentReadRequestDTO dto);
    void applyRecruitmentCreate(String userUid, String recruitmentUid);
    void applyRecruitmentDelete(String userUid, String recruitmentUid);
    void favoriteRecruitmentCreate(String userUid, String recruitmentUid);
    void favoriteRecruitmentDelete(String userUid, String recruitmentUid);
    void automaticDeletionOfNotices();
}
