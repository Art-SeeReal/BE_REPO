package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.banner.BannerCreateRequestDTO;
import com.ArtSeeReal.pro.dto.banner.BannerCreateResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerReadResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerUpdateRequestDTO;
import java.io.IOException;
import java.util.List;

public interface BannerService {

    BannerCreateResponseDTO createBanner(BannerCreateRequestDTO dto) throws IOException;
    BannerReadResponseDTO readBanner(String uid);
    BannerReadResponseDTO updateBanner(BannerUpdateRequestDTO dto) throws IOException;
    String deleteBanner(String uid);
    List<BannerReadResponseDTO> bannerList();
}
