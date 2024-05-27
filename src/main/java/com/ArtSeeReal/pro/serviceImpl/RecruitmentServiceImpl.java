package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentListRequestDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentListResponseDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.entity.composite.ApplyRecruitmentKey;
import com.ArtSeeReal.pro.entity.composite.FavoriteRecruitmentKey;
import com.ArtSeeReal.pro.entity.delete.RecruitmentDelete;
import com.ArtSeeReal.pro.entity.history.RecruitmentHistory;
import com.ArtSeeReal.pro.entity.main.ApplyRecruitments;
import com.ArtSeeReal.pro.entity.main.FavoriteRecruitments;
import com.ArtSeeReal.pro.entity.main.Recruitment;
import com.ArtSeeReal.pro.repository.jpa.delete.RecruitmentDeleteRepository;
import com.ArtSeeReal.pro.repository.jpa.history.RecruitmentHistoryRepository;
import com.ArtSeeReal.pro.repository.jpa.main.ApplyRecruitmentsRepository;
import com.ArtSeeReal.pro.repository.jpa.main.FavoriteRecruitmentsRepository;
import com.ArtSeeReal.pro.repository.jpa.main.RecruitmentRepository;
import com.ArtSeeReal.pro.repository.querydsl.main.RecruitmentQueryDslRepository;
import com.ArtSeeReal.pro.service.RecruitmentService;
import com.ArtSeeReal.pro.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.NO_BOARD_DATA_ERROR;
import static com.ArtSeeReal.pro.enums.error.ErrorCode.NO_DATA_ERROR;
import static com.ArtSeeReal.pro.etc.Uid.uidCreator;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Transactional
@Log4j2
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentQueryDslRepository recruitmentQueryDslRepository;
    private final RecruitmentHistoryRepository recruitmentHistoryRepository;
    private final RecruitmentDeleteRepository recruitmentDeleteRepository;
    private final ApplyRecruitmentsRepository applyRecruitmentsRepository;
    private final FavoriteRecruitmentsRepository favoriteRecruitmentsRepository;
    private final ValidateService validateService;
    @Override
    public RecruitmentCreateResponseDTO createRecruitment(RecruitmentCreateRequestDTO dto){
        Recruitment changedEntityData = dto.form(uidCreator(recruitmentRepository));
        Recruitment savedData = recruitmentRepository.save(changedEntityData);
        return savedData.toCreateResponseDTO();
    }
    @Override
    public RecruitmentReadResponseDTO readRecruitment(String boardUid, String userUid){
        return recruitmentQueryDslRepository.findRecruitmentReadByIdAndUserId(boardUid,userUid);
    }
    @Override
    public com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO updateRecruitment(RecruitmentUpdateRequestDTO dto){
        Recruitment recruitment = recruitmentRepository.findById(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException(NO_BOARD_DATA_ERROR.getMessage()));
        validateService.roleCheck(dto.getUserUid(),recruitment.getUserUid());
        RecruitmentHistory recruitmentHistory = dto.createHistoryRecord(
                uidCreator(recruitmentHistoryRepository),
                recruitment);
        recruitment.updateFromDTO(dto);
        recruitmentHistoryRepository.save(recruitmentHistory);
        Recruitment changedRecruitment = recruitmentRepository.save(recruitment);
        return changedRecruitment.toReadResponseDTO();
    }

    @Override
    public String deleteRecruitment(String userUid, String boardUid){
        Recruitment recruitment = recruitmentRepository.findById(boardUid)
                .orElseThrow(() -> new IllegalArgumentException(NO_BOARD_DATA_ERROR.getMessage()));
        validateService.roleCheck(userUid,recruitment.getUserUid());
        RecruitmentDelete deletedBoard = recruitment.toBoardDelete(boardUid);
        recruitmentDeleteRepository.save(deletedBoard);
        recruitmentRepository.deleteById(boardUid);
        return boardUid;
    }
    @Override
    public void applyRecruitmentCreate(String userUid, String recruitmentUid){
        ApplyRecruitmentKey likes = new ApplyRecruitmentKey(userUid,recruitmentUid);
        if(applyRecruitmentsRepository.existsById(likes))
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
        applyRecruitmentsRepository.save(new ApplyRecruitments(likes));
    }
    @Override
    public void applyRecruitmentDelete(String userUid, String recruitmentUid){
        ApplyRecruitmentKey likes = new ApplyRecruitmentKey(userUid,recruitmentUid);
        if(applyRecruitmentsRepository.existsById(likes))
            applyRecruitmentsRepository.deleteById(likes);
        else
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
    }
    @Override
    public void favoriteRecruitmentCreate(String userUid, String recruitmentUid){
        FavoriteRecruitmentKey likes = new FavoriteRecruitmentKey(userUid,recruitmentUid);
        if(favoriteRecruitmentsRepository.existsById(likes))
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
        favoriteRecruitmentsRepository.save(new FavoriteRecruitments(likes));
    }
    @Override
    public void favoriteRecruitmentDelete(String userUid, String recruitmentUid){
        FavoriteRecruitmentKey likes = new FavoriteRecruitmentKey(userUid,recruitmentUid);
        if(favoriteRecruitmentsRepository.existsById(likes))
            favoriteRecruitmentsRepository.deleteById(likes);
        else
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
    }
    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void automaticDeletionOfNotices(){
        LocalDateTime today = LocalDateTime.now()
                .minusWeeks(1);
        List<String> dataToDelete = recruitmentRepository.findUidByDueDateAfter(today);
        for (String uid : dataToDelete){
            Recruitment recruitment = recruitmentRepository.findById(uid)
                    .orElseThrow(() -> new IllegalArgumentException(NO_BOARD_DATA_ERROR.getMessage()));
            RecruitmentDelete deletedBoard = recruitment.toBoardDelete(uid);
            recruitmentDeleteRepository.save(deletedBoard);
            recruitmentRepository.deleteById(uid);
        }
    }

    @Override
    public RecruitmentListResponseDTO readRecruitment(RecruitmentListRequestDTO dto, String userUid) {
        return recruitmentQueryDslRepository.findListByRecruitmentDTO(dto,userUid);
    }
}
