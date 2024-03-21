package com.ArtSeeReal.pro.controller;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
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

    // TODO : 1차적으로는 페이징 처리만 했지만 검색과 합쳐야 할듯
    @GetMapping("/page")
    @Operation(summary = "준구현 상태 입니다.",description = "모든 포트폴리오를 시간의 역순으로 정렬하여 10개 반환 합니다.")
    public ResponseEntity<Page<PortfolioReadResponseDTO>> pagePortfolio(Integer pageNum){
        return new ResponseEntity<>(portfolioService.pageReadPortfolio(pageNum), HttpStatus.OK);
    }

    @GetMapping("/latest")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<Page<PortfolioReadResponseDTO>> latest() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }

}
