package com.ArtSeeReal.pro.dto.response.recruitment;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentListDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RecruitmentListResponseDTO {
    private List<RecruitmentListDTO> result;
    private Integer count;
}
