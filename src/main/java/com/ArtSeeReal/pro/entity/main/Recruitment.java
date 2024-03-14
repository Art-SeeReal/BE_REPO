package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.delete.RecruitmentDelete;
import com.ArtSeeReal.pro.enums.RegionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "RECRUITMENT_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Recruitment {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(nullable = false, columnDefinition = "BIGINT default 0")
    private Long viewCnt;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private RegionType regionType;

    @Column(nullable = false)
    private Long category;

    @Column(nullable = false)
    private LocalDateTime regDate;

    // TODO : 아마 바이트타입으로 바꿀 필요 있을 듯
    @Column(length = 256)
    private String thumbnail;

    @Column(nullable = false)
    private LocalDateTime dueDate;

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
