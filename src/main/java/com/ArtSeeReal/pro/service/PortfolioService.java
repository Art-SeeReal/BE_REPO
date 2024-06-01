package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.request.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioInfoResponseDTO;
import com.ArtSeeReal.pro.dto.request.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.request.portfolio.PortfolioListRequestDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioListResponseDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioReadResponseDTO;

public interface PortfolioService {
    PortfolioCreateResponseDTO createPortfolio(PortfolioCreateRequestDTO dto);
    PortfolioReadResponseDTO readPortfolio(String boardUid, String userUid);
    PortfolioInfoResponseDTO updatePortfolio(PortfolioUpdateRequestDTO dto);
    String deletePortfolio(String boardUid,String userUid);
    void favoritePortfolioCreate(String userUid, String portfolioUid);
    void favoritePortfolioDelete(String userUid, String portfolioUid);
    PortfolioListResponseDTO readPortfolio(PortfolioListRequestDTO dto, String userUid);
    PortfolioListResponseDTO myFavoritePortFolios(String userUid, Long postCount);
}


