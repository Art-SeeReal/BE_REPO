package com.ArtSeeReal.pro.dto.response.portfoilo;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioListDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PortfolioListResponseDTO {
    private List<PortfolioListDTO> result;
    private Integer count;
}
