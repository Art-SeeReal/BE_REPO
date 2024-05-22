package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.dto.portfolio.*;
import com.ArtSeeReal.pro.dto.with.PortfolioWithUserDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.*;
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
    public PortfolioReadResponseDTO readPortfolio(String boardUid){
        PortfolioWithUserDTO dto = portfolioQueryDslRepository.findUserAndPortfolioByUid(boardUid);
        return dto.toReadResponseDTO();
    }
    @Override
    public PortfolioReadResponseDTO updatePortfolio(PortfolioUpdateRequestDTO dto){
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
    // TODO : 페이징 군을 나눌 때 지역, 분야, 제목, 작성자
    public Page<PortfolioReadResponseDTO> pageReadPortfolio(PortfolioReadRequestDTO dto){
        if (dto.getPageNum() == null || dto.getPageNum() < 0)
            throw new IllegalArgumentException(NO_PAGE_ERROR.getMessage());

        List<PortfolioWithUserDTO> portfolioWithUser = portfolioQueryDslRepository
                .findByUserAndPortfolioOrderByRegDateDesc(dto);

        List<PortfolioReadResponseDTO> portfolioReadResponseDTOList = portfolioWithUser
                .stream()
                .map(PortfolioWithUserDTO::toReadResponseDTO)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(dto.getPageNum(),dto.getLimit());

        return new PageImpl<>(portfolioReadResponseDTOList, pageable, portfolioRepository.count());
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

}
