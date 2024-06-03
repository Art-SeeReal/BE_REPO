package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentCreateResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentInfoResponseDTO;
import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentListRequestDTO;
import com.ArtSeeReal.pro.service.RecruitmentService;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.ValidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NOT_IMPLEMENTED_EXCEPTION;
@Tag(name = "Recruits API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/recruits")
@Log4j2
public class RecruitmentController {

    private final RecruitmentService recruitService;
    private final TokenService tokenService;
    private final ValidateService validateService;

    @PostMapping
    public ResponseEntity<RecruitmentCreateResponseDTO> createRecruitment(
            @RequestBody RecruitmentCreateRequestDTO dto,
            @RequestHeader("Authorization") String header){
        String userUid = tokenService.getUserUid(header);
        validateService.existsUser(userUid);
        return new ResponseEntity<>(recruitService.createRecruitment(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> readRecruitment(
            RecruitmentListRequestDTO dto,
            @RequestHeader(name = "Authorization", required = false, defaultValue = "") String header,
            @RequestParam(name = "portId", required = false, defaultValue = "") String postId) {
        String userUid = header.isEmpty() ? null : tokenService.getUserUid(header);
        if (postId != null && !postId.isEmpty())
            return new ResponseEntity<>(recruitService.readRecruitment(postId, userUid), HttpStatus.OK);
        if (dto != null)
            return new ResponseEntity<>(recruitService.readRecruitment(dto, userUid), HttpStatus.OK);
        return new ResponseEntity<>("해당 없음", HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<RecruitmentInfoResponseDTO> updateRecruitment(
            @RequestBody RecruitmentUpdateRequestDTO dto,
            @RequestHeader("Authorization") String header){
        dto.setUserUid(tokenService.getUserUid(header));
        return new ResponseEntity<>(recruitService.updateRecruitment(dto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRecruitment(@RequestParam String recruitmentUid,
                                                    @RequestHeader("Authorization") String header){
        return new ResponseEntity<>(recruitService.deleteRecruitment(tokenService.getUserUid(header), recruitmentUid), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/latest")
    @Operation(summary = "미구현 상태 입니다.")
    public ResponseEntity<Page<RecruitmentInfoResponseDTO>> latest() throws NotImplementedException {
        throw new NotImplementedException(NOT_IMPLEMENTED_EXCEPTION.getMessage());
    }

    @PostMapping("/scrap")
    public ResponseEntity<Void> recruitmentScrapCreate(@RequestBody String recruitUid,
                                                       @RequestHeader("Authorization") String header){
        recruitService.favoriteRecruitmentCreate(tokenService.getUserUid(header),recruitUid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{recruitUid}/scrap")
    public ResponseEntity<Void> recruitmentScrapDelete(@PathVariable("recruitUid") String recruitUid,
                                                       @RequestHeader("Authorization") String header){
        recruitService.favoriteRecruitmentDelete(tokenService.getUserUid(header),recruitUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/apply")
    public ResponseEntity<Void> recruitmentApplyCreate(@RequestBody String recruitUid,
                                                       @RequestHeader("Authorization") String header){
        recruitService.applyRecruitmentCreate(tokenService.getUserUid(header),recruitUid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{recruitUid}/apply")
    public ResponseEntity<Void> recruitmentApplyDelete(@PathVariable("recruitUid") String recruitUid,
                                                       @RequestHeader("Authorization") String header){
        recruitService.applyRecruitmentDelete(tokenService.getUserUid(header),recruitUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
