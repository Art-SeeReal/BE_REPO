package com.ArtSeeReal.pro.repository.querydsl.main;

import com.ArtSeeReal.pro.dto.response.user.ApplicantResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserAuthorDTO;
import com.ArtSeeReal.pro.dto.user.UserPlannerDTO;
import com.ArtSeeReal.pro.dto.with.UserIntroduceDTO;
import com.ArtSeeReal.pro.dto.with.UserWithIntroduceDTO;

import java.util.List;

public interface UserQueryDslRepository {
    UserWithIntroduceDTO findUserProfileByUserUid(String userUid);
    UserIntroduceDTO findUserIntroduceByUserUid(String userUid);
    List<UserAuthorDTO> findUserLikeAuthorByUserUid(String userUid);
    List<UserPlannerDTO> findUserLikePlannerByUserUid(String userUid);
    ApplicantResponseDTO findApplicantUsersByRecruitmentUid(String recruitmentUid);
}
