package com.ArtSeeReal.pro.dto.response.user;

import com.ArtSeeReal.pro.dto.user.ApplicantDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApplicantResponseDTO {
    private List<ApplicantDTO> result;
    private Integer count;
}
