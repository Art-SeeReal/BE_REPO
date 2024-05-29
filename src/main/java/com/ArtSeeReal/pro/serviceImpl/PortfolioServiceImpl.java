package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.request.portfolio.PortfolioListRequestDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioListResponseDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.entity.composite.FavoritePortfolioKey;
import com.ArtSeeReal.pro.entity.delete.PortfolioDelete;
import com.ArtSeeReal.pro.entity.history.PortfolioHistory;
import com.ArtSeeReal.pro.entity.main.FavoritePortfolios;
import com.ArtSeeReal.pro.entity.main.Portfolio;
import com.ArtSeeReal.pro.repository.jpa.delete.PortfolioDeleteRepository;
import com.ArtSeeReal.pro.repository.jpa.history.PortfolioHistoryRepository;
import com.ArtSeeReal.pro.repository.jpa.main.FavoritePortfoliosRepository;
import com.ArtSeeReal.pro.repository.jpa.main.PortfolioRepository;
import com.ArtSeeReal.pro.repository.querydsl.main.PortfolioQueryDslRepository;
import com.ArtSeeReal.pro.service.PortfolioService;
import com.ArtSeeReal.pro.service.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NO_BOARD_DATA_ERROR;
import static com.ArtSeeReal.pro.enums.error.ErrorCode.NO_DATA_ERROR;
import static com.ArtSeeReal.pro.etc.Uid.uidCreator;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioQueryDslRepository portfolioQueryDslRepository;
    private final PortfolioHistoryRepository portfolioHistoryRepository;
    private final PortfolioDeleteRepository portfolioDeleteRepository;
    private final FavoritePortfoliosRepository favoritePortfoliosRepository;
    private final ValidateService validateService;

    @Override
    public PortfolioCreateResponseDTO createPortfolio(PortfolioCreateRequestDTO dto){
        validateService.existsUser(dto.getUserUid());
        Portfolio changedEntityData = dto.form(uidCreator(portfolioRepository));
        Portfolio savedData = portfolioRepository.save(changedEntityData);
        return savedData.toCreateResponseDTO();
    }
    @Override
    public PortfolioReadResponseDTO readPortfolio(String boardUid, String userUid){
        return portfolioQueryDslRepository.findPortfolioReadByIdAndUserId(boardUid, userUid);
    }
    @Override
    public com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO updatePortfolio(PortfolioUpdateRequestDTO dto){
        Portfolio portfolio = portfolioRepository.findById(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException(NO_BOARD_DATA_ERROR.getMessage()));
        validateService.roleCheck(dto.getUserUid(),portfolio.getUserUid());
        saveUpdateEntity(dto, portfolio);
        portfolio.updateFromDTO(dto);
        Portfolio changedPortfolio = portfolioRepository.save(portfolio);
        return changedPortfolio.toReadResponseDTO();
    }

    private void saveUpdateEntity(PortfolioUpdateRequestDTO dto, Portfolio portfolio) {
        PortfolioHistory portfolioHistory = dto.createHistoryRecord(
                uidCreator(portfolioHistoryRepository),
                portfolio);
        portfolioHistoryRepository.save(portfolioHistory);
    }
    @Override
    public String deletePortfolio(String boardUid,String userUid){
        Portfolio portfolio = portfolioRepository.findById(boardUid)
                .orElseThrow(() -> new IllegalArgumentException(NO_BOARD_DATA_ERROR.getMessage()));
        validateService.roleCheck(userUid,portfolio.getUserUid());
        PortfolioDelete deletedBoard = portfolio.toBoardDelete(boardUid);
        portfolioDeleteRepository.save(deletedBoard);
        portfolioRepository.deleteById(boardUid);
        return boardUid;
    }
    @Override
    public void favoritePortfolioCreate(String userUid, String portfolioUid){
        FavoritePortfolioKey likes = new FavoritePortfolioKey(userUid,portfolioUid);
        if(favoritePortfoliosRepository.existsById(likes))
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
        favoritePortfoliosRepository.save(new FavoritePortfolios(likes));
    }
    @Override
    public void favoritePortfolioDelete(String userUid, String portfolioUid){
        FavoritePortfolioKey likes = new FavoritePortfolioKey(userUid,portfolioUid);
        if(favoritePortfoliosRepository.existsById(likes))
            favoritePortfoliosRepository.deleteById(likes);
        else
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
    }

    @Override
    public PortfolioListResponseDTO readPortfolio(PortfolioListRequestDTO dto, String userUid) {
        return portfolioQueryDslRepository.findListByPortfolioDTO(dto,userUid);
    }

    @Override
    public PortfolioListResponseDTO myFavoritePortFolios(String userUid, Long postCount) {
        return portfolioQueryDslRepository.findMyScrapPortfolioByUserUid(userUid,postCount);
    }

}
