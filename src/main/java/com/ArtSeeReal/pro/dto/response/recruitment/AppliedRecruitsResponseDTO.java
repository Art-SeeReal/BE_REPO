package com.ArtSeeReal.pro.dto.response.recruitment;

import com.ArtSeeReal.pro.dto.recruitment.AppliedRecruitDTO;
import com.ArtSeeReal.pro.dto.recruitment.ApplyRecruitDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AppliedRecruitsResponseDTO {
    private List<AppliedRecruitDTO> result;
    private Long count;
}
