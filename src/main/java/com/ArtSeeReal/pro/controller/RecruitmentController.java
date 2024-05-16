package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.recruitment.*;
import com.ArtSeeReal.pro.service.RecruitmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;
@Tag(name = "Recruits API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/recruits")
public class RecruitmentController {

    private final RecruitmentService recruitService;

    @PostMapping
    public ResponseEntity<RecruitmentCreateResponseDTO> createRecruitment(RecruitmentCreateRequestDTO dto){
        return new ResponseEntity<>(recruitService.createRecruitment(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RecruitmentReadResponseDTO> readRecruitment(String recruitmentUid){
        return new ResponseEntity<>(recruitService.readRecruitment(recruitmentUid), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RecruitmentReadResponseDTO> updateRecruitment(RecruitmentUpdateRequestDTO dto){
        return new ResponseEntity<>(recruitService.updateRecruitment(dto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRecruitment(String recruitmentUid){
        return new ResponseEntity<>(recruitService.deleteRecruitment(recruitmentUid), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<RecruitmentReadResponseDTO>> pageRecruitment(RecruitmentReadRequestDTO dto){
        return new ResponseEntity<>(recruitService.pageReadRecruitment(dto), HttpStatus.OK);
    }

    @GetMapping("/latest")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<Page<RecruitmentReadResponseDTO>> latest() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }

    @PostMapping("/scrap")
    public ResponseEntity<Void> recruitmentScrapCreate(String recruitUid){
        // TODO : userUid 가져 온 다음 수정
        recruitService.favoriteRecruitmentCreate("userUid",recruitUid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/scrap")
    public ResponseEntity<Void> recruitmentScrapDelete(String recruitUid){
        // TODO : userUid 가져 온 다음 수정
        recruitService.favoriteRecruitmentDelete("userUid",recruitUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/apply")
    public ResponseEntity<Void> recruitmentApplyCreate(String recruitUid){
        // TODO : userUid 가져 온 다음 수정
        recruitService.applyRecruitmentCreate("userUid",recruitUid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/apply")
    public ResponseEntity<Void> recruitmentApplyDelete(String recruitUid){
        // TODO : userUid 가져 온 다음 수정
        recruitService.applyRecruitmentDelete("userUid",recruitUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
