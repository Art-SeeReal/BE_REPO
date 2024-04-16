package com.ArtSeeReal.pro.controller;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.service.RecruitmentService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    // TODO : 1차적으로는 페이징 처리만 했지만 검색과 합쳐야 할듯
    @GetMapping("/page")
    @Operation(summary = "준구현 상태 입니다.",description = "모든 공고를 시간의 역순으로 정렬하여 10개 반환 합니다.")
    public ResponseEntity<Page<RecruitmentReadResponseDTO>> pageRecruitment(Integer pageNum){
        return new ResponseEntity<>(recruitService.pageReadRecruitment(pageNum), HttpStatus.OK);
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

}
