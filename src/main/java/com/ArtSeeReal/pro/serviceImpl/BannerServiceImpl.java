package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.dto.banner.BannerCreateRequestDTO;
import com.ArtSeeReal.pro.dto.banner.BannerCreateResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerReadResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.delete.BannerDelete;
import com.ArtSeeReal.pro.entity.history.BannerHistory;
import com.ArtSeeReal.pro.entity.main.Banner;
import com.ArtSeeReal.pro.enums.error.ErrorCode;
import com.ArtSeeReal.pro.repository.jpa.delete.BannerDeleteRepository;
import com.ArtSeeReal.pro.repository.jpa.history.BannerHistoryRepository;
import com.ArtSeeReal.pro.repository.jpa.main.BannerRepository;
import com.ArtSeeReal.pro.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.ArtSeeReal.pro.etc.Uid.uidCreator;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

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
        BannerHistory bannerHistory = banner.toHistoryEntity(
                uidCreator(bannerHistoryRepository),
                dto,
                "tempAdmin");
        bannerHistoryRepository.save(bannerHistory);
        banner.change(dto);
        return bannerRepository.save(banner).toReadDTO();
    }

    public String deleteBanner(String uid){
        Banner banner = bannerRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_DATA_ERROR.getMessage()));
        BannerDelete bannerDelete = banner.toDeleteEntity(
                uidCreator(bannerDeleteRepository),
                "tempAdmin");
        bannerDeleteRepository.save(bannerDelete);
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
