package com.ArtSeeReal.pro.repository.querydsl.main;

import com.ArtSeeReal.pro.dto.request.portfolio.PortfolioListRequestDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioListResponseDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.with.PortfolioWithUserDTO;

public interface PortfolioQueryDslRepository {

    PortfolioWithUserDTO findUserAndPortfolioByUid(String uid);
    PortfolioListResponseDTO findListByPortfolioDTO(PortfolioListRequestDTO dto, String userUid);
    PortfolioReadResponseDTO findPortfolioReadByIdAndUserId(String portfolioUid, String userUid);

}
