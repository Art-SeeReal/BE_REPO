package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.portfolio.*;
import com.ArtSeeReal.pro.service.PortfolioService;
import com.ArtSeeReal.pro.service.TokenService;
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
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<PortfolioCreateResponseDTO> createPortfolio(
            @RequestHeader("Authorization") String header,
            @RequestBody PortfolioCreateRequestDTO dto){
        dto.setUserUid(tokenService.getUserUid(header));
        return new ResponseEntity<>(portfolioService.createPortfolio(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PortfolioReadResponseDTO> readPortfolio(
            @RequestParam(name = "portfolioUid") String portfolioUid){
        return new ResponseEntity<>(portfolioService.readPortfolio(portfolioUid), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PortfolioReadResponseDTO> updatePortfolio(
            @RequestHeader("Authorization") String header,
            @RequestBody PortfolioUpdateRequestDTO dto){
        dto.setUserUid(tokenService.getUserUid(header));
        return new ResponseEntity<>(portfolioService.updatePortfolio(dto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePortfolio(
            @RequestHeader("Authorization") String header,
            @RequestParam(name = "portfolioUid") String portfolioUid){
        return new ResponseEntity<>(portfolioService.deletePortfolio(portfolioUid,tokenService.getUserUid(header)), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PortfolioReadResponseDTO>> pagePortfolio(PortfolioReadRequestDTO dto){
        return new ResponseEntity<>(portfolioService.pageReadPortfolio(dto), HttpStatus.OK);
    }

    @PostMapping("/scrap")
    public ResponseEntity<Void> portfolioScrapCreate(
            @RequestBody String recruitUid,
            @RequestHeader("Authorization") String header){
        portfolioService.favoritePortfolioCreate(tokenService.getUserUid(header),recruitUid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/scrap")
    public ResponseEntity<Void> portfolioScrapDelete(
            @RequestBody String recruitUid,
            @RequestHeader("Authorization") String header){
        portfolioService.favoritePortfolioDelete(tokenService.getUserUid(header),recruitUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
