package com.ArtSeeReal.pro.dto.introduce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class IntroReadResponseDTO {
    private final String content;
}
