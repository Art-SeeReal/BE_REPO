package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.introduce.IntroReadResponseDTO;
import com.ArtSeeReal.pro.dto.introduce.IntroUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.response.user.MyInfoResponseDTO;
import com.ArtSeeReal.pro.dto.with.UserIntroduceDTO;

public interface IntroduceService {
    IntroReadResponseDTO createIntro(String userUid);
    IntroReadResponseDTO readIntro(String userUid);
    IntroReadResponseDTO updateIntro(IntroUpdateRequestDTO dto);
    UserIntroduceDTO readUserIntroduce(String userId);
    MyInfoResponseDTO myInfoRead(String userUid);
}
