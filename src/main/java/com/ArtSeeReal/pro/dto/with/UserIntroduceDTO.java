package com.ArtSeeReal.pro.dto.with;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserIntroduceDTO {
    private String nickname;
    private String email;
    private String phone;
    private String intro;
}
