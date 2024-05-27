package com.ArtSeeReal.pro.dto.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApplicantDTO {
    private String name;
    private String nickName;
    private String phone;
    private String email;
    private String userId;
}
