package com.ArtSeeReal.pro.dto.response.recuitment;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RecruitmentListResponseDTO {
    private List<RecruitmentReadDTO> result;
    private Integer count;
}
