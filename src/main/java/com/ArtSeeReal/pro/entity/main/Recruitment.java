package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentCreateResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentInfoResponseDTO;
import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.delete.RecruitmentDelete;
import com.ArtSeeReal.pro.entity.module.RecruitmentModule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name = "RECRUITMENT_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Table(indexes = {@Index(name = "idx_recruitment_title", columnList = "title")})
public class Recruitment extends RecruitmentModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;
    public RecruitmentCreateResponseDTO toCreateResponseDTO() {
        return RecruitmentCreateResponseDTO.builder()
                .uid(uid)
                .userUid(userUid)
                .viewCnt(viewCnt)
                .title(title)
                .content(content)
                .region(region)
                .category(category)
                .dueDate(dueDate)
                .regDate(regDate)
                .payment(payment)
                .build();
    }
    public RecruitmentInfoResponseDTO toReadResponseDTO() {
        return RecruitmentInfoResponseDTO.builder()
                .uid(uid)
                .userUid(userUid)
                .viewCnt(viewCnt)
                .title(title)
                .content(content)
                .region(region)
                .category(category)
                .regDate(regDate)
                .dueDate(dueDate)
                .payment(payment)
                .build();
    }
    public void updateFromDTO(RecruitmentUpdateRequestDTO dto){
        title = dto.getTitle();
        content = dto.getContent();
        region = dto.getRegion();
        category = dto.getCategory();
        dueDate = dto.getDueDate();
        payment = dto.getPayment();
    }
    public RecruitmentDelete toBoardDelete(String uid){
        return RecruitmentDelete.builder()
                .uid(uid)
                .boardUid(this.uid)
                .userUid(userUid)
                .viewCnt(viewCnt)
                .title(title)
                .content(content)
                .region(region)
                .category(category)
                .regDate(regDate)
                .thumbnail(thumbnail)
                .dueDate(dueDate)
                .payment(payment)
                .delDate(LocalDateTime.now())
                .build();
    }
}
