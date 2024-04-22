package com.ArtSeeReal.pro.controller;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.service.PortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<?> portfolioScrap() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }
    @DeleteMapping("/scrap")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<?> portfolioScrap(String id) throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
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
