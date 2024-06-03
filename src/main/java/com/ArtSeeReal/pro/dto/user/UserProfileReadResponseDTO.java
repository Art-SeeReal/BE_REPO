package com.ArtSeeReal.pro.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class UserProfileReadResponseDTO {

    private final String nickName;
    private final String email;
    private final String phone;
    private final String intro;
}
