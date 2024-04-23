package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
import org.springframework.data.domain.Page;

public interface PortfolioService {
    PortfolioCreateResponseDTO createPortfolio(PortfolioCreateRequestDTO dto);
    PortfolioReadResponseDTO readPortfolio(String boardUid);
    PortfolioReadResponseDTO updatePortfolio(PortfolioUpdateRequestDTO dto);
    String deletePortfolio(String boardUid);
    Page<PortfolioReadResponseDTO> pageReadPortfolio(PortfolioReadRequestDTO dto);
    void favoritePortfolioCreate(String userUid, String portfolioUid);
    void favoritePortfolioDelete(String userUid, String portfolioUid);
}


