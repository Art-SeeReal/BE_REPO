package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.request.portfolio.PortfolioListRequestDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioListResponseDTO;

public interface PortfolioService {
    PortfolioCreateResponseDTO createPortfolio(PortfolioCreateRequestDTO dto);
    com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioReadResponseDTO readPortfolio(String boardUid, String userUid);
    PortfolioReadResponseDTO updatePortfolio(PortfolioUpdateRequestDTO dto);
    String deletePortfolio(String boardUid,String userUid);
    void favoritePortfolioCreate(String userUid, String portfolioUid);
    void favoritePortfolioDelete(String userUid, String portfolioUid);
    PortfolioListResponseDTO readPortfolio(PortfolioListRequestDTO dto, String userUid);
}


