package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.dto.introduce.IntroReadResponseDTO;
import com.ArtSeeReal.pro.dto.introduce.IntroUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.main.Introduce;
import com.ArtSeeReal.pro.enums.error.ErrorCode;
import com.ArtSeeReal.pro.repository.jpa.main.IntroduceRepository;
import com.ArtSeeReal.pro.service.IntroduceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntroduceServiceImpl implements IntroduceService {
    private final IntroduceRepository introduceRepository;
    @Override
    public IntroReadResponseDTO createIntro(String userUid) {
        Introduce intro = Introduce.builder()
                .uid(userUid)
                .content("")
                .build();
        return introduceRepository.save(intro)
                .entityToReadDTO();
    }

    @Override
    public IntroReadResponseDTO readIntro(String userUid) {
        Introduce intro = introduceRepository.findById(userUid)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_DATA_ERROR.getMessage()));
        return intro.entityToReadDTO();
    }

    @Override
    public IntroReadResponseDTO updateIntro(IntroUpdateRequestDTO dto) {
        Introduce intro = dto.updateDTOToEntity();
        return introduceRepository.save(intro)
                .entityToReadDTO();
    }
}
