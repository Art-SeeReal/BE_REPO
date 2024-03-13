package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<PortfolioCreateResponseDTO> createPortfolio(PortfolioCreateRequestDTO dto){
        return new ResponseEntity<>(portfolioService.createPortfolio(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PortfolioReadResponseDTO> readPortfolio(String portfolioUid){
        return new ResponseEntity<>(portfolioService.readPortfolio(portfolioUid), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PortfolioReadResponseDTO> updatePortfolio(PortfolioUpdateRequestDTO dto){
        return new ResponseEntity<>(portfolioService.updatePortfolio(dto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePortfolio(String portfolioUid){
        return new ResponseEntity<>(portfolioService.deletePortfolio(portfolioUid), HttpStatus.NO_CONTENT);
    }

    // TODO : 1차적으로는 페이징 처리만 했지만 검색과 합쳐야 할듯
    @GetMapping("/page")
    public ResponseEntity<Page<PortfolioReadResponseDTO>> pagePortfolio(Integer pageNum){
        return new ResponseEntity<>(portfolioService.pageReadPortfolio(pageNum), HttpStatus.OK);
    }

}
