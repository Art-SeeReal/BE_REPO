package com.ArtSeeReal.pro.dto.with;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.entity.main.Recruitment;
import com.ArtSeeReal.pro.entity.main.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RecruitmentWithUserDTO {
    private Recruitment recruitment;
    private User user;

    public RecruitmentReadResponseDTO toReadResponseDTO() {
        return RecruitmentReadResponseDTO.builder()
                .uid(recruitment.getUid())
                .userUid(user.getUid())
                .nickname(user.getNickname())
                .viewCnt(recruitment.getViewCnt())
                .title(recruitment.getTitle())
                .content(recruitment.getContent())
                .region(recruitment.getRegion())
                .category(recruitment.getCategory())
                .regDate(recruitment.getRegDate())
                .dueDate(recruitment.getDueDate())
                .build();
    }

}
