package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.service.RecruitmentService;
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
@RequestMapping("/recruitment")
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
    public ResponseEntity<Page<RecruitmentReadResponseDTO>> ppageRecruitment(Integer pageNum){
        return new ResponseEntity<>(recruitService.pageReadRecruitment(pageNum), HttpStatus.OK);
    }

}