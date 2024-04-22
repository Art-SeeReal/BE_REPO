package com.ArtSeeReal.pro.service;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NO_DATA_ERROR;
import static com.ArtSeeReal.pro.etc.Uid.uidCreator;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
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
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioQueryDslRepository portfolioQueryDslRepository;
    private final PortfolioHistoryRepository portfolioHistoryRepository;
    private final PortfolioDeleteRepository portfolioDeleteRepository;
    private final FavoritePortfoliosRepository favoritePortfoliosRepository;

    private final String NO_BOARD_DATA_ERROR = "[ERROR] 게시물 데이터가 없습니다.";
    private final String NO_PAGE_ERROR = "[ERROR] 페이지가 1미만 또는 NULL값입니다.";

    public PortfolioCreateResponseDTO createPortfolio(PortfolioCreateRequestDTO dto){
        Portfolio changedEntityData = dto.form(uidCreator(portfolioRepository));
        Portfolio savedData = portfolioRepository.save(changedEntityData);
        PortfolioCreateResponseDTO result = savedData.toCreateResponseDTO();
        return result;
    }

    public PortfolioReadResponseDTO readPortfolio(String boardUid){
        PortfolioWithUserDTO dto = portfolioQueryDslRepository.findUserAndPortfolioByUid(boardUid);
        PortfolioReadResponseDTO result = dto.toReadResponseDTO();
        return result;
    }

    public PortfolioReadResponseDTO updatePortfolio(PortfolioUpdateRequestDTO dto){
        Portfolio portfolio = portfolioRepository.findById(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException(NO_BOARD_DATA_ERROR));
        // TODO : 수정유저 데이터는 아마 스프링 시큐리티 끝나면 받아올 수 있을 듯
        PortfolioHistory portfolioHistory = dto.createHistoryRecord(
                uidCreator(portfolioHistoryRepository),
                portfolio,
                "temp");
        portfolio.updateFromDTO(dto);
        portfolioHistoryRepository.save(portfolioHistory);
        Portfolio changedPortfolio = portfolioRepository.save(portfolio);
        PortfolioReadResponseDTO result = changedPortfolio.toReadResponseDTO();
        return result;
    }

    public String deletePortfolio(String boardUid){
        Portfolio portfolio = portfolioRepository.findById(boardUid)
                .orElseThrow(() -> new IllegalArgumentException(NO_BOARD_DATA_ERROR));
        // TODO : 삭제유저 데이터는 아마 스프링 시큐리티 끝나면 받아올 수 있을 듯
        PortfolioDelete deletedBoard = portfolio.toBoardDelete(boardUid,"temp");
        portfolioDeleteRepository.save(deletedBoard);
        portfolioRepository.deleteById(boardUid);
        return boardUid;
    }

    // TODO : 페이징 군을 나눌 때 지역, 분야, 제목, 작성자
    public Page<PortfolioReadResponseDTO> pageReadPortfolio(PortfolioReadRequestDTO dto){
        if (dto.getPageNum() == null || dto.getPageNum() < 0)
            throw new IllegalArgumentException(NO_PAGE_ERROR);

        List<PortfolioWithUserDTO> portfolioWithUser = portfolioQueryDslRepository
                .findByUserAndPortfolioOrderByRegDateDesc(dto);

        List<PortfolioReadResponseDTO> portfolioReadResponseDTOList = portfolioWithUser
                .stream()
                .map(pwuDTO -> pwuDTO.toReadResponseDTO())
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(dto.getPageNum(),dto.getLimit());

        return new PageImpl<>(portfolioReadResponseDTOList, pageable, portfolioRepository.count());
    }

    public void favoritePortfolioCreate(String userUid, String portfolioUid){
        // TODO : 검증로직을 만들 필요가 있지 않을까? EX) 유저 pk, 포트폴리오 pk의 유효성을 검사하는
        // TODO : 이거하다가 생각났는데 검증로직을 하나의 별도 서비스로 분리할 필요가 있지 않을까?
        FavoritePortfolioKey likes = new FavoritePortfolioKey(userUid,portfolioUid);
        if(favoritePortfoliosRepository.existsById(likes))
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
        favoritePortfoliosRepository.save(new FavoritePortfolios(likes));
    }

    public void favoritePortfolioDelete(String userUid, String portfolioUid){
        FavoritePortfolioKey likes = new FavoritePortfolioKey(userUid,portfolioUid);
        if(favoritePortfoliosRepository.existsById(likes))
            favoritePortfoliosRepository.deleteById(likes);
        else
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
    }

}
