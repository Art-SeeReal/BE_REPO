package com.ArtSeeReal.pro.repository.querydsl.main;

import com.ArtSeeReal.pro.dto.with.UserIntroduceDTO;
import com.ArtSeeReal.pro.dto.with.UserWithIntroduceDTO;

public interface UserQueryDslRepository {
    UserWithIntroduceDTO findUserProfileByUserUid(String userUid);
    UserIntroduceDTO findUserIntroduceByUserUid(String userUid);
}
