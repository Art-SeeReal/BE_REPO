package com.ArtSeeReal.pro.dto.user;

import lombok.*;

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
