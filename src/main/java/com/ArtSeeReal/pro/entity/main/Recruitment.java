package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.delete.RecruitmentDelete;
import com.ArtSeeReal.pro.entity.module.RecruitmentModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity(name = "RECRUITMENT_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
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
                .regionType(regionType)
                .category(category)
                .regDate(regDate)
                .dueDate(dueDate)
                .build();
    }

    public RecruitmentReadResponseDTO toReadResponseDTO() {
        return RecruitmentReadResponseDTO.builder()
                .uid(uid)
                .userUid(userUid)
                .viewCnt(viewCnt)
                .title(title)
                .content(content)
                .regionType(regionType)
                .category(category)
                .regDate(regDate)
                .dueDate(dueDate)
                .build();
    }

    public void updateFromDTO(RecruitmentUpdateRequestDTO dto){
        title = dto.getTitle();
        content = dto.getContent();
        regionType = dto.getRegionType();
        category = dto.getCategory();
        dueDate = dto.getDueDate();
    }

    public RecruitmentDelete toBoardDelete(String uid, String delUserUid){
        return RecruitmentDelete.builder()
                .uid(uid)
                .boardUid(this.uid)
                .userUid(userUid)
                .viewCnt(viewCnt)
                .title(title)
                .content(content)
                .regionType(regionType)
                .category(category)
                .regDate(regDate)
                .thumbnail(thumbnail)
                .dueDate(dueDate)
                .delDate(LocalDateTime.now())
                .delUserUid(delUserUid)
                .build();
    }
}
