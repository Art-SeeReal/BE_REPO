package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.portfolio.*;
import com.ArtSeeReal.pro.service.PortfolioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Portfolios API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<PortfolioCreateResponseDTO> createPortfolio(
            @RequestBody PortfolioCreateRequestDTO dto){
        return new ResponseEntity<>(portfolioService.createPortfolio(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PortfolioReadResponseDTO> readPortfolio(
            @RequestParam(name = "portfolioUid") String portfolioUid){
        return new ResponseEntity<>(portfolioService.readPortfolio(portfolioUid), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PortfolioReadResponseDTO> updatePortfolio(
            @RequestBody PortfolioUpdateRequestDTO dto){
        return new ResponseEntity<>(portfolioService.updatePortfolio(dto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePortfolio(
            @RequestParam(name = "portfolioUid") String portfolioUid){
        return new ResponseEntity<>(portfolioService.deletePortfolio(portfolioUid), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PortfolioReadResponseDTO>> pagePortfolio(PortfolioReadRequestDTO dto){
        return new ResponseEntity<>(portfolioService.pageReadPortfolio(dto), HttpStatus.OK);
    }

    @PostMapping("/scrap")
    public ResponseEntity<Void> portfolioScrapCreate(String recruitUid){
        // TODO : userUid 가져 온 다음 수정
        portfolioService.favoritePortfolioCreate("userUid",recruitUid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/scrap")
    public ResponseEntity<Void> portfolioScrapDelete(String recruitUid){
        // TODO : userUid 가져 온 다음 수정
        portfolioService.favoritePortfolioDelete("userUid",recruitUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
