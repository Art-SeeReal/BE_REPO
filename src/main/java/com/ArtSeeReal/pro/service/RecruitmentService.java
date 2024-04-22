package com.ArtSeeReal.pro.service;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NO_DATA_ERROR;
import static com.ArtSeeReal.pro.etc.Uid.uidCreator;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.with.RecruitmentWithUserDTO;
import com.ArtSeeReal.pro.entity.composite.ApplyRecruitmentKey;
import com.ArtSeeReal.pro.entity.delete.RecruitmentDelete;
import com.ArtSeeReal.pro.entity.history.RecruitmentHistory;
import com.ArtSeeReal.pro.entity.main.ApplyRecruitments;
import com.ArtSeeReal.pro.entity.main.Recruitment;
import com.ArtSeeReal.pro.repository.jpa.delete.RecruitmentDeleteRepository;
import com.ArtSeeReal.pro.repository.jpa.history.RecruitmentHistoryRepository;
import com.ArtSeeReal.pro.repository.jpa.main.ApplyRecruitmentsRepository;
import com.ArtSeeReal.pro.repository.jpa.main.RecruitmentRepository;
import com.ArtSeeReal.pro.repository.querydsl.main.RecruitmentQueryDslRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Log4j2
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentQueryDslRepository recruitmentQueryDslRepository;
    private final RecruitmentHistoryRepository recruitmentHistoryRepository;
    private final RecruitmentDeleteRepository recruitmentDeleteRepository;
    private final ApplyRecruitmentsRepository applyRecruitmentsRepository;

    private final String NO_BOARD_DATA_ERROR = "[ERROR] 게시물 데이터가 없습니다.";
    private final String NO_PAGE_ERROR = "[ERROR] 페이지가 1미만 또는 NULL값입니다.";

    public RecruitmentCreateResponseDTO createRecruitment(RecruitmentCreateRequestDTO dto){
        Recruitment changedEntityData = dto.form(uidCreator(recruitmentRepository));
        Recruitment savedData = recruitmentRepository.save(changedEntityData);
        RecruitmentCreateResponseDTO result = savedData.toCreateResponseDTO();
        return result;
    }

    public RecruitmentReadResponseDTO readRecruitment(String boardUid){
        RecruitmentWithUserDTO dto = recruitmentQueryDslRepository.findUserAndRecruitmentByUid(boardUid);
        if (dto == null)
            new IllegalArgumentException(NO_BOARD_DATA_ERROR);
        RecruitmentReadResponseDTO result = dto.toReadResponseDTO();
        return result;
    }

    @Transactional
    public RecruitmentReadResponseDTO updateRecruitment(RecruitmentUpdateRequestDTO dto){
        Recruitment recruitment = recruitmentRepository.findById(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException(NO_BOARD_DATA_ERROR));
        RecruitmentHistory recruitmentHistory = dto.createHistoryRecord(
                uidCreator(recruitmentHistoryRepository),
                recruitment,
                "temp");
        recruitment.updateFromDTO(dto);
        // TODO : 수정유저 데이터는 아마 스프링 시큐리티 끝나면 받아올 수 있을 듯
        recruitmentHistoryRepository.save(recruitmentHistory);
        Recruitment changedRecruitment = recruitmentRepository.save(recruitment);
        RecruitmentReadResponseDTO result = changedRecruitment.toReadResponseDTO();
        return result;
    }

    @Transactional
    public String deleteRecruitment(String boardUid){
        Recruitment recruitment = recruitmentRepository.findById(boardUid)
                .orElseThrow(() -> new IllegalArgumentException(NO_BOARD_DATA_ERROR));
        // TODO : 삭제유저 데이터는 아마 스프링 시큐리티 끝나면 받아올 수 있을 듯
        RecruitmentDelete deletedBoard = recruitment.toBoardDelete(boardUid,"temp");
        recruitmentDeleteRepository.save(deletedBoard);
        recruitmentRepository.deleteById(boardUid);
        return boardUid;
    }

    // TODO : 페이징 군을 나눌 때 지역, 분야, 제목, 작성자
    public Page<RecruitmentReadResponseDTO> pageReadRecruitment(Integer pageNum){
        if (pageNum == null || pageNum < 1)
            throw new IllegalArgumentException(NO_PAGE_ERROR);

        // TODO : 페이지 갯수에 따라서 정할 수 있을 듯
        Pageable pageable = PageRequest.of(pageNum - 1, 10);

        Page<RecruitmentWithUserDTO> recruitmentReadResponseDTOs =
                recruitmentQueryDslRepository.findByUserAndRecruitmentOrderByRegDateDesc(pageable);

        List<RecruitmentReadResponseDTO> recruitmentReadResponseDTOList = recruitmentReadResponseDTOs.getContent()
                .stream()
                .map(dto -> dto.toReadResponseDTO())
                .collect(Collectors.toList());

        return new PageImpl<>(recruitmentReadResponseDTOList, pageable, recruitmentReadResponseDTOs.getTotalElements());
    }

    public void applyRecruitmentCreate(String userUid, String recruitmentUid){
        // TODO : 검증로직을 만들 필요가 있지 않을까? EX) 유저 pk, 포트폴리오 pk의 유효성을 검사하는
        // TODO : 이거하다가 생각났는데 검증로직을 하나의 별도 서비스로 분리할 필요가 있지 않을까?
        ApplyRecruitmentKey likes = new ApplyRecruitmentKey(userUid,recruitmentUid);
        if(applyRecruitmentsRepository.existsById(likes))
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
        applyRecruitmentsRepository.save(new ApplyRecruitments(likes));
    }

    public void applyRecruitmentDelete(String userUid, String recruitmentUid){
        ApplyRecruitmentKey likes = new ApplyRecruitmentKey(userUid,recruitmentUid);
        if(applyRecruitmentsRepository.existsById(likes))
            applyRecruitmentsRepository.deleteById(likes);
        else
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void automaticDeletionOfNotices(){
        LocalDateTime today = LocalDateTime.now()
                .minusWeeks(1);
        List<String> dataToDelete = recruitmentRepository.findUidByDueDateAfter(today);
        for (String uid : dataToDelete)
            // TODO : 삭제유저 데이터는 아마 스프링 시큐리티 끝나면 받아올 수 있을 듯
            deleteRecruitment(uid);
    }
}
