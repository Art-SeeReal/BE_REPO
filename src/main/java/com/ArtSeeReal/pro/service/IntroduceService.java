package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.introduce.IntroReadResponseDTO;
import com.ArtSeeReal.pro.dto.introduce.IntroUpdateRequestDTO;

public interface IntroduceService {
    IntroReadResponseDTO createIntro(String userUid);
    IntroReadResponseDTO readIntro(String userUid);
    IntroReadResponseDTO updateIntro(IntroUpdateRequestDTO dto);
}
