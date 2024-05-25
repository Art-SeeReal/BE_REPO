package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.request.portfolio.PortfolioListRequestDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioListResponseDTO;
import com.ArtSeeReal.pro.service.PortfolioService;
import com.ArtSeeReal.pro.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Portfolios API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/portfolios")
@Log4j2
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

//    @GetMapping
//    public ResponseEntity<PortfolioReadResponseDTO> readPortfolio(
//            @RequestParam(name = "portfolioUid") String portfolioUid){
//        return new ResponseEntity<>(portfolioService.readPortfolio(portfolioUid), HttpStatus.OK);
//    }

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
    @GetMapping
    public ResponseEntity<PortfolioListResponseDTO> pagePortfolio(
            PortfolioListRequestDTO dto,
            @RequestHeader(name = "Authorization", required = false, defaultValue = "") String header){
        log.info("헤더 : {}", header);
        if(header.isEmpty())
            return new ResponseEntity<>(portfolioService.readPortfolio(dto,null), HttpStatus.OK);
        else
            return new ResponseEntity<>(portfolioService.readPortfolio(dto,tokenService.getUserUid(header)), HttpStatus.OK);
    }

    @PostMapping("/scrap")
    public ResponseEntity<Void> portfolioScrapCreate(
            @RequestBody String portfolioUid,
            @RequestHeader("Authorization") String header){
        portfolioService.favoritePortfolioCreate(tokenService.getUserUid(header),portfolioUid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{recruitUid}/scrap")
    public ResponseEntity<Void> portfolioScrapDelete(
            @PathVariable("recruitUid") String portfolioUid,
            @RequestHeader("Authorization") String header){
        portfolioService.favoritePortfolioDelete(tokenService.getUserUid(header),portfolioUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
