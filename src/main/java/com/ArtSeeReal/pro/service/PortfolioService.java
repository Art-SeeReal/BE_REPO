package com.ArtSeeReal.pro.service;

import static com.ArtSeeReal.pro.etc.Uid.uidCreator;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.with.PortfolioWithUserDTO;
import com.ArtSeeReal.pro.entity.delete.PortfolioDelete;
import com.ArtSeeReal.pro.entity.history.PortfolioHistory;
import com.ArtSeeReal.pro.entity.main.Portfolio;
import com.ArtSeeReal.pro.repository.jpa.delete.PortfolioDeleteRepository;
import com.ArtSeeReal.pro.repository.jpa.history.PortfolioHistoryRepository;
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

    @Transactional
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

    @Transactional
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
    public Page<PortfolioReadResponseDTO> pageReadPortfolio(Integer pageNum){
        if (pageNum == null || pageNum < 1)
            throw new IllegalArgumentException(NO_PAGE_ERROR);

        // TODO : 페이지 갯수에 따라서 정할 수 있을 듯
        Pageable pageable = PageRequest.of(pageNum - 1, 10);

        Page<PortfolioWithUserDTO> portfolioWithPage = portfolioQueryDslRepository
                .findByUserAndPortfolioOrderByRegDateDesc(pageable);

        List<PortfolioReadResponseDTO> portfolioReadResponseDTOList = portfolioWithPage.getContent()
                .stream()
                .map(dto -> dto.toReadResponseDTO())
                .collect(Collectors.toList());

        return new PageImpl<>(portfolioReadResponseDTOList, pageable, portfolioWithPage.getTotalElements());
    }

    public void viewCountPlus(String uid){
        portfolioRepository.incrementViewCnt(uid);
    }

}
