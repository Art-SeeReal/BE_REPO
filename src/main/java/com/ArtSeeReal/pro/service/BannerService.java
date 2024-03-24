package com.ArtSeeReal.pro.service;

import static com.ArtSeeReal.pro.etc.Uid.uidCreator;

import com.ArtSeeReal.pro.dto.banner.BannerCreateRequestDTO;
import com.ArtSeeReal.pro.dto.banner.BannerCreateResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerReadResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.delete.BannerDelete;
import com.ArtSeeReal.pro.entity.main.Banner;
import com.ArtSeeReal.pro.enums.error.ErrorCode;
import com.ArtSeeReal.pro.repository.jpa.delete.BannerDeleteRepository;
import com.ArtSeeReal.pro.repository.jpa.history.BannerHistoryRepository;
import com.ArtSeeReal.pro.repository.jpa.main.BannerRepository;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
    private final BannerHistoryRepository bannerHistoryRepository;
    private final BannerDeleteRepository bannerDeleteRepository;

    public BannerCreateResponseDTO createBanner(BannerCreateRequestDTO dto) throws IOException {
        Banner banner = dto.toEntity(uidCreator(bannerRepository));
        return bannerRepository.save(banner).toCreateDTO();
    }

    public BannerReadResponseDTO readBanner(String uid) {
        return bannerRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_DATA_ERROR.getMessage()))
                .toReadDTO();
    }

    public BannerReadResponseDTO updateBanner(BannerUpdateRequestDTO dto) throws IOException {
        Banner banner = bannerRepository.findById(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_DATA_ERROR.getMessage()));
        // TODO : 머지 후 만들자
        // 히스토리 엔티티 생성, 저장 로직
        banner.change(dto);
        return bannerRepository.save(banner).toReadDTO();
    }

    public String deleteBanner(String uid){
        // TODO : 머지 후 만들자
        // 딜리트 엔티티 생성, 저장 로직
        bannerRepository.deleteById(uid);
        return uid;
    }

    public List<BannerReadResponseDTO> bannerList(){
        return bannerRepository.findAllByOrderByRegDateDesc()
                .stream()
                .map(banner -> banner.toReadDTO())
                .toList();
    }

}
